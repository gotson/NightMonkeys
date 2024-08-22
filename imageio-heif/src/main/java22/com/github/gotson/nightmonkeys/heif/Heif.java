package com.github.gotson.nightmonkeys.heif;

import com.github.gotson.nightmonkeys.heif.lib.HeifError;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifChroma;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifColorSpace;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifErrorCode;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifFiletypeResult;
import com.github.gotson.nightmonkeys.heif.lib.panama.heif_h;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageReadParam;
import javax.imageio.stream.ImageInputStream;
import java.awt.Rectangle;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteOrder;

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

    private static void checkError(MemorySegment segment) throws HeifException {
        check(HeifError.from(segment));
    }

    private static void check(HeifError error) throws HeifException {
        if (error.code() != HeifErrorCode.HEIF_ERROR_OK) {
            throw new HeifException(error.message());
        }
    }
}
