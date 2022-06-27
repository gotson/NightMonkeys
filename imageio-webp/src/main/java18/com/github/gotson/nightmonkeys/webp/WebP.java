package com.github.gotson.nightmonkeys.webp;

import com.github.gotson.nightmonkeys.webp.lib.enums.VP8StatusCode;
import com.github.gotson.nightmonkeys.webp.lib.enums.WebPFeatureFlags;
import com.github.gotson.nightmonkeys.webp.lib.enums.WebPFormatFeature;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPAnimDecoderOptions;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPAnimInfo;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPChunkIterator;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPData;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPDecBuffer;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPDecoderConfig;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPDecoderOptions;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPRGBABuffer;
import com.github.gotson.nightmonkeys.webp.lib.panama.demux_h;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;
import jdk.incubator.foreign.SegmentAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageReadParam;
import javax.imageio.stream.ImageInputStream;
import java.awt.color.ICC_Profile;
import java.awt.image.WritableRaster;
import java.io.IOException;

import static com.github.gotson.nightmonkeys.common.imageio.IIOUtil.byteArrayFromStream;
import static com.github.gotson.nightmonkeys.webp.lib.panama.demux_h.C_INT;
import static com.github.gotson.nightmonkeys.webp.lib.panama.demux_h.C_POINTER;

/**
 * Java bindings for libwebp via Foreign Linker API *
 */
public class WebP {
    private static final int minDecoderAbi = Integer.parseInt("0200", 16);
    private static final int minDemuxAbi = Integer.parseInt("0100", 16);

    private static final Logger LOGGER = LoggerFactory.getLogger(WebP.class);

    public static boolean canDecode(final ImageInputStream stream) throws WebpException {
        try (ResourceScope scope = ResourceScope.newSharedScope()) {
            stream.mark();
            var webpData = new byte[30];
            stream.read(webpData);
            stream.reset();

            var data = MemorySegment.allocateNative(webpData.length, scope);
            data.copyFrom(MemorySegment.ofArray(webpData));

            return demux_h.WebPGetInfo(data, webpData.length, MemoryAddress.NULL, MemoryAddress.NULL) > 0;
        } catch (IOException e) {
            throw new WebpException("Couldn't get stream content", e);
        }
    }

