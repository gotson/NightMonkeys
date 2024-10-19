package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.twelvemonkeys.imageio.spi.ImageWriterSpiBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ServiceRegistry;
import java.util.Locale;

public class WebpImageWriterSpi extends ImageWriterSpiBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebpImageWriterSpi.class);

    private boolean libLoaded = false;

    public WebpImageWriterSpi() {
        super(new WebpProviderInfo());
    }

    @Override
    public void onRegistration(ServiceRegistry registry, Class<?> category) {
        LOGGER.info("This plugin only supports Java 22, plugin will be disabled");
        registry.deregisterServiceProvider(this);
        super.onRegistration(registry, category);
    }

    @Override
    public boolean canEncodeImage(ImageTypeSpecifier type) {
        return false;
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
