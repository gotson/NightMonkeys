package com.github.gotson.nightmonkeys.heif;

import com.github.gotson.nightmonkeys.heif.imageio.plugins.HeifWriteParam;
import com.github.gotson.nightmonkeys.heif.lib.HeifError;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifChannel;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifChroma;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifColorSpace;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifCompressionFormat;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifErrorCode;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifFiletypeResult;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifSuberrorCode;
import com.github.gotson.nightmonkeys.heif.lib.panama.heif_error;
import com.github.gotson.nightmonkeys.heif.lib.panama.heif_h;
import com.github.gotson.nightmonkeys.heif.lib.panama.heif_writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageWriteParam;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;

import static com.github.gotson.nightmonkeys.common.imageio.IIOUtil.byteArrayFromStream;
import static com.github.gotson.nightmonkeys.heif.lib.panama.heif_h.C_INT;
import static com.github.gotson.nightmonkeys.heif.lib.panama.heif_h.C_POINTER;
import static com.github.gotson.nightmonkeys.heif.lib.panama.heif_h.heif_channel_interleaved;
import static com.github.gotson.nightmonkeys.heif.lib.panama.heif_h.heif_get_version;

/**
 * Java bindings for libheif via Foreign Linker API
 */
public class Heif {
    private static final Logger LOGGER = LoggerFactory.getLogger(Heif.class);

    private static final ValueLayout.OfInt pixelLayout = C_INT.withOrder(ByteOrder.BIG_ENDIAN);

    private static Collection<HeifEncoder> encoders;

    public static boolean isLibVersionSupported() {
        // heif_get_version_number_minor() does not return the correct value
        int versionBcd = heif_h.heif_get_version_number();
        int major = (versionBcd >> 24) & 0xFF;
        int minor = (versionBcd >> 16) & 0xFF;
        if (major != 1) return false;
        return minor >= 16;
    }

    public static String getLibVersion() {
        return heif_get_version().getString(0);
    }

    public static boolean canDecode(final ImageInputStream stream) throws HeifException {
        try (var arena = Arena.ofConfined()) {
            stream.mark();
            int len = 12;
            var heifData = new byte[len];
            stream.read(heifData);
            stream.reset();

            var data = arena.allocate(heifData.length);
            data.copyFrom(MemorySegment.ofArray(heifData));

            HeifFiletypeResult result = HeifFiletypeResult.fromId(heif_h.heif_check_filetype(data, len));
            // libheif will return YES_UNSUPPORTED for Animations, but can still decode the first image
            // libheif will return MAYBE for some images, but can still decode
            return result == HeifFiletypeResult.HEIF_FILETYPE_YES_SUPPORTED
                   || result == HeifFiletypeResult.HEIF_FILETYPE_YES_UNSUPPORTED
                   || result == HeifFiletypeResult.HEIF_FILETYPE_MAYBE;
        } catch (IOException e) {
            throw new HeifException("Couldn't get stream content", e);
        }
    }

    public static BasicInfo getBasicInfo(final ImageInputStream stream) throws HeifException {
        try (var arena = Arena.ofConfined()) {
            stream.mark();
            var heifData = byteArrayFromStream(stream);
            stream.reset();

            var data = arena.allocate(heifData.length);
            data.copyFrom(MemorySegment.ofArray(heifData));

            MemorySegment heifContext = null;
            MemorySegment handle = null;
            try {
                heif_h.heif_init(arena, MemorySegment.NULL);
                heifContext = heif_h.heif_context_alloc();
                checkError(heif_h.heif_context_read_from_memory_without_copy(arena, heifContext, data, heifData.length, MemorySegment.NULL));
                var frameCount = heif_h.heif_context_get_number_of_top_level_images(heifContext);
                var handlePtr = arena.allocate(C_POINTER);
                checkError(heif_h.heif_context_get_primary_image_handle(arena, heifContext, handlePtr));
                handle = handlePtr.get(C_POINTER, 0);
                var width = heif_h.heif_image_handle_get_width(handle);
                var height = heif_h.heif_image_handle_get_height(handle);
                var hasAlpha = heif_h.heif_image_handle_has_alpha_channel(handle) > 0;

                return new BasicInfo(width, height, hasAlpha, null, frameCount);
            } finally {
                if (heifContext != null) heif_h.heif_context_free(heifContext);
                if (handle != null) heif_h.heif_image_handle_release(handle);
                heif_h.heif_deinit();
            }
        } catch (IOException e) {
            throw new HeifException("Couldn't get stream content", e);
        }
    }

