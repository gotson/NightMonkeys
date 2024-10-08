package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.heif.Heif;
import com.github.gotson.nightmonkeys.heif.HeifException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Locale;

public class HeifImageReaderSpi extends ImageReaderSpi {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeifImageReaderSpi.class);
    private static final String vendorName = "NightMonkeys";
    private static final String version = "0.1.0";
    private static final String readerClassName = "com.github.gotson.nightmonkeys.heif.imageio.plugins.HeifImageReader";
    private static final String[] names = {"AVIF", "HEIF", "HEIC"};
    private static final String[] suffixes = {"heif", "heifs", "heic", "heics", "avci", "avcs", "avif", "HIF"};
    private static final String[] MIMETypes = {"image/avif", "image/heif", "image/heif-sequence", "image/heic", "image/heic-sequence"};
    private static final String[] writerSpiNames = null;

    private boolean libLoaded = false;

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

    private boolean loadLibrary() {
        if (!libLoaded) {
            try {
                LOGGER.info("Loaded libheif v{}", Heif.getLibVersion());
                if (Heif.isLibVersionSupported()) {
                    libLoaded = true;
                } else {
                    LOGGER.warn("libheif version is not supported");
                }
            } catch (UnsatisfiedLinkError e) {
                LOGGER.warn("Could not load libheif, plugin will be disabled. {}", e.getMessage());
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
            return Heif.canDecode((ImageInputStream) input);
        } catch (HeifException e) {
            throw new IOException(e);
        }
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
