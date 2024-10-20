package com.github.gotson.nightmonkeys.webp;

import com.github.gotson.nightmonkeys.webp.imageio.plugins.WebpImageWriteParam;
import com.github.gotson.nightmonkeys.webp.lib.enums.VP8StatusCode;
import com.github.gotson.nightmonkeys.webp.lib.enums.WebPFeatureFlags;
import com.github.gotson.nightmonkeys.webp.lib.enums.WebPFormatFeature;
import com.github.gotson.nightmonkeys.webp.lib.enums.WebpLosslessPresets;
import com.github.gotson.nightmonkeys.webp.lib.panama.*;

import javax.imageio.ImageReadParam;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.color.ICC_Profile;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.github.gotson.nightmonkeys.common.imageio.IIOUtil.byteArrayFromStream;

/**
 * Java bindings for libwebp via Foreign Linker API *
 */
public final class WebP {

    public static final WebpLosslessPresets DEFAULT_LOSSLESS_LEVEL = WebpLosslessPresets.LEVEL_6;
    public static final float DEFAULT_LOSSY_QUALITY = 0.75F;
    public static final int DEFAULT_LOSSY_METHOD = 4;

    private static final int minDecoderAbi = Integer.parseInt("0200", 16);
    private static final int minDemuxAbi = Integer.parseInt("0100", 16);

    private WebP() {
        // Static helper class
    }

    public static boolean canDecode(final ImageInputStream stream) throws WebpException {
        try (var arena = Arena.ofConfined()) {
            stream.mark();
            var webpData = new byte[30];
            stream.read(webpData);
            stream.reset();

            var data = arena.allocate(webpData.length);
            data.copyFrom(MemorySegment.ofArray(webpData));

            return decode_h.WebPGetInfo(data, webpData.length, MemorySegment.NULL, MemorySegment.NULL) > 0;
        } catch (IOException e) {
            throw new WebpException("Couldn't get stream content", e);
        }
    }

    public static String getDecoderVersion() {
        return parseVersionInt(decode_h.WebPGetDecoderVersion());
    }

    public static String getEncoderVersion() {
        return parseVersionInt(encode_h.WebPGetEncoderVersion());
    }

    public static String getDemuxVersion() {
        return parseVersionInt(demux_h.WebPGetDemuxVersion());
    }

    private static String parseVersionInt(int version) {
        int major = (version >> 16) & 0xFF;
        int minor = (version >> 8) & 0xFF;
        int patch = version & 0xFF;
        return String.format("%d.%d.%d", major, minor, patch);
    }

    public static BasicInfo getBasicInfo(final ImageInputStream stream) throws WebpException {
        try (var arena = Arena.ofConfined()) {
            stream.mark();
            var webpData = byteArrayFromStream(stream);
            stream.reset();

            var data = arena.allocate(webpData.length);
            data.copyFrom(MemorySegment.ofArray(webpData));

            var demuxData = WebPData.allocate(arena);
            WebPData.bytes(demuxData, data);
            WebPData.size(demuxData, webpData.length);

            var demux = demux_h.WebPDemuxInternal(demuxData, 0, MemorySegment.NULL, minDemuxAbi);
            ICC_Profile iccProfile = null;
            if (!demux.equals(MemorySegment.NULL)) {
                var flags = WebPFeatureFlags.parseInt(demux_h.WebPDemuxGetI(demux, WebPFormatFeature.WEBP_FF_FORMAT_FLAGS.intValue()));

                int width = demux_h.WebPDemuxGetI(demux, WebPFormatFeature.WEBP_FF_CANVAS_WIDTH.intValue());
                int height = demux_h.WebPDemuxGetI(demux, WebPFormatFeature.WEBP_FF_CANVAS_HEIGHT.intValue());
                int frameCount = flags.contains(WebPFeatureFlags.ANIMATION_FLAG) ? demux_h.WebPDemuxGetI(demux, WebPFormatFeature.WEBP_FF_FRAME_COUNT.intValue()) : 1;

                var chunkIter = WebPChunkIterator.allocate(arena);

                if (flags.contains(WebPFeatureFlags.ICCP_FLAG)) {
                    demux_h.WebPDemuxGetChunk(demux, arena.allocateFrom("ICCP"), 1, chunkIter);
                    var chunkWebpData = WebPChunkIterator.chunk(chunkIter);
                    long size = WebPData.size(chunkWebpData);
                    var iccData = new byte[(int) size];
                    WebPData.bytes(chunkWebpData).asSlice(0, size).asByteBuffer().get(iccData);
                    iccProfile = ICC_Profile.getInstance(iccData);
                }
                demux_h.WebPDemuxReleaseChunkIterator(chunkIter);
                demux_h.WebPDemuxDelete(demux);

                return new BasicInfo(
                    width,
                    height,
                    flags.contains(WebPFeatureFlags.ALPHA_FLAG),
                    flags.contains(WebPFeatureFlags.ANIMATION_FLAG),
                    iccProfile,
                    frameCount
                );
            }

            throw new WebpException("Couldn't get basic information");
        } catch (IOException e) {
            throw new WebpException("Couldn't get stream content", e);
        }
    }

