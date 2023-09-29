package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.NoOpImageReaderAbstractTest;

import java.util.Arrays;
import java.util.List;

class HeifImageReaderTest extends NoOpImageReaderAbstractTest<HeifImageReader> {
    @Override
    protected List<String> getFormatNames() {
        return Arrays.asList("AVIF", "HEIF", "HEIC");
    }

    @Override
    protected List<String> getSuffixes() {
        return Arrays.asList("heif", "heifs", "heic", "heics", "avci", "avcs", "avif", "HIF");
    }

    @Override
    protected List<String> getMIMETypes() {
        return Arrays.asList("image/avif", "image/heif", "image/heif-sequence", "image/heic", "image/heic-sequence");
    }
}