    public static String getDecoderVersion() {
        return parseVersionInt(demux_h.WebPGetDecoderVersion());
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
        try (ResourceScope scope = ResourceScope.newSharedScope()) {
            stream.mark();
            var webpData = byteArrayFromStream(stream);
            stream.reset();

            var data = MemorySegment.allocateNative(webpData.length, scope);
            data.copyFrom(MemorySegment.ofArray(webpData));

            var demuxData = WebPData.allocate(scope);
            WebPData.bytes$set(demuxData, data.address());
            WebPData.size$set(demuxData, webpData.length);

            var demux = demux_h.WebPDemuxInternal(demuxData, 0, MemoryAddress.NULL, minDemuxAbi);
            ICC_Profile iccProfile = null;
            if (!demux.equals(MemoryAddress.NULL)) {
                var flags = WebPFeatureFlags.parseInt(demux_h.WebPDemuxGetI(demux, WebPFormatFeature.WEBP_FF_FORMAT_FLAGS.intValue()));

                int width = demux_h.WebPDemuxGetI(demux, WebPFormatFeature.WEBP_FF_CANVAS_WIDTH.intValue());
                int height = demux_h.WebPDemuxGetI(demux, WebPFormatFeature.WEBP_FF_CANVAS_HEIGHT.intValue());
                int frameCount = flags.contains(WebPFeatureFlags.ANIMATION_FLAG) ? demux_h.WebPDemuxGetI(demux, WebPFormatFeature.WEBP_FF_FRAME_COUNT.intValue()) : 1;

                var chunkIter = WebPChunkIterator.allocate(scope);

                if (flags.contains(WebPFeatureFlags.ICCP_FLAG)) {
                    demux_h.WebPDemuxGetChunk(demux, SegmentAllocator.nativeAllocator(scope).allocateUtf8String("ICCP"), 1, chunkIter);
                    var chunkWebpData = WebPChunkIterator.chunk$slice(chunkIter);
                    long size = WebPData.size$get(chunkWebpData);
                    var iccData = new byte[(int) size];
                    var chunkData = MemorySegment.ofAddress(WebPData.bytes$get(chunkWebpData), size, scope).asByteBuffer();
                    chunkData.get(iccData);
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
        try (ResourceScope scope = ResourceScope.newSharedScope()) {
            var config = WebPDecoderConfig.allocate(scope);
            if (demux_h.WebPInitDecoderConfigInternal(config, minDecoderAbi) == 0) {
                throw new WebpException("Could not init decoder config");
            }

            stream.mark();
            var webpData = byteArrayFromStream(stream);
            stream.reset();

            var data = MemorySegment.allocateNative(webpData.length, scope);
            data.copyFrom(MemorySegment.ofArray(webpData));

            var features = WebPDecoderConfig.input$slice(config);
            var statusCode = VP8StatusCode.fromId(demux_h.WebPGetFeaturesInternal(data, webpData.length, features, minDecoderAbi));
            if (statusCode != VP8StatusCode.VP8_STATUS_OK) {
                throw new WebpException("Couldn't get WebpFeatures: " + statusCode);
            }

            var options = WebPDecoderConfig.options$slice(config);
            if (param != null) {
                if (param.getSourceRegion() != null) {
                    WebPDecoderOptions.use_cropping$set(options, 1);
                    WebPDecoderOptions.crop_height$set(options, param.getSourceRegion().height);
                    WebPDecoderOptions.crop_width$set(options, param.getSourceRegion().width);
                    WebPDecoderOptions.crop_left$set(options, param.getSourceRegion().x);
                    WebPDecoderOptions.crop_top$set(options, param.getSourceRegion().y);
                }
            }
            if (info.width() != raster.getWidth() || info.height() != raster.getHeight()) {
                WebPDecoderOptions.use_scaling$set(options, 1);
                WebPDecoderOptions.scaled_height$set(options, raster.getHeight());
                WebPDecoderOptions.scaled_width$set(options, raster.getWidth());
            }

            var output = WebPDecoderConfig.output$slice(config);
            WebPDecBuffer.colorspace$set(output, demux_h.MODE_ARGB());
            WebPDecBuffer.width$set(output, raster.getWidth());
            WebPDecBuffer.height$set(output, raster.getHeight());

            var rgba = WebPDecBuffer.u$slice(output);
            WebPRGBABuffer.stride$set(rgba, raster.getWidth() * 4);
            WebPRGBABuffer.size$set(rgba, (long) raster.getWidth() * raster.getHeight() * 4);

            statusCode = VP8StatusCode.fromId(demux_h.WebPDecode(data, webpData.length, config));
            if (statusCode != VP8StatusCode.VP8_STATUS_OK) {
                throw new WebpException("Couldn't decode: " + statusCode);
            }

            var pixels = MemorySegment.ofAddress(WebPRGBABuffer.rgba$get(rgba), (long) raster.getWidth() * raster.getHeight() * 4, scope).asByteBuffer().asIntBuffer();
            var pixelsArray = new int[raster.getWidth() * raster.getHeight()];
            pixels.get(pixelsArray);

            raster.setDataElements(0, 0, raster.getWidth(), raster.getHeight(), pixelsArray);

            demux_h.WebPFreeDecBuffer(output);
        } catch (IOException e) {
            throw new WebpException("Couldn't get stream content", e);
        }
    }

    public static void decodeAnim(final ImageInputStream stream, BasicInfo info, final WritableRaster raster, ImageReadParam param, int imageIndex) throws WebpException {
        try (ResourceScope scope = ResourceScope.newSharedScope()) {
            stream.mark();
            var webpData = byteArrayFromStream(stream);
            stream.reset();

            var data = MemorySegment.allocateNative(webpData.length, scope);
            data.copyFrom(MemorySegment.ofArray(webpData));

            var demuxData = WebPData.allocate(scope);
            WebPData.bytes$set(demuxData, data.address());
            WebPData.size$set(demuxData, webpData.length);

            var dec_options = WebPAnimDecoderOptions.allocate(scope);
            demux_h.WebPAnimDecoderOptionsInitInternal(dec_options, minDemuxAbi);
            WebPAnimDecoderOptions.color_mode$set(dec_options, demux_h.MODE_RGBA());

            var dec = demux_h.WebPAnimDecoderNewInternal(demuxData, dec_options, minDemuxAbi);
            var anim_info = WebPAnimInfo.allocate(scope);
            demux_h.WebPAnimDecoderGetInfo(dec, anim_info);

            int i = 0;
            var pixelsArray = new int[info.width() * info.height()];
            do {
                var buf = MemorySegment.allocateNative(C_POINTER, scope);
                var timestamp = MemorySegment.allocateNative(C_INT, scope);
                demux_h.WebPAnimDecoderGetNext(dec, buf, timestamp);
                if (i == imageIndex) {
                    MemorySegment.ofAddress(
                        buf.get(C_POINTER, 0),
                        (long) info.width() * info.height() * 4,
                        scope
                    ).asByteBuffer().asIntBuffer().get(pixelsArray);
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
}
