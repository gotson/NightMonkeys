package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.NoOpImageReaderAbstractTest;
import com.github.gotson.nightmonkeys.common.imageio.ProviderInfo;

class JpegXlImageReaderTest extends NoOpImageReaderAbstractTest<JxlImageReader> {
    @Override
    protected ProviderInfo createProvider() {
        return new JxlProviderInfo();
    }
}
