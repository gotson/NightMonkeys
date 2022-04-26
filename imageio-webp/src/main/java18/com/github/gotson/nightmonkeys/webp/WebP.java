package com.github.gotson.nightmonkeys.webp;

import com.github.gotson.nightmonkeys.webp.lib.enums.VP8StatusCode;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPBitstreamFeatures;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPDecBuffer;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPDecoderConfig;
import com.github.gotson.nightmonkeys.webp.lib.panama.WebPRGBABuffer;
import com.github.gotson.nightmonkeys.webp.lib.panama.decode_h;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;

import static com.github.gotson.nightmonkeys.common.imageio.IIOUtil.byteArrayFromStream;

/**
 * Java bindings for libwebp via Foreign Linker API *
 */
public class WebP {
    private static final int minDecoderAbi = Integer.parseInt("0200", 16);

    private static final Logger LOG = LoggerFactory.getLogger(WebP.class);

    public static boolean canDecode(final ImageInputStream stream) throws WebpException {
        try (ResourceScope scope = ResourceScope.newSharedScope()) {
            stream.mark();
            var webpData = new byte[30];
            stream.read(webpData);
            stream.reset();

            var data = MemorySegment.allocateNative(webpData.length, scope);
            data.copyFrom(MemorySegment.ofArray(webpData));

            return decode_h.WebPGetInfo(data, webpData.length, MemoryAddress.NULL, MemoryAddress.NULL) > 0;
        } catch (IOException e) {
            throw new WebpException("Couldn't get stream content", e);
        }
    }

    public static String getLibVersion() {
        int versionInt = decode_h.WebPGetDecoderVersion();
        int major = (versionInt >> 16) & 0xFF;
        int minor = (versionInt >> 8) & 0xFF;
        int patch = versionInt & 0xFF;
        return String.format("%d.%d.%d", major, minor, patch);
    }

    public static BasicInfo getBasicInfo(final ImageInputStream stream) throws WebpException {
        try (ResourceScope scope = ResourceScope.newSharedScope()) {
            stream.mark();
            var webpData = new byte[30];
            stream.read(webpData);
            stream.reset();

            var data = MemorySegment.allocateNative(webpData.length, scope);
            data.copyFrom(MemorySegment.ofArray(webpData));

            var features = WebPBitstreamFeatures.allocate(scope);
            var statusCode = VP8StatusCode.fromId(decode_h.WebPGetFeaturesInternal(data, webpData.length, features, minDecoderAbi));

            if (statusCode == VP8StatusCode.VP8_STATUS_OK) {
                return new BasicInfo(
                    WebPBitstreamFeatures.width$get(features),
                    WebPBitstreamFeatures.height$get(features),
                    WebPBitstreamFeatures.has_alpha$get(features) > 0,
                    WebPBitstreamFeatures.has_animation$get(features) > 0,
                    switch (WebPBitstreamFeatures.format$get(features)) {
                        case 2 -> BasicInfo.Format.LOSSLESS;
                        case 1 -> BasicInfo.Format.LOSSY;
                        default -> BasicInfo.Format.UNDEFINED_OR_MIXED;
                    }
                );
            }
            throw new WebpException("Couldn't get WebpFeatures: " + statusCode);
        } catch (IOException e) {
            throw new WebpException("Couldn't get stream content", e);
        }
    }

    // TODO: use incremental decoder and publish progress update
    public static void decode(final ImageInputStream stream, final BasicInfo info, final WritableRaster raster) throws WebpException {
        try (ResourceScope scope = ResourceScope.newSharedScope()) {
            var config = WebPDecoderConfig.allocate(scope);
            if (decode_h.WebPInitDecoderConfigInternal(config, minDecoderAbi) == 0) {
                throw new WebpException("Could not init decoder config");
            }

            stream.mark();
            var webpData = byteArrayFromStream(stream);
            stream.reset();

            var data = MemorySegment.allocateNative(webpData.length, scope);
            data.copyFrom(MemorySegment.ofArray(webpData));

            var features = WebPDecoderConfig.input$slice(config);
            var statusCode = VP8StatusCode.fromId(decode_h.WebPGetFeaturesInternal(data, webpData.length, features, minDecoderAbi));
            if (statusCode != VP8StatusCode.VP8_STATUS_OK) {
                throw new WebpException("Couldn't get WebpFeatures: " + statusCode);
            }

            var output = WebPDecoderConfig.output$slice(config);
            WebPDecBuffer.colorspace$set(output, decode_h.MODE_ARGB());
            WebPDecBuffer.width$set(output, info.width());
            WebPDecBuffer.height$set(output, info.height());

            var rgba = WebPDecBuffer.u$slice(output);
            WebPRGBABuffer.stride$set(rgba, info.width() * 4);
            WebPRGBABuffer.size$set(rgba, (long) info.width() * info.height() * 4);

            statusCode = VP8StatusCode.fromId(decode_h.WebPDecode(data, webpData.length, config));
            if (statusCode != VP8StatusCode.VP8_STATUS_OK) {
                throw new WebpException("Couldn't decode: " + statusCode);
            }

            var pixels = MemorySegment.ofAddress(WebPRGBABuffer.rgba$get(rgba), (long) info.width() * info.height() * 4, scope).asByteBuffer().asIntBuffer();
            var pixelsArray = new int[info.width() * info.height()];
            pixels.get(pixelsArray);

            raster.setDataElements(0, 0, info.width(), info.height(), pixelsArray);

            decode_h.WebPFreeDecBuffer(output);
        } catch (IOException e) {
            throw new WebpException("Couldn't get stream content", e);
        }
    }
}