    public static void decode(final ImageInputStream stream, BasicInfo info, final WritableRaster raster, ImageReadParam param, int imageIndex) throws HeifException {
        try (var arena = Arena.ofConfined()) {
            stream.mark();
            var heifData = byteArrayFromStream(stream);
            stream.reset();

            var data = arena.allocate(heifData.length);
            data.copyFrom(MemorySegment.ofArray(heifData));

            MemorySegment heifContext = null;
            MemorySegment handle = null;
            MemorySegment image = null;
            try {
                heif_h.heif_init(arena, MemorySegment.NULL);
                heifContext = heif_h.heif_context_alloc();
                checkError(heif_h.heif_context_read_from_memory_without_copy(arena, heifContext, data, heifData.length, MemorySegment.NULL));

                var imageIds = arena.allocate(C_INT, info.frameCount());
                heif_h.heif_context_get_list_of_top_level_image_IDs(heifContext, imageIds, info.frameCount());
                var imageId = imageIds.get(C_INT, imageIndex * C_INT.byteSize());

                var handlePtr = arena.allocate(C_POINTER);
                checkError(heif_h.heif_context_get_image_handle(arena, heifContext, imageId, handlePtr));
                handle = handlePtr.get(C_POINTER, 0);
                var width = heif_h.heif_image_handle_get_width(handle);
                var height = heif_h.heif_image_handle_get_height(handle);

                var imagePtr = arena.allocate(C_POINTER);
                checkError(
                    heif_h.heif_decode_image(arena, handle, imagePtr, HeifColorSpace.HEIF_COLOR_SPACE_RGB.intValue(), HeifChroma.HEIF_CHROMA_INTERLEAVED_RGBA.intValue(),
                        MemorySegment.NULL));
                image = imagePtr.get(C_POINTER, 0);

                var stridePtr = arena.allocate(C_INT);
                var pixels = heif_h.heif_image_get_plane_readonly(image, heif_channel_interleaved(), stridePtr);
                var stride = stridePtr.get(C_INT, 0);
                var pixelsRaster = new int[Math.min(width, raster.getWidth()) * Math.min(height, raster.getHeight())];

                var sourceRegion = param != null ? param.getSourceRegion() : null;
                if (sourceRegion == null) sourceRegion = new Rectangle(0, 0, width, height);
                var ssX = param != null ? param.getSourceXSubsampling() : 1;
                var ssY = param != null ? param.getSourceYSubsampling() : 1;
                var ssOffX = param != null ? param.getSubsamplingXOffset() : 0;
                var ssOffY = param != null ? param.getSubsamplingYOffset() : 0;

                var offset = 0;
                for (int row = sourceRegion.y + ssOffY; row < sourceRegion.y + sourceRegion.height; row += ssY) {
                    if (offset >= pixelsRaster.length) break;
                    for (int col = sourceRegion.x + ssOffX; col < sourceRegion.x + sourceRegion.width; col += ssX) {
                        if (offset >= pixelsRaster.length) break;
                        int pixel = pixels.get(pixelLayout, (long) row * stride + (col * pixelLayout.byteSize()));
                        pixelsRaster[offset++] = pixel;
                    }
                }

                if (param != null && param.getDestinationOffset() != null) {
                    raster.setDataElements(param.getDestinationOffset().x, param.getDestinationOffset().y, raster.getWidth() - param.getDestinationOffset().x,
                        raster.getHeight() - param.getDestinationOffset().y, pixelsRaster);
                } else {
                    raster.setDataElements(0, 0, raster.getWidth(), raster.getHeight(), pixelsRaster);
                }
            } finally {
                if (image != null) heif_h.heif_image_release(image);
                if (heifContext != null) heif_h.heif_context_free(heifContext);
                if (handle != null) heif_h.heif_image_handle_release(handle);
                heif_h.heif_deinit();
            }

        } catch (IOException e) {
            throw new HeifException("Couldn't get stream content", e);
        }
    }

