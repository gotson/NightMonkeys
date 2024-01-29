package com.github.gotson.nightmonkeys.jxl;

import com.github.gotson.nightmonkeys.jxl.lib.enums.JxlColorProfileTarget;
import com.github.gotson.nightmonkeys.jxl.lib.enums.JxlDataType;
import com.github.gotson.nightmonkeys.jxl.lib.enums.JxlDecoderStatus;
import com.github.gotson.nightmonkeys.jxl.lib.enums.JxlEndianness;
import com.github.gotson.nightmonkeys.jxl.lib.enums.JxlSignature;
import com.github.gotson.nightmonkeys.jxl.lib.panama.JxlBasicInfo;
import com.github.gotson.nightmonkeys.jxl.lib.panama.JxlPixelFormat;
import com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageReadParam;
import javax.imageio.stream.ImageInputStream;
import java.awt.Rectangle;
import java.awt.color.ICC_Profile;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static com.github.gotson.nightmonkeys.common.imageio.IIOUtil.byteArrayFromStream;
import static com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h.C_CHAR;
import static com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h.C_INT;
import static com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h.C_LONG;

/**
 * Java bindings for libjxl via Foreign Linker API *
 */
public class JpegXl {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpegXl.class);

    private static final ValueLayout.OfInt pixelLayout = C_INT.withOrder(ByteOrder.BIG_ENDIAN);

    private static int[] version;

    private static void getLibVersionInt() {
        int versionInt = decode_h.JxlDecoderVersion();
        int major = versionInt / 1000000;
        int minor = (versionInt - major * 1000000) / 1000;
        int patch = versionInt - major * 1000000 - minor * 1000;
        version = new int[] {major, minor, patch};
    }

    public static boolean canDecode(final ImageInputStream stream) throws JxlException {
        try (var arena = Arena.ofConfined()) {
            stream.mark();
            var jxlData = new byte[12];
            stream.read(jxlData);
            stream.reset();

            var data = arena.allocate(jxlData.length);
            data.copyFrom(MemorySegment.ofArray(jxlData));

            var signature = JxlSignature.fromId(decode_h.JxlSignatureCheck(data, jxlData.length));
            return signature == JxlSignature.JXL_SIG_CODESTREAM || signature == JxlSignature.JXL_SIG_CONTAINER;
        } catch (IOException e) {
            throw new JxlException("Couldn't get stream content", e);
        }
    }

    public static String getLibVersion() {
        if (version == null) getLibVersionInt();
        return String.format("%d.%d.%d", version[0], version[1], version[2]);
    }

    private static boolean legacyIcc() {
        if (version == null) getLibVersionInt();
        return version[0] == 0 && version[1] < 9;
    }

    public static BasicInfo getBasicInfo(final ImageInputStream stream) throws JxlException {
        var dec = decode_h.JxlDecoderCreate(MemorySegment.NULL);

        try {
            try (var arena = Arena.ofConfined()) {
                if (dec == null) {
                    throw new JxlException("JxlDecoderCreate failed");
                }

                // we want the image with its transformations
                // this will return the correct width/height
                decode_h.JxlDecoderSetKeepOrientation(dec, 0);

                if (JxlDecoderStatus.JXL_DEC_SUCCESS != JxlDecoderStatus.fromId(
                    decode_h.JxlDecoderSubscribeEvents(dec, JxlDecoderStatus.JXL_DEC_BASIC_INFO.intValue() | JxlDecoderStatus.JXL_DEC_COLOR_ENCODING.intValue()))) {
                    throw new JxlException("JxlDecoderSubscribeEvents failed");
                }

                var info = JxlBasicInfo.allocate(arena);

                stream.mark();
                var jxlData = byteArrayFromStream(stream);
                stream.reset();

                var data = arena.allocate(jxlData.length);
                data.copyFrom(MemorySegment.ofArray(jxlData));
                decode_h.JxlDecoderSetInput(dec, data, jxlData.length);

                var format = JxlPixelFormat.allocate(arena);
                ByteBuffer iccProfile = ByteBuffer.allocate(0);

                while (true) {
                    var status = JxlDecoderStatus.fromId(decode_h.JxlDecoderProcessInput(dec));

                    if (status == JxlDecoderStatus.JXL_DEC_ERROR) {
                        throw new JxlException("Decoder error");
                    }
                    if (status == JxlDecoderStatus.JXL_DEC_NEED_MORE_INPUT) {
                        throw new JxlException("Error, already provided all input");
                    }
                    if (status == JxlDecoderStatus.JXL_DEC_BASIC_INFO) {
                        if (JxlDecoderStatus.JXL_DEC_SUCCESS != JxlDecoderStatus.fromId(decode_h.JxlDecoderGetBasicInfo(dec, info))) {
                            throw new JxlException("JxlDecoderGetBasicInfo failed");
                        }
                    } else if (status == JxlDecoderStatus.JXL_DEC_COLOR_ENCODING) {
                        // Get the ICC color profile of the pixel data
                        var iccSize = arena.allocate(C_LONG, 0);
                        int iccSizeResult;
                        if (legacyIcc()) {
                            iccSizeResult = decode_h.JxlDecoderGetICCProfileSizeLegacy(dec, format, JxlColorProfileTarget.JXL_COLOR_PROFILE_TARGET_DATA.intValue(), iccSize);
                        } else {
                            iccSizeResult = decode_h.JxlDecoderGetICCProfileSize(dec, JxlColorProfileTarget.JXL_COLOR_PROFILE_TARGET_DATA.intValue(), iccSize);
                        }
                        if (JxlDecoderStatus.JXL_DEC_SUCCESS != JxlDecoderStatus.fromId(
                            iccSizeResult)) {
                            throw new JxlException("JxlDecoderGetICCProfileSize failed");
                        }

                        iccProfile = ByteBuffer.allocateDirect(iccSize.get(C_INT, 0));
                        int iccProfileResult;
                        if (legacyIcc()) {
                            iccProfileResult = decode_h.JxlDecoderGetColorAsICCProfileLegacy(dec, format, JxlColorProfileTarget.JXL_COLOR_PROFILE_TARGET_DATA.intValue(),
                                MemorySegment.ofBuffer(iccProfile),
                                iccSize.get(C_LONG, 0));
                        } else {
                            iccProfileResult = decode_h.JxlDecoderGetColorAsICCProfile(dec, JxlColorProfileTarget.JXL_COLOR_PROFILE_TARGET_DATA.intValue(),
                                MemorySegment.ofBuffer(iccProfile),
                                iccSize.get(C_LONG, 0));
                        }
                        if (JxlDecoderStatus.JXL_DEC_SUCCESS !=
                            JxlDecoderStatus.fromId(
                                iccProfileResult)) {
                            throw new JxlException("JxlDecoderGetColorAsICCProfile failed");
                        }
                        break;
                    }
                }

                var iccData = new byte[iccProfile.remaining()];
                iccProfile.get(iccData);
                var icc = ICC_Profile.getInstance(iccData);

                return new BasicInfo(
                    JxlBasicInfo.xsize$get(info),
                    JxlBasicInfo.ysize$get(info),
                    JxlBasicInfo.alpha_bits$get(info) > 0,
                    JxlBasicInfo.have_animation$get(info) > 0,
                    JxlBasicInfo.num_color_channels$get(info),
                    icc,
                    1 // no support for animations
                );

            } catch (IOException e) {
                throw new JxlException("Couldn't get stream content", e);
            }
        } finally {
            decode_h.JxlDecoderDestroy(dec);
        }
    }

    public static void decode(final ImageInputStream stream, final BasicInfo info, WritableRaster raster, ImageReadParam param, int imageIndex) throws JxlException {
        var dec = decode_h.JxlDecoderCreate(MemorySegment.NULL.NULL);

        try {
            if (dec == null) {
                throw new JxlException("JxlDecoderCreate failed");
            }

            if (JxlDecoderStatus.JXL_DEC_SUCCESS !=
                JxlDecoderStatus.fromId(
                    decode_h.JxlDecoderSubscribeEvents(dec, JxlDecoderStatus.JXL_DEC_FULL_IMAGE.intValue()))) {
                throw new JxlException("JxlDecoderSubscribeEvents failed");
            }

            // we want the image with its transformations
            decode_h.JxlDecoderSetKeepOrientation(dec, 0);

            try (var arena = Arena.ofConfined()) {
                stream.mark();
                var jxlData = byteArrayFromStream(stream);
                stream.reset();

                var data = arena.allocate(jxlData.length);
                data.copyFrom(MemorySegment.ofArray(jxlData));
                decode_h.JxlDecoderSetInput(dec, data, jxlData.length);

                var dataType = JxlDataType.JXL_TYPE_UINT8;
                var format = JxlPixelFormat.allocate(arena);
                JxlPixelFormat.num_channels$set(format, 4);
                JxlPixelFormat.data_type$set(format, dataType.intValue());
                JxlPixelFormat.endianness$set(format, JxlEndianness.JXL_BIG_ENDIAN.intValue());
                JxlPixelFormat.align$set(format, 0);

                MemorySegment pixels = MemorySegment.NULL;

                while (true) {
                    var status = JxlDecoderStatus.fromId(decode_h.JxlDecoderProcessInput(dec));

                    if (status == JxlDecoderStatus.JXL_DEC_ERROR) {
                        throw new JxlException("Decoder error");
                    }
                    if (status == JxlDecoderStatus.JXL_DEC_NEED_MORE_INPUT) {
                        throw new JxlException("Error, already provided all input");
                    }

                    if (status == JxlDecoderStatus.JXL_DEC_NEED_IMAGE_OUT_BUFFER) {
                        var bufferSizeBytes = arena.allocate(C_LONG, 0);
                        if (JxlDecoderStatus.JXL_DEC_SUCCESS != JxlDecoderStatus.fromId(decode_h.JxlDecoderImageOutBufferSize(dec, format, bufferSizeBytes))) {
                            throw new JxlException("JxlDecoderImageOutBufferSize failed");
                        }

                        var bufferSizeBytesValue = bufferSizeBytes.get(C_INT, 0);
                        var bufferSizeExpected = info.width() * info.height() * JxlPixelFormat.num_channels$get(format) * dataType.sizeBytes();
                        if (bufferSizeBytesValue != bufferSizeExpected) {
                            throw new JxlException(
                                "Invalid out buffer size. Got: " + bufferSizeBytesValue + ", expected: " + bufferSizeExpected);
                        }

                        pixels = arena.allocateArray(C_CHAR, bufferSizeBytesValue);
                        if (JxlDecoderStatus.JXL_DEC_SUCCESS !=
                            JxlDecoderStatus.fromId(decode_h.JxlDecoderSetImageOutBuffer(dec, format, pixels, bufferSizeBytesValue))) {
                            throw new JxlException("JxlDecoderSetImageOutBuffer failed");
                        }
                    } else if (status == JxlDecoderStatus.JXL_DEC_FULL_IMAGE) {
                        // If the image is an animation, more full frames may be decoded
                        // We keep only the first image
                        break;
                    } else if (status == JxlDecoderStatus.JXL_DEC_SUCCESS) {
                        // All decoding successfully finished.
                        // It's not required to call JxlDecoderReleaseInput(dec.get()) here since
                        // the decoder will be destroyed.
                        break;
                    } else {
                        throw new JxlException("Unknown decoder status: " + status);
                    }
                }

                var pixelsRaster = new int[Math.min(info.width(), raster.getWidth()) * Math.min(info.height(), raster.getHeight())];

                var sourceRegion = param != null ? param.getSourceRegion() : null;
                if (sourceRegion == null) sourceRegion = new Rectangle(0, 0, info.width(), info.height());
                var ssX = param != null ? param.getSourceXSubsampling() : 1;
                var ssY = param != null ? param.getSourceYSubsampling() : 1;
                var ssOffX = param != null ? param.getSubsamplingXOffset() : 0;
                var ssOffY = param != null ? param.getSubsamplingYOffset() : 0;

                var offset = 0;
                for (int row = sourceRegion.y + ssOffY; row < sourceRegion.y + sourceRegion.height; row += ssY) {
                    if (offset >= pixelsRaster.length) break;
                    for (int col = sourceRegion.x + ssOffX; col < sourceRegion.x + sourceRegion.width; col += ssX) {
                        if (offset >= pixelsRaster.length) break;
                        int pixel = pixels.get(pixelLayout, (((long) row * info.width()) + col) * pixelLayout.byteSize());
                        pixelsRaster[offset++] = pixel;
                    }
                }

                if (param != null && param.getDestinationOffset() != null) {
                    raster.setDataElements(param.getDestinationOffset().x, param.getDestinationOffset().y, raster.getWidth() - param.getDestinationOffset().x,
                        raster.getHeight() - param.getDestinationOffset().y, pixelsRaster);
                } else {
                    raster.setDataElements(0, 0, raster.getWidth(), raster.getHeight(), pixelsRaster);
                }
            } catch (IOException e) {
                throw new JxlException("Couldn't get stream content", e);
            }
        } finally {
            decode_h.JxlDecoderDestroy(dec);
        }
    }
}
