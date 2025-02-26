package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.twelvemonkeys.imageio.util.ImageReaderAbstractTest;

import javax.imageio.spi.ImageReaderSpi;
import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class HeifImageReaderTest extends ImageReaderAbstractTest<HeifImageReader> {

    @Override
    protected ImageReaderSpi createProvider() {
        return new HeifImageReaderSpi();
    }

    @Override
    protected List<TestData> getTestData() {
        return asList(
            // HEIF (from http://nokiatech.github.io/heif/examples.html)
            // Each file contains a high quality still image and its thumbnail
            new TestData(getClassLoaderResource("/heif/autumn_1440x960.heic"), new Dimension(1440, 960)),
            new TestData(getClassLoaderResource("/heif/old_bridge_1440x960.heic"), new Dimension(1440, 960)),

            // HEIF (from https://www.dwsamplefiles.com/download-heif-sample-files/)
            new TestData(getClassLoaderResource("/heif/dwsample-heif-640.heif"), new Dimension(640, 427)),

            // HEIF (from https://github.com/nokiatech/heif_conformance)
            // An image item with an associated alpha mask auxiliary image
            new TestData(getClassLoaderResource("/heif/C006.heic"), new Dimension(1280, 720)),

            // HEIC Collection (from http://nokiatech.github.io/heif/examples.html)
            new TestData(getClassLoaderResource("/heif/collection_random_1440x960.heic"), Collections.nCopies(4, new Dimension(1440, 960)), null),
            new TestData(getClassLoaderResource("/heif/collection_season_1440x960.heic"), Collections.nCopies(4, new Dimension(1440, 960)), null),

            // HEIC Image Bursts
            // Each file contains a short video, thumbnails of each frame in the video and high quality version of the “selected best shots” from an image burst.
            // The files are represented in two different modes: 1. Burst images, 2. Video and HD quality images.
            // Status: 4 images available
            new TestData(getClassLoaderResource("/heif/burst_bird.heic"), Collections.nCopies(4, new Dimension(1280, 720)), null),
            new TestData(getClassLoaderResource("/heif/burst_rally.heic"), Collections.nCopies(4, new Dimension(1280, 720)), null),

            // HEIC Animation (from http://nokiatech.github.io/heif/examples.html)
            // Each file contains multiple still images, just like an album or collection. Album groupings and descriptions can be indicated in the file:
            // Status: not supported by libheif, only 1 image will be available
            new TestData(getClassLoaderResource("/heif/animation_sea1.heic"), new Dimension(256, 144)),
            new TestData(getClassLoaderResource("/heif/animation_starfield.heic"), new Dimension(256, 144)),

            // AVIF (from http://188.121.162.14/avif/)
            new TestData(getClassLoaderResource("/avif/avif-enabled.avif"), new Dimension(640, 478)),

            // AVIF Animation (from http://188.121.162.14/avif/)
            // Status: not supported by libheif, only 1 image will be available
            new TestData(getClassLoaderResource("/avif/animation2.avif"), new Dimension(640, 640)),

            // AVIF with stride != from width
            new TestData(getClassLoaderResource("/avif/kk.avif"), new Dimension(2535, 4167)),
            new TestData(getClassLoaderResource("/avif/monster.avif"), new Dimension(846, 1200))
        );
    }

    @Override
    protected List<String> getFormatNames() {
        return List.of(new HeifProviderInfo().getFormatNames());
    }

    @Override
    protected List<String> getSuffixes() {
        return List.of(new HeifProviderInfo().getSuffixes());
    }

    @Override
    protected List<String> getMIMETypes() {
        return List.of(new HeifProviderInfo().getMimeTypes());
    }
}
