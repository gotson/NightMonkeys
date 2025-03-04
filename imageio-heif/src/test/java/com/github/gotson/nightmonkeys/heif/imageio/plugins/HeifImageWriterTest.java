package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.NoOpImageWriterAbstractTest;
import com.github.gotson.nightmonkeys.common.imageio.ProviderInfo;

class HeifImageWriterTest extends NoOpImageWriterAbstractTest<HeifImageWriter> {
    @Override
    protected ProviderInfo createProvider() {
        return new HeifProviderInfo();
    }
}
