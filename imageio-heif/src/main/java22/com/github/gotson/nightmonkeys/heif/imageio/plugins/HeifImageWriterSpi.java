package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ImageWriterSpiBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ServiceRegistry;
import java.util.Locale;

public class HeifImageWriterSpi extends ImageWriterSpiBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeifImageWriterSpi.class);

    public HeifImageWriterSpi() {
        super(new HeifProviderInfo());
    }

    @Override
    public boolean canEncodeImage(ImageTypeSpecifier type) {
        return true;
    }

    @Override
    public ImageWriter createWriterInstance(Object extension) {
        return new HeifImageWriter(this);
    }

    @Override
    public void onRegistration(ServiceRegistry registry, Class<?> category) {
        if (!HeifLibraryLoader.loadLibrary()) {
            LOGGER.info("Deregistering service provider");
            registry.deregisterServiceProvider(this);
        }
        super.onRegistration(registry, category);
    }

    @Override
    public String getDescription(Locale locale) {
        return "Avif/Heif writer plugin based on libheif.";
    }
}
