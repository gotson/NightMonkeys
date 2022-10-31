package com.github.gotson.nightmonkeys.jxl;

import com.github.gotson.nightmonkeys.jxl.lib.enums.JxlColorProfileTarget;
import com.github.gotson.nightmonkeys.jxl.lib.enums.JxlDataType;
import com.github.gotson.nightmonkeys.jxl.lib.enums.JxlDecoderStatus;
import com.github.gotson.nightmonkeys.jxl.lib.enums.JxlEndianness;
import com.github.gotson.nightmonkeys.jxl.lib.enums.JxlSignature;
import com.github.gotson.nightmonkeys.jxl.lib.panama.JxlBasicInfo;
import com.github.gotson.nightmonkeys.jxl.lib.panama.JxlPixelFormat;
import com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.stream.ImageInputStream;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.nio.ByteBuffer;

import static com.github.gotson.nightmonkeys.common.imageio.IIOUtil.byteArrayFromStream;
import static com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h.C_INT;
import static com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h.C_LONG;

/**
 * Java bindings for libjxl via Foreign Linker API *
 */
public class JpegXl {

    private static final Logger LOG = LoggerFactory.getLogger(JpegXl.class);

    public static boolean canDecode(final ImageInputStream stream) throws JxlException {
        try (MemorySession scope = MemorySession.openShared()) {
            stream.mark();
            var jxlData = new byte[12];
            stream.read(jxlData);
            stream.reset();

            var data = MemorySegment.allocateNative(jxlData.length, scope);
            data.copyFrom(MemorySegment.ofArray(jxlData));

            var signature = JxlSignature.fromId(decode_h.JxlSignatureCheck(data, jxlData.length));
            return signature == JxlSignature.JXL_SIG_CODESTREAM || signature == JxlSignature.JXL_SIG_CONTAINER;
        } catch (IOException e) {
            throw new JxlException("Couldn't get stream content", e);
        }
    }

    public static String getLibVersion() {
        int versionInt = decode_h.JxlDecoderVersion();
        int major = versionInt / 1000000;
        int minor = (versionInt - major * 1000000) / 1000;
        int patch = versionInt - major * 1000000 - minor * 1000;
        return String.format("%d.%d.%d", major, minor, patch);
    }

