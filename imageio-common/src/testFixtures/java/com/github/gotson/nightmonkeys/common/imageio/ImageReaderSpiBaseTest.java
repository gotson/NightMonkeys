package com.github.gotson.nightmonkeys.common.imageio;

import java.io.InputStream;
import java.net.URL;

public class ImageReaderSpiBaseTest {

    public static URL getClassLoaderResource(String fixtureFile) {
        return ImageReaderSpiBaseTest.class.getResource(fixtureFile);
    }

    public static InputStream getClassLoaderResourceAsInputStream(String fixtureFile) {
        return ImageReaderSpiBaseTest.class.getResourceAsStream(fixtureFile);
    }
}
