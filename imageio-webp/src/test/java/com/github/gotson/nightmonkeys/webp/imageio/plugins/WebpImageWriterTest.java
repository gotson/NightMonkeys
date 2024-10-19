package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.NoOpImageWriterAbstractTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class WebpImageWriterTest extends NoOpImageWriterAbstractTest {
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
