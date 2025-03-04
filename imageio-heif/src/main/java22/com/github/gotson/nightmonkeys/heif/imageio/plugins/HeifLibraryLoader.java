package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.heif.Heif;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HeifLibraryLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeifLibraryLoader.class);
    private static Boolean libLoaded;

    synchronized static boolean loadLibrary() {
        if (libLoaded == null) {
            try {
                LOGGER.info("Loaded libheif v{}", Heif.getLibVersion());
                if (Heif.isLibVersionSupported()) {
                    libLoaded = true;
                    return true;
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
            libLoaded = false;
        }
        return libLoaded;
    }
}
