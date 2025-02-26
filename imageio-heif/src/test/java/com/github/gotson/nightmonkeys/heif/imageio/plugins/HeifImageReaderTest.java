package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.NoOpImageReaderAbstractTest;
import com.github.gotson.nightmonkeys.common.imageio.ProviderInfo;

class HeifImageReaderTest extends NoOpImageReaderAbstractTest<HeifImageReader> {
    @Override
    protected ProviderInfo createProvider() {
        return new HeifProviderInfo();
    }
}
