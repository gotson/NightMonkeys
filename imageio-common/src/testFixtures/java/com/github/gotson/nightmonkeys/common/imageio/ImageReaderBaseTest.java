package com.github.gotson.nightmonkeys.common.imageio;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageReaderBaseTest<T extends ImageReader> {

    public static URL getClassLoaderResource(String fixtureFile) {
        return ImageReaderBaseTest.class.getResource(fixtureFile);
    }

    public static InputStream getClassLoaderResourceAsInputStream(String fixtureFile) {
        return ImageReaderBaseTest.class.getResourceAsStream(fixtureFile);
    }

    public static File getClassLoaderResourceAsFile(String fixtureFile) {
        return new File(getClassLoaderResource(fixtureFile).getFile());
    }

    @SuppressWarnings("unchecked")
    public T getReader(String fixtureFile) throws IOException {
        File inFile = getClassLoaderResourceAsFile(fixtureFile);
        ImageInputStream is = ImageIO.createImageInputStream(inFile);
        ImageReader reader = ImageIO.getImageReaders(is).next();
        reader.setInput(is);
        return (T) reader;
    }
}