    public static void encode(final ImageOutputStream stream, IIOImage image, ImageWriteParam param) throws HeifException {
        if (param == null) param = new HeifWriteParam(null);
        HeifCompressionFormat compressionFormat = HeifCompressionFormat.HEIF_COMPRESSION_UNDEFINED;
        if (param.getCompressionMode() == ImageWriteParam.MODE_EXPLICIT) {
            compressionFormat = HeifCompressionFormat.valueOf(param.getCompressionType());
        }

        try (var arena = Arena.ofConfined()) {
            MemorySegment heifContext = null;
            MemorySegment heifEncoder = null;
            MemorySegment heifImage = null;

            try {
                var tile = image.getRenderedImage().getTile(0, 0);
                if (tile.getTransferType() != DataBuffer.TYPE_INT) {
                    throw new UnsupportedOperationException("TransferType is not INT");
                }

                heif_h.heif_init(arena, MemorySegment.NULL);
                heifContext = heif_h.heif_context_alloc();
                var heifEncoderPtr = arena.allocate(C_POINTER);
                checkError(heif_h.heif_context_get_encoder_for_format(arena, heifContext, compressionFormat.intValue(), heifEncoderPtr));
                heifEncoder = heifEncoderPtr.get(C_POINTER, 0);

                if (param.isCompressionLossless()) {
                    checkError(heif_h.heif_encoder_set_lossless(arena, heifEncoder, 1));
                } else {
                    checkError(heif_h.heif_encoder_set_lossy_quality(arena, heifEncoder, (int) (param.getCompressionQuality() * 100)));
                }

                ColorModel colorModel = image.getRenderedImage().getColorModel();

                var sourceRegion = param.getSourceRegion();
                if (sourceRegion == null) sourceRegion = new Rectangle(0, 0, image.getRenderedImage().getWidth(), image.getRenderedImage().getHeight());
                var ssX = param.getSourceXSubsampling();
                var ssY = param.getSourceYSubsampling();
                var ssOffX = param.getSubsamplingXOffset();
                var ssOffY = param.getSubsamplingYOffset();

                // the heif image we need to write our source data to, before we can encode it
                var heifImagePtr = arena.allocate(C_POINTER);
                checkError(heif_h.heif_image_create(arena, sourceRegion.width, sourceRegion.height, HeifColorSpace.HEIF_COLOR_SPACE_RGB.intValue(),
                    HeifChroma.HEIF_CHROMA_INTERLEAVED_RGBA.intValue(), heifImagePtr));
                heifImage = heifImagePtr.get(C_POINTER, 0);
                checkError(heif_h.heif_image_add_plane(arena, heifImage, HeifChannel.HEIF_CHANNEL_INTERLEAVED.intValue(), sourceRegion.width, sourceRegion.height, 32));

                var stridePtr = arena.allocate(C_INT);
                var heifPixels = heif_h.heif_image_get_plane(heifImage, heif_channel_interleaved(), stridePtr);
                var heifStride = stridePtr.get(C_INT, 0);

                int[] data =
                    (int[]) tile.getSampleModel().getDataElements(sourceRegion.x, sourceRegion.y, sourceRegion.width, sourceRegion.height, null, tile.getDataBuffer());


                var offset = 0;
                for (int row = sourceRegion.y + ssOffY; row < sourceRegion.y + sourceRegion.height; row += ssY) {
                    if (offset >= data.length) break;
                    for (int col = sourceRegion.x + ssOffX; col < sourceRegion.x + sourceRegion.width; col += ssX) {
                        if (offset >= data.length) break;
                        int pixel = data[offset];

                        // get the pixels from the color model, so we always get the correct value, whatever the source mask
                        var a = colorModel.getAlpha(pixel);
                        var r = colorModel.getRed(pixel);
                        var g = colorModel.getGreen(pixel);
                        var b = colorModel.getBlue(pixel);
                        // repack in RGBA for HEIF
                        int repack = (r << 24) | (g << 16) | (b << 8) | (a);

                        heifPixels.set(pixelLayout, (long) row * heifStride + (col * pixelLayout.byteSize()), repack);

                        offset++;
                    }
                }

                // encode the data we provided from the source Java image
                checkError(heif_h.heif_context_encode_image(arena, heifContext, heifImage, heifEncoder, MemorySegment.NULL, MemorySegment.NULL));

                // the writer callback is called by heif_writer.write to write somewhere else than a file
                var writerCallback = new heif_writer.write.Function() {
                    @Override
                    public MemorySegment apply(MemorySegment ctx, MemorySegment data, long size, MemorySegment userData) {
                        var error = heif_error.allocate(arena);
                        heif_error.subcode(error, HeifSuberrorCode.HEIF_SUBERROR_UNSPECIFIED.intValue());
                        heif_error.message(error, arena.allocateFrom("success"));
                        try {
                            var slice = data.asSlice(0, size);
                            stream.write(slice.toArray(ValueLayout.JAVA_BYTE), 0, (int) size);

                            heif_error.code(error, HeifErrorCode.HEIF_ERROR_OK.intValue());
                        } catch (Exception e) {
                            heif_error.code(error, HeifErrorCode.HEIF_ERROR_USAGE_ERROR.intValue());
                            heif_error.message(error, arena.allocateFrom("Error while writing to ImageOutputStream"));
                        }
                        return error;
                    }
                };

                var writer = heif_writer.allocate(arena);
                heif_writer.writer_api_version(writer, 1);
                heif_writer.write(writer, heif_writer.write.allocate(writerCallback, arena));

                checkError(heif_h.heif_context_write(arena, heifContext, writer, MemorySegment.NULL));
            } finally {
                if (heifImage != null) heif_h.heif_image_release(heifImage);
                if (heifEncoder != null) heif_h.heif_encoder_release(heifEncoder);
                if (heifContext != null) heif_h.heif_context_free(heifContext);
                heif_h.heif_deinit();
            }
        } catch (Exception e) {
            throw new HeifException("Couldn't encode", e);
        }
    }

