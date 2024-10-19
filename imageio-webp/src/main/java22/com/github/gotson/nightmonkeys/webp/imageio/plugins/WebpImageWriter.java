package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.webp.WebP;
import com.github.gotson.nightmonkeys.webp.WebpException;
import com.twelvemonkeys.imageio.ImageWriterBase;

import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.io.IOException;

public class WebpImageWriter extends ImageWriterBase {

    WebpImageWriter(ImageWriterSpi provider) {
        super(provider);
    }

    @Override
    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
        return null;
    }

    @Override
    public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param) {
        return null;
    }

    @Override
    public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IOException {
        assertOutput();

        if (image == null) {
            throw new IllegalArgumentException("image may not be null");
        }

        if (param == null) {
            param = getDefaultWriteParam();
        }
        WebpImageWriteParam webpParam = (WebpImageWriteParam) param;

        RenderedImage renderedImage = image.getRenderedImage();
        Raster raster = getRaster(renderedImage);

        processImageStarted(0);
        processImageProgress(0F);
        if (abortRequested()) {
            processWriteAborted();
        } else {
            try {
                WebP.encode(imageOutput, raster, webpParam);
                if (abortRequested()) {
                    processWriteAborted();
                } else {
                    processImageComplete();
                }
            } catch (WebpException e) {
                throw new IOException(e);
            }
        }
    }

    @Override
    public WebpImageWriteParam getDefaultWriteParam() {
        return new WebpImageWriteParam();
    }

    private static Raster getRaster(final RenderedImage image) {
        if (image instanceof BufferedImage bi) {
            return bi.getRaster();
        }
        if (image.getNumXTiles() == 1 && image.getNumYTiles() == 1) {
            return image.getTile(0, 0);
        }
        return image.getData();
    }
}