    public static void decode(final ImageInputStream stream, BasicInfo info, final WritableRaster raster, ImageReadParam param, int imageIndex) throws WebpException {
        if (info.hasAnimation()) {
            decodeAnim(stream, info, raster, param, imageIndex);
        } else {
            decodeVP8(stream, info, raster, param);
        }
    }

    // TODO: use incremental decoder and publish progress update
    public static void decodeVP8(final ImageInputStream stream, BasicInfo info, final WritableRaster raster, ImageReadParam param) throws WebpException {
        try (var arena = Arena.ofConfined()) {
            var config = WebPDecoderConfig.allocate(arena);
            if (decode_h.WebPInitDecoderConfigInternal(config, minDecoderAbi) == 0) {
                throw new WebpException("Could not init decoder config");
            }

            stream.mark();
            var webpData = byteArrayFromStream(stream);
            stream.reset();

            var data = arena.allocate(webpData.length);
            data.copyFrom(MemorySegment.ofArray(webpData));

            var features = WebPDecoderConfig.input(config);
            var statusCode = VP8StatusCode.fromId(decode_h.WebPGetFeaturesInternal(data, webpData.length, features, minDecoderAbi));
            if (statusCode != VP8StatusCode.VP8_STATUS_OK) {
                throw new WebpException("Couldn't get WebpFeatures: " + statusCode);
            }

            var options = WebPDecoderConfig.options(config);
            if (param != null) {
                if (param.getSourceRegion() != null) {
                    WebPDecoderOptions.use_cropping(options, 1);
                    WebPDecoderOptions.crop_height(options, param.getSourceRegion().height);
                    WebPDecoderOptions.crop_width(options, param.getSourceRegion().width);
                    WebPDecoderOptions.crop_left(options, param.getSourceRegion().x);
                    WebPDecoderOptions.crop_top(options, param.getSourceRegion().y);
                }
            }
            if (info.width() != raster.getWidth() || info.height() != raster.getHeight()) {
                WebPDecoderOptions.use_scaling(options, 1);
                WebPDecoderOptions.scaled_height(options, raster.getHeight());
                WebPDecoderOptions.scaled_width(options, raster.getWidth());
            }

            var output = WebPDecoderConfig.output(config);
            WebPDecBuffer.colorspace(output, decode_h.MODE_ARGB());
            WebPDecBuffer.width(output, raster.getWidth());
            WebPDecBuffer.height(output, raster.getHeight());

            var rgba = WebPDecBuffer.u(output);
            WebPRGBABuffer.stride(rgba, raster.getWidth() * 4);
            WebPRGBABuffer.size(rgba, (long) raster.getWidth() * raster.getHeight() * 4);

            statusCode = VP8StatusCode.fromId(decode_h.WebPDecode(data, webpData.length, config));
            if (statusCode != VP8StatusCode.VP8_STATUS_OK) {
                throw new WebpException("Couldn't decode: " + statusCode);
            }

            var pixelsArray = new int[raster.getWidth() * raster.getHeight()];
            WebPRGBABuffer.rgba(rgba).asSlice(0, (long) raster.getWidth() * raster.getHeight() * 4).asByteBuffer().asIntBuffer().get(pixelsArray);

            raster.setDataElements(0, 0, raster.getWidth(), raster.getHeight(), pixelsArray);

            decode_h.WebPFreeDecBuffer(output);
        } catch (IOException e) {
            throw new WebpException("Couldn't get stream content", e);
        }
    }

