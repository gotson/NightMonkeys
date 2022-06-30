package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.NoOpImageReaderAbstractTest;

import java.util.Collections;
import java.util.List;

class JpegXlImageReaderTest extends NoOpImageReaderAbstractTest<JxlImageReader> {

    @Override
    protected List<String> getFormatNames() {
        return Collections.singletonList("jxl");
    }

    @Override
    protected List<String> getSuffixes() {
        return Collections.singletonList("jxl");
    }

    @Override
    protected List<String> getMIMETypes() {
        return Collections.singletonList("image/jxl");
    }
}
