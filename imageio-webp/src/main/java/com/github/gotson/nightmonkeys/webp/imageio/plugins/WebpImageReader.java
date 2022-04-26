package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

public class WebpImageReader extends ImageReader {

    protected WebpImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    /**
     * The number of images corresponds to the number of different resolutions that can be directly
     * decoded.
     */
    @Override
    public int getNumImages(boolean allowSearch) {
        return 0;
    }

    @Override
    public int getWidth(int imageIndex) throws IOException {
        throw new IOException();
    }

    @Override
    public int getHeight(int imageIndex) throws IOException {
        throw new IOException();
    }

    @Override
    public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) throws IOException {
        throw new IOException();
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
        throw new IOException();
    }

    @Override
    public IIOMetadata getStreamMetadata() {
        return null;
    }

    @Override
    public IIOMetadata getImageMetadata(int imageIndex) {
        return null;
    }
}
