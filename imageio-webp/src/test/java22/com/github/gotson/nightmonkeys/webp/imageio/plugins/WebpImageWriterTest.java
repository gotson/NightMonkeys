package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.twelvemonkeys.imageio.util.ImageWriterAbstractTest;

import javax.imageio.spi.ImageWriterSpi;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.List;

public class WebpImageWriterTest extends ImageWriterAbstractTest<WebpImageWriter> {

    @Override
    protected ImageWriterSpi createProvider() {
        return new WebpImageWriterSpi();
    }

    @Override
    protected List<? extends RenderedImage> getTestData() {
        return List.of(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
    }
}