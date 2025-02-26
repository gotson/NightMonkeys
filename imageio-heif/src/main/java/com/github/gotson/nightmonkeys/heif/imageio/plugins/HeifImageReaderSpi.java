package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ImageReaderSpiBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageReader;
import javax.imageio.spi.ServiceRegistry;
import java.util.Locale;

public class HeifImageReaderSpi extends ImageReaderSpiBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeifImageReaderSpi.class);

    public HeifImageReaderSpi() {
        super(new HeifProviderInfo());
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
        return new HeifImageReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "Avif/Heif reader plugin based on libheif.";
    }
}
