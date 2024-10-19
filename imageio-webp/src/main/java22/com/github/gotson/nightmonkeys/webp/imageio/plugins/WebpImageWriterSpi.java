package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.webp.WebP;
import com.twelvemonkeys.imageio.spi.ImageWriterSpiBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ServiceRegistry;
import java.awt.image.BufferedImage;
import java.util.Locale;

public class WebpImageWriterSpi extends ImageWriterSpiBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebpImageWriterSpi.class);

    private boolean libLoaded = false;

    public WebpImageWriterSpi() {
        super(new WebpProviderInfo());
    }

    private boolean loadLibrary() {
        if (!libLoaded) {
            try {
                LOGGER.info("Loaded libwebp: encoder v{}", WebP.getEncoderVersion());
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
            LOGGER.info("Unregistering service provider");
            registry.deregisterServiceProvider(this);
        }
        super.onRegistration(registry, category);
    }

    @Override
    public boolean canEncodeImage(ImageTypeSpecifier type) {
        int bufferedImageType = type.getBufferedImageType();
        return bufferedImageType == BufferedImage.TYPE_INT_RGB || bufferedImageType == BufferedImage.TYPE_INT_ARGB;
    }

    @Override
    public ImageWriter createWriterInstance(Object extension) {
        return new WebpImageWriter(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "WebP writer plugin based on libwebp.";
    }
}
