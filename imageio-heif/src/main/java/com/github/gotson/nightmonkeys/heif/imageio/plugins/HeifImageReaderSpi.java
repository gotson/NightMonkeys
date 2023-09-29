package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;
import java.util.Locale;

public class HeifImageReaderSpi extends ImageReaderSpi {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeifImageReaderSpi.class);
    private static final String vendorName = "NightMonkeys";
    private static final String version = "0.1.0";
    private static final String readerClassName = "com.github.gotson.nightmonkeys.heif.imageio.plugins.HeifImageReader";
    private static final String[] names = {"Avif", "Heif"};
    private static final String[] suffixes = {"avif", "heif"};
    private static final String[] MIMETypes = {"image/avif", "image/heif"};
    private static final String[] writerSpiNames = null;

    /**
     * Construct the SPI. Boilerplate.
     */
    public HeifImageReaderSpi() {
        super(
            vendorName,
            version,
            names,
            suffixes,
            MIMETypes,
            readerClassName,
            new Class[] {ImageInputStream.class},
            writerSpiNames,
            false,
            null,
            null,
            null,
            null,
            false,
            null,
            null,
            null,
            null);
    }

    @Override
    public void onRegistration(ServiceRegistry registry, Class<?> category) {
        LOGGER.info("This plugin only supports Java 21, plugin will be disabled");
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
