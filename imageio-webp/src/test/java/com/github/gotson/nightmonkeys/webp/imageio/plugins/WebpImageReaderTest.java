package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.NoOpImageReaderAbstractTest;
import com.github.gotson.nightmonkeys.common.imageio.ProviderInfo;

class WebpImageReaderTest extends NoOpImageReaderAbstractTest<WebpImageReader> {
    @Override
    protected ProviderInfo createProvider() {
        return new WebpProviderInfo();
    }
}
