package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ImageReaderSpiBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;
import java.util.Locale;

public class WebpImageReaderSpi extends ImageReaderSpiBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebpImageReaderSpi.class);

    public WebpImageReaderSpi() {
        super(new WebpProviderInfo());
    }

    @Override
    public void onRegistration(ServiceRegistry registry, Class<?> category) {
        LOGGER.info("This plugin only supports Java 22, plugin will be disabled");
        registry.deregisterServiceProvider(this);
        super.onRegistration(registry, category);
    }

    @Override
    public boolean canDecodeInput(Object input) {
        return false;
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
