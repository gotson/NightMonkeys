package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ProviderInfo;

final class JxlProviderInfo extends ProviderInfo {
    JxlProviderInfo() {
        super(
            new String[] {"Jpeg XL", "jxl"},
            new String[] {"jxl"},
            new String[] {"image/jxl"},
            "com.github.gotson.nightmonkeys.jxl.imageio.JxlImageReader",
            new String[] { "com.github.gotson.nightmonkeys.jxl.imageio.JxlImageReaderSpi"},
            null,
            null,
            false,
            null,
            null,
            null,
            null,
            false,
            null,
            null,
            null,
            null
        );
    }
}
