package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import com.github.gotson.nightmonkeys.jxl.BasicInfo;
import com.github.gotson.nightmonkeys.jxl.JpegXl;
import com.github.gotson.nightmonkeys.jxl.JxlException;
import com.twelvemonkeys.imageio.ImageReaderBase;
import com.twelvemonkeys.imageio.color.ColorSpaces;
import com.twelvemonkeys.imageio.util.ImageTypeSpecifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class JxlImageReader extends ImageReaderBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(JxlImageReader.class);

    private BasicInfo info;

    private List<ImageTypeSpecifier> imageTypes;

    protected JxlImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    private void readInfo() throws IOException {
        if (info == null) {

            try {
                info = JpegXl.getBasicInfo((ImageInputStream) input);

                ColorSpace colorSpace = info.iccProfile() != null ? ColorSpaces.createColorSpace(info.iccProfile()) : ColorSpace.getInstance(ColorSpace.CS_sRGB);
                imageTypes = List.of(
                    // RGBA
                    ImageTypeSpecifiers.createPacked(colorSpace, 0xff000000, 0x00ff0000, 0x0000ff00, 0x000000ff, DataBuffer.TYPE_INT, false)
                );
            } catch (JxlException e) {
                throw new IOException(e);
            }
        }
    }

    private void readInfo(int imageIndex) throws IOException {
        checkBounds(imageIndex);

        readInfo();
    }

    /**
     * The number of images corresponds to the number of different resolutions that can be directly
     * decoded.
     */
    @Override
    public int getNumImages(boolean allowSearch) throws IOException {
        assertInput();
        readInfo();

        return info.frameCount();
    }

    @Override
    public int getWidth(int imageIndex) throws IOException {
        readInfo(imageIndex);

        return info.width();
    }

    @Override
    public int getHeight(int imageIndex) throws IOException {
        readInfo(imageIndex);

        return info.height();
    }

    @Override
    public ImageTypeSpecifier getRawImageType(int imageIndex) throws IOException {
        readInfo(imageIndex);

        return imageTypes.get(0);
    }

    @Override
    public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) throws IOException {
        readInfo(imageIndex);

        return imageTypes.iterator();
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
        readInfo(imageIndex);

        int width = getWidth(imageIndex);
        int height = getHeight(imageIndex);
        BufferedImage destination = getDestination(param, getImageTypes(imageIndex), width, height);

        processImageStarted(imageIndex);
        processImageProgress(0F);

        try {
            JpegXl.decode((ImageInputStream) getInput(), info, destination.getRaster(), param, imageIndex);
        } catch (JxlException e) {
            throw new IOException(e);
        }

        if (abortRequested()) {
            processReadAborted();
        } else {
            processImageComplete();
        }

        return destination;
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
        imageTypes = null;
        super.setInput(input, seekForwardOnly, ignoreMetadata);
    }

    @Override
    protected void resetMembers() {
        info = null;
        imageTypes = null;
    }
}
