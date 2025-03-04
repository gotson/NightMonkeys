package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.heif.Heif;
import com.github.gotson.nightmonkeys.heif.HeifException;
import com.twelvemonkeys.imageio.ImageWriterBase;

import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import java.io.IOException;

public class HeifImageWriter extends ImageWriterBase {

    protected HeifImageWriter(ImageWriterSpi originatingProvider) {
        super(originatingProvider);
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

        if (image.hasRaster()) {
            throw new UnsupportedOperationException("image has a Raster");
        }

        processImageStarted(0);
        processImageProgress(0);

        try {
            Heif.encode(imageOutput, image, param);
        } catch (HeifException e) {
            throw new IOException(e);
        }

        processImageComplete();
    }

    @Override
    public ImageWriteParam getDefaultWriteParam() {
        return new HeifWriteParam(getLocale());
    }
}
