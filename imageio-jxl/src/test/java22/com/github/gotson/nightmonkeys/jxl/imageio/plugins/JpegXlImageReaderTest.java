package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ImageReaderAbstractTest;

import javax.imageio.spi.ImageReaderSpi;
import java.awt.Dimension;
import java.util.List;

import static java.util.Arrays.asList;

class JpegXlImageReaderTest extends ImageReaderAbstractTest<JxlImageReader> {

    @Override
    protected ImageReaderSpi createProvider() {
        return new JxlImageReaderSpi();
    }

    @Override
    protected List<TestData> getTestData() {
        return asList(
            // JPEG XL image, 610x407, lossy, 8-bit RGB+Alpha
            new TestData(getClassLoaderResource("/jxl/hills.jxl"), new Dimension(610, 407)),
            // JPEG XL image, 1386x924, (possibly) lossless, 10-bit RGB+Alpha
            // Orientation: 7 (Anti-Transposed) => we need to invert the width and height
            new TestData(getClassLoaderResource("/jxl/island.jxl"), new Dimension(924, 1386)),
            // Animation, 12 frames
            new TestData(getClassLoaderResource("/jxl/animation.jxl"), new Dimension(256, 256)),
            // Grayscale ICC profile
            new TestData(getClassLoaderResource("/jxl/gray.jxl"), new Dimension(1561, 2240))
        );
    }

    @Override
    protected List<String> getFormatNames() {
        return List.of(new JxlProviderInfo().getFormatNames());
    }

    @Override
    protected List<String> getSuffixes() {
        return List.of(new JxlProviderInfo().getSuffixes());
    }

    @Override
    protected List<String> getMIMETypes() {
        return List.of(new JxlProviderInfo().getMimeTypes());
    }
}
