package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ImageReaderSpiBase;
import com.github.gotson.nightmonkeys.webp.WebP;
import com.github.gotson.nightmonkeys.webp.WebpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Locale;

public class WebpImageReaderSpi extends ImageReaderSpiBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebpImageReaderSpi.class);
    private boolean libLoaded = false;

    public WebpImageReaderSpi() {
        super(new WebpProviderInfo());
    }

    private boolean loadLibrary() {
        if (!libLoaded) {
            try {
                LOGGER.info("Loaded libwebp: decoder v{}, demux v{}", WebP.getDecoderVersion(), WebP.getDemuxVersion());
                libLoaded = true;
            } catch (UnsatisfiedLinkError e) {
                LOGGER.warn("Could not load libwebp, plugin will be disabled. {}", e.getMessage());
            } catch (ExceptionInInitializerError e) {
                LOGGER.warn("Native access is disabled, plugin will be disabled. Try adding JVM arguments: --enable-native-access=ALL-UNNAMED");
            } catch (Exception e) {
                LOGGER.warn("Unknown error", e);
            }
        }
        return libLoaded;
    }

    @Override
    public void onRegistration(ServiceRegistry registry, Class<?> category) {
        if (!loadLibrary()) {
            LOGGER.info("Deregistering service provider");
            registry.deregisterServiceProvider(this);
        }
        super.onRegistration(registry, category);
    }

    @Override
    public boolean canDecodeInput(Object input) throws IOException {
        if (!(input instanceof ImageInputStream)) {
            input = ImageIO.createImageInputStream(input);
        }
        if (input == null) {
            return false;
        }

        try {
            return WebP.canDecode((ImageInputStream) input);
        } catch (WebpException e) {
            throw new IOException(e);
        }
    }

    @Override
    public ImageReader createReaderInstance(Object extension) {
        return new WebpImageReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "WebP reader plugin based on libwebp.";
    }
}
