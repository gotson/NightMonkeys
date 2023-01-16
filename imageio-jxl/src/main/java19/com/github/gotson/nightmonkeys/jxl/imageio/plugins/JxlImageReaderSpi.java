package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import com.github.gotson.nightmonkeys.jxl.JpegXl;
import com.github.gotson.nightmonkeys.jxl.JxlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Locale;

public class JxlImageReaderSpi extends ImageReaderSpi {

    private static final Logger LOGGER = LoggerFactory.getLogger(JxlImageReaderSpi.class);
    private static final String vendorName = "NightMonkeys";
    private static final String version = "0.1.0";
    private static final String readerClassName = "com.github.gotson.nightmonkeys.jxl.imageio.JxlImageReader";
    private static final String[] names = {"Jpeg XL", "jxl"};
    private static final String[] suffixes = {"jxl"};
    private static final String[] MIMETypes = {"image/jxl"};
    private static final String[] writerSpiNames = null;

    private boolean libLoaded = false;

    /**
     * Construct the SPI. Boilerplate.
     */
    public JxlImageReaderSpi() {
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

    private boolean loadLibrary() {
        if (!libLoaded) {
            try {
                String libVersion = JpegXl.getLibVersion();
                LOGGER.info("Loaded libjxl v{}", libVersion);
                libLoaded = true;
            } catch (UnsatisfiedLinkError e) {
                LOGGER.warn("Could not load libjxl, plugin will be disabled. {}", e.getMessage());
            } catch (NoClassDefFoundError | UnsupportedClassVersionError e) {
                LOGGER.warn("Missing Foreign Linker API, plugin will be disabled. Try adding JVM arguments: --enable-preview");
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
            return JpegXl.canDecode((ImageInputStream) input);
        } catch (JxlException e) {
            throw new IOException(e);
        }
    }

    @Override
    public ImageReader createReaderInstance(Object extension) {
        return new JxlImageReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "Jpeg XL reader plugin based on libjxl.";
    }
}