    public static void decodeAnim(final ImageInputStream stream, BasicInfo info, final WritableRaster raster, ImageReadParam param, int imageIndex) throws WebpException {
        try (var arena = Arena.ofConfined()) {
            stream.mark();
            var webpData = byteArrayFromStream(stream);
            stream.reset();

            var data = arena.allocate(webpData.length);
            data.copyFrom(MemorySegment.ofArray(webpData));

            var demuxData = WebPData.allocate(arena);
            WebPData.bytes(demuxData, data);
            WebPData.size(demuxData, webpData.length);

            var dec_options = WebPAnimDecoderOptions.allocate(arena);
            demux_h.WebPAnimDecoderOptionsInitInternal(dec_options, minDemuxAbi);
            WebPAnimDecoderOptions.color_mode(dec_options, demux_h.MODE_RGBA());

            var dec = demux_h.WebPAnimDecoderNewInternal(demuxData, dec_options, minDemuxAbi);
            var anim_info = WebPAnimInfo.allocate(arena);
            demux_h.WebPAnimDecoderGetInfo(dec, anim_info);

            int i = 0;
            var pixelsArray = new int[info.width() * info.height()];
            do {
                var buf = arena.allocate(demux_h.C_POINTER);
                var timestamp = arena.allocate(demux_h.C_INT);
                demux_h.WebPAnimDecoderGetNext(dec, buf, timestamp);
                if (i == imageIndex) {
                    buf.get(demux_h.C_POINTER, 0).asSlice(0, (long) info.width() * info.height() * 4).asByteBuffer().asIntBuffer().get(pixelsArray);
                    break;
                }
                i++;
            } while (i <= imageIndex && demux_h.WebPAnimDecoderHasMoreFrames(dec) > 0);
            demux_h.WebPAnimDecoderDelete(dec);

            raster.setDataElements(0, 0, raster.getWidth(), raster.getHeight(), pixelsArray);

        } catch (IOException e) {
            throw new WebpException("Couldn't get stream content", e);
        }
    }

    /**
     * Encode a raster image to a stream.
     *
     * @param stream the output stream.
     * @param raster the image raster.
     * @param param the encoding parameters.
     * @param progressCallback the progress callback.
     * @throws WebpException if the encoding fails.
     */
    public static void encode(final ImageOutputStream stream, final Raster raster, WebpImageWriteParam param, ProgressCallback progressCallback) throws WebpException {
        // TODO add animation support
        encodeVP8(stream, raster, param, progressCallback);
    }

    /**
     * Encode a VP8 image.
     *
     * @param stream the output stream.
     * @param raster the image raster.
     * @param param the encoding parameters.
     * @param progressCallback the progress callback.
     * @throws WebpException if the encoding fails.
     */
    public static void encodeVP8(final ImageOutputStream stream, final Raster raster, final WebpImageWriteParam param, ProgressCallback progressCallback) throws WebpException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment config = WebPConfig.allocate(arena);
            if (encode_h.WebPConfigInitInternal(config, param.getPreset().ordinal(), param.getCompressionQuality() * 100F, encode_h.WEBP_ENCODER_ABI_VERSION()) == 0) {
                throw new WebpException("Could not init encoder config");
            }
            WebPConfig.method(config, param.getMethod());
            WebPConfig.lossless(config, param.isCompressionLossless() ? 1 : 0);

            int width = raster.getWidth();
            int height = raster.getHeight();
            int numBands = raster.getNumBands();

            MemorySegment picture = WebPPicture.allocate(arena);
            WebPPicture.width(picture, width);
            WebPPicture.height(picture, height);
            WebPPicture.use_argb(picture, 1);

            int[] intPixelArray = raster.getPixels(0, 0, width, height, (int[]) null);
            byte[] pixelArray = new byte[intPixelArray.length];
            for (int i = 0; i < intPixelArray.length; i++) {
                pixelArray[i] = (byte) intPixelArray[i];
            }
            MemorySegment data = arena.allocateFrom(encode_h.C_CHAR, pixelArray);

            if ((numBands > 3 ? encode_h.WebPPictureImportRGBA(picture, data, width * numBands) : encode_h.WebPPictureImportRGB(picture, data, width * numBands)) == 0) {
                throw new WebpException("Could not import picture");
            }

            MemorySegment writer = WebPMemoryWriter.allocate(arena);
            encode_h.WebPMemoryWriterInit(writer);
            WebPPicture.writer(picture, WebPWriterFunction.allocate(encode_h::WebPMemoryWrite, arena));
            WebPPicture.custom_ptr(picture, writer);

            MemorySegment progressHook = WebPProgressHook.allocate((percent, _) -> progressCallback.onProgress(percent / 100F) ? 1 : 0, arena);
            WebPPicture.progress_hook(picture, progressHook);

            if (encode_h.WebPEncode(config, picture) == 0) {
                throw new WebpException("Couldn't encode: " + VP8StatusCode.fromId(WebPPicture.error_code(picture)));
            }

            byte[] bytes = new byte[(int) WebPMemoryWriter.size(writer)];
            WebPMemoryWriter.mem(writer).asSlice(0, bytes.length).asByteBuffer().get(bytes);
            stream.write(bytes);

            encode_h.WebPPictureFree(picture);
        } catch (IOException e) {
            throw new WebpException("Couldn't write to stream", e);
        }
    }
}
