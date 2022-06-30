package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.NoOpImageReaderAbstractTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class WebpImageReaderTest extends NoOpImageReaderAbstractTest<WebpImageReader> {
    @Override
    protected List<String> getFormatNames() {
        return Arrays.asList("webp", "WebP");
    }

    @Override
    protected List<String> getSuffixes() {
        return Collections.singletonList("webp");
    }

    @Override
    protected List<String> getMIMETypes() {
        return Collections.singletonList("image/webp");
    }
}
