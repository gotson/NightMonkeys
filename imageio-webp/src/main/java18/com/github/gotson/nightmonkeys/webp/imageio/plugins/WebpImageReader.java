package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.webp.BasicInfo;
import com.github.gotson.nightmonkeys.webp.WebP;
import com.github.gotson.nightmonkeys.webp.WebpException;
import com.twelvemonkeys.imageio.ImageReaderBase;
import com.twelvemonkeys.imageio.color.ColorProfiles;
import com.twelvemonkeys.imageio.color.ColorSpaces;
import com.twelvemonkeys.imageio.util.ImageTypeSpecifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class WebpImageReader extends ImageReaderBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebpImageReader.class);

    private BasicInfo info;

    protected WebpImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
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

        if (info.hasAnimation() && allowSearch) {
            if (isSeekForwardOnly()) {
                throw new IllegalStateException("Illegal combination of allowSearch with seekForwardOnly");
            }

            // TODO: handle animations
            return 1;
        }

        return info.hasAnimation() ? -1 : 1;
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

        if (info.iccProfile() != null) {
            ICC_ColorSpace colorSpace = ColorSpaces.createColorSpace(info.iccProfile());
            return ImageTypeSpecifiers.createPacked(colorSpace, 0x00ff0000, 0x0000ff00, 0x000000ff, 0xff000000, DataBuffer.TYPE_INT, false);
        }

        return ImageTypeSpecifier.createFromBufferedImageType(info.hasAlpha() ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) throws IOException {
        readInfo(imageIndex);

        return List.of(
            getRawImageType(imageIndex),
            ImageTypeSpecifier.createFromBufferedImageType(info.hasAlpha() ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB)
        ).iterator();
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
            WebP.decode((ImageInputStream) getInput(), info, destination.getRaster(), param);
        } catch (WebpException e) {
            throw new IOException(e);
        }

        applyICCProfileIfNeeded(destination);

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
        super.setInput(input, seekForwardOnly, ignoreMetadata);
    }

    @Override
    protected void resetMembers() {
        info = null;
    }

    private void applyICCProfileIfNeeded(final BufferedImage destination) {
        if (info.iccProfile() != null) {
            ColorModel colorModel = destination.getColorModel();
            ICC_Profile destinationProfile = ((ICC_ColorSpace) colorModel.getColorSpace()).getProfile();

            if (!info.iccProfile().equals(destinationProfile)) {
                LOGGER.debug("Converting from " + info.iccProfile() + " to " + (ColorProfiles.isCS_sRGB(destinationProfile) ? "sRGB" : destinationProfile));

                WritableRaster raster = colorModel.hasAlpha()
                    ? destination.getRaster().createWritableChild(0, 0, destination.getWidth(), destination.getHeight(), 0, 0, new int[] {0, 1, 2})
                    : destination.getRaster();

                new ColorConvertOp(new ICC_Profile[] {info.iccProfile(), destinationProfile}, null)
                    .filter(raster, raster);
            }
        }
    }
}
