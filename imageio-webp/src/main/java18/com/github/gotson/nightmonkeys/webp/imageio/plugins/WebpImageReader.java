package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.webp.BasicInfo;
import com.github.gotson.nightmonkeys.webp.WebP;
import com.github.gotson.nightmonkeys.webp.WebpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

public class WebpImageReader extends ImageReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebpImageReader.class);

    private BasicInfo info;

    protected WebpImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    private void checkIndex(int imageIndex) {
        if (imageIndex > 0) {
            throw new IndexOutOfBoundsException("bad index");
        }
    }

    private void readInfo() throws IOException {
        if (info == null) {

            try {
                info = WebP.getBasicInfo((ImageInputStream) input);
            } catch (WebpException e) {
                throw new IOException(e);
            }
        }
    }

    /**
     * The number of images corresponds to the number of different resolutions that can be directly
     * decoded.
     */
    @Override
    public int getNumImages(boolean allowSearch) {
        return 1;
    }

    @Override
    public int getWidth(int imageIndex) throws IOException {
        checkIndex(imageIndex);
        readInfo();

        return info.width();
    }

    @Override
    public int getHeight(int imageIndex) throws IOException {
        checkIndex(imageIndex);
        readInfo();

        return info.height();
    }

    @Override
    public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) throws IOException {
        return Collections.singletonList(
                ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB))
            .iterator();

//        var colorModel = new ComponentColorModel(info.iccSpace(), info.hasAlpha(), false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);
//        var sampleModel = colorModel.createCompatibleSampleModel(info.width(), info.height());
//
//        return Collections.singletonList(new ImageTypeSpecifier(colorModel, sampleModel))
//            .iterator();
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
        checkIndex(imageIndex);
        readInfo();

        try {
            return WebP.decode((ImageInputStream) getInput(), info);
        } catch (WebpException e) {
            throw new IOException(e);
        }
    }

    @Override
    public IIOMetadata getStreamMetadata() {
        return null;
    }

    @Override
    public IIOMetadata getImageMetadata(int imageIndex) {
        return null;
    }

    @Override
    public void setInput(Object input, boolean seekForwardOnly, boolean ignoreMetadata) {
        info = null;
        super.setInput(input, seekForwardOnly, ignoreMetadata);
    }

    @Override
    public void reset() {
        info = null;
        super.reset();
    }
}