    public static BasicInfo getBasicInfo(final ImageInputStream stream) throws JxlException {
        var dec = decode_h.JxlDecoderCreate(MemoryAddress.NULL);

        try {
            try (MemorySession scope = MemorySession.openShared()) {
                if (dec == null) {
                    throw new JxlException("JxlDecoderCreate failed");
                }

                decode_h.JxlDecoderSetKeepOrientation(dec, 1);

                if (JxlDecoderStatus.JXL_DEC_SUCCESS != JxlDecoderStatus.fromId(
                    decode_h.JxlDecoderSubscribeEvents(dec, JxlDecoderStatus.JXL_DEC_BASIC_INFO.intValue() | JxlDecoderStatus.JXL_DEC_COLOR_ENCODING.intValue()))) {
                    throw new JxlException("JxlDecoderSubscribeEvents failed");
                }

                var info = JxlBasicInfo.allocate(scope);

                stream.mark();
                var jxlData = byteArrayFromStream(stream);
                stream.reset();

                var data = MemorySegment.allocateNative(jxlData.length, scope);
                data.copyFrom(MemorySegment.ofArray(jxlData));
                decode_h.JxlDecoderSetInput(dec, data, jxlData.length);

                var format = JxlPixelFormat.allocate(scope);
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
                        var iccSize = SegmentAllocator.newNativeArena(scope).allocate(C_LONG, 0);
                        if (JxlDecoderStatus.JXL_DEC_SUCCESS != JxlDecoderStatus.fromId(
                            decode_h.JxlDecoderGetICCProfileSize(dec, format, JxlColorProfileTarget.JXL_COLOR_PROFILE_TARGET_DATA.intValue(), iccSize))) {
                            throw new JxlException("JxlDecoderGetICCProfileSize failed");
                        }

                        iccProfile = ByteBuffer.allocateDirect(iccSize.get(C_INT, 0));
                        if (JxlDecoderStatus.JXL_DEC_SUCCESS !=
                            JxlDecoderStatus.fromId(
                                decode_h.JxlDecoderGetColorAsICCProfile(dec, format, JxlColorProfileTarget.JXL_COLOR_PROFILE_TARGET_DATA.intValue(),
                                    MemorySegment.ofBuffer(iccProfile),
                                    iccSize.get(C_LONG, 0)))) {
                            throw new JxlException("JxlDecoderGetColorAsICCProfile failed");
                        }
                        break;
                    }
                }

                var iccData = new byte[iccProfile.remaining()];
                iccProfile.get(iccData);
                var icc = ICC_Profile.getInstance(iccData);
                var iccSpace = new ICC_ColorSpace(icc);

                return new BasicInfo(
                    JxlBasicInfo.xsize$get(info),
                    JxlBasicInfo.ysize$get(info),
                    JxlBasicInfo.alpha_bits$get(info) > 0,
                    JxlBasicInfo.have_animation$get(info) > 0,
                    JxlBasicInfo.num_color_channels$get(info),
                    iccSpace
                );

            } catch (IOException e) {
                throw new JxlException("Couldn't get stream content", e);
            }
        } finally {
            decode_h.JxlDecoderDestroy(dec);
        }
    }

    public static BufferedImage decode(final ImageInputStream stream, final BasicInfo info) throws JxlException {
        var dec = decode_h.JxlDecoderCreate(MemoryAddress.NULL);

        try {
            if (dec == null) {
                throw new JxlException("JxlDecoderCreate failed");
            }

            if (JxlDecoderStatus.JXL_DEC_SUCCESS !=
                JxlDecoderStatus.fromId(
                    decode_h.JxlDecoderSubscribeEvents(dec, JxlDecoderStatus.JXL_DEC_FULL_IMAGE.intValue()))) {
                throw new JxlException("JxlDecoderSubscribeEvents failed");
            }

            try (MemorySession scope = MemorySession.openShared()) {
                stream.mark();
                var jxlData = byteArrayFromStream(stream);
                stream.reset();

                var data = MemorySegment.allocateNative(jxlData.length, scope);
                data.copyFrom(MemorySegment.ofArray(jxlData));
                decode_h.JxlDecoderSetInput(dec, data, jxlData.length);

                var dataType = JxlDataType.JXL_TYPE_UINT8;
                var format = JxlPixelFormat.allocate(scope);
                JxlPixelFormat.num_channels$set(format, info.colorChannels() + (info.hasAlpha() ? 1 : 0));
                JxlPixelFormat.data_type$set(format, dataType.intValue());
                JxlPixelFormat.endianness$set(format, JxlEndianness.JXL_NATIVE_ENDIAN.intValue());
                JxlPixelFormat.align$set(format, 0);

                ByteBuffer pixels = ByteBuffer.allocate(0);

                while (true) {
                    var status = JxlDecoderStatus.fromId(decode_h.JxlDecoderProcessInput(dec));

                    if (status == JxlDecoderStatus.JXL_DEC_ERROR) {
                        throw new JxlException("Decoder error");
                    }
                    if (status == JxlDecoderStatus.JXL_DEC_NEED_MORE_INPUT) {
                        throw new JxlException("Error, already provided all input");
                    }

                    if (status == JxlDecoderStatus.JXL_DEC_NEED_IMAGE_OUT_BUFFER) {
                        var bufferSizeBytes = SegmentAllocator.newNativeArena(scope).allocate(C_LONG, 0);
                        if (JxlDecoderStatus.JXL_DEC_SUCCESS != JxlDecoderStatus.fromId(decode_h.JxlDecoderImageOutBufferSize(dec, format, bufferSizeBytes))) {
                            throw new JxlException("JxlDecoderImageOutBufferSize failed");
                        }

                        var bufferSizeBytesValue = bufferSizeBytes.get(C_INT, 0);
                        var bufferSizeExpected = info.width() * info.height() * JxlPixelFormat.num_channels$get(format) * dataType.sizeBytes();
                        if (bufferSizeBytesValue != bufferSizeExpected) {
                            throw new JxlException(
                                "Invalid out buffer size. Got: " + bufferSizeBytesValue + ", expected: " + bufferSizeExpected);
                        }

                        pixels = ByteBuffer.allocateDirect(bufferSizeBytesValue);
                        if (JxlDecoderStatus.JXL_DEC_SUCCESS !=
                            JxlDecoderStatus.fromId(decode_h.JxlDecoderSetImageOutBuffer(dec, format, MemorySegment.ofBuffer(pixels), bufferSizeBytes.get(C_LONG, 0)))) {
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

                var colorModel = new ComponentColorModel(info.iccSpace(), info.hasAlpha(), false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);
                var sampleModel = colorModel.createCompatibleSampleModel(info.width(), info.height());

                var pixelsArray = new byte[pixels.capacity()];
                pixels.get(pixelsArray);
                var db = new DataBufferByte(pixelsArray, info.width() * info.height());
                var raster = WritableRaster.createWritableRaster(sampleModel, db, null);
                return new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);
            } catch (IOException e) {
                throw new JxlException("Couldn't get stream content", e);
            }
        } finally {
            decode_h.JxlDecoderDestroy(dec);
        }
    }
}
