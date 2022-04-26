package com.github.gotson.nightmonkeys.webp.imageio.plugins;

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

public class WebpImageReaderSpi extends ImageReaderSpi {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebpImageReaderSpi.class);
    private static final String vendorName = "NightMonkeys";
    private static final String version = "0.1.0";
    private static final String readerClassName = "com.github.gotson.nightmonkeys.webp.imageio.plugins.WebpImageReader";
    private static final String[] names = {"WebP", "webp"};
    private static final String[] suffixes = {"webp"};
    private static final String[] MIMETypes = {"image/webp"};
    private static final String[] writerSpiNames = null;

    private boolean libLoaded = false;

    /**
     * Construct the SPI. Boilerplate.
     */
    public WebpImageReaderSpi() {
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
                String libVersion = WebP.getLibVersion();
                LOGGER.info("Loaded libwebp v{}", libVersion);
                libLoaded = true;
            } catch (UnsatisfiedLinkError e) {
                LOGGER.warn("Could not load libwebp, plugin will be disabled. {}", e.getMessage());
            } catch (NoClassDefFoundError e) {
                LOGGER.warn("Missing Foreign Linker API, plugin will be disabled. Try adding JVM arguments: --add-modules jdk.incubator.foreign");
            } catch (ExceptionInInitializerError e) {
                LOGGER.warn("Native access is disabled, plugin will be disabled. Try adding JVM arguments: --enable-native-access=ALL-UNNAMED");
            }
        }
        return libLoaded;
    }

    @Override
    public void onRegistration(ServiceRegistry registry, Class<?> category) {
        if (!loadLibrary()) {
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