    public static Collection<HeifEncoder> getAvailableEncoders() throws HeifException {
        if (encoders == null) {
            synchronized (Heif.class) {
                encoders = new ArrayList<>();
                try (var arena = Arena.ofConfined()) {
                    heif_h.heif_init(arena, MemorySegment.NULL);
                    int maxEncoders = 20;
                    var encoderDescriptorPtr = arena.allocate(C_POINTER, maxEncoders);
                    int encoderCount =
                        heif_h.heif_get_encoder_descriptors(HeifCompressionFormat.HEIF_COMPRESSION_UNDEFINED.intValue(), MemorySegment.NULL, encoderDescriptorPtr,
                            maxEncoders);
                    System.out.println("HEIF ENCODERS:" + encoderCount);
                    for (int i = 0; i < encoderCount; i++) {
                        var encoderDescriptor = encoderDescriptorPtr.get(C_POINTER, i * C_POINTER.byteSize());
                        var name = heif_h.heif_encoder_descriptor_get_name(encoderDescriptor).getString(0);
                        var id = heif_h.heif_encoder_descriptor_get_id_name(encoderDescriptor).getString(0);
                        var format = HeifCompressionFormat.fromId(heif_h.heif_encoder_descriptor_get_compression_format(encoderDescriptor));
                        boolean supportsLossless = heif_h.heif_encoder_descriptor_supports_lossless_compression(encoderDescriptor) > 0;
                        boolean supportsLossy = heif_h.heif_encoder_descriptor_supports_lossy_compression(encoderDescriptor) > 0;

                        if (format != null) encoders.add(new HeifEncoder(name, id, format, supportsLossless, supportsLossy));
                    }
                } finally {
                    heif_h.heif_deinit();
                }
            }
        }
        return encoders;
    }

    private static void checkError(MemorySegment segment) throws HeifException {
        check(HeifError.from(segment));
    }

    private static void check(HeifError error) throws HeifException {
        if (error.code() != HeifErrorCode.HEIF_ERROR_OK) {
            throw new HeifException(error.message());
        }
    }
}
