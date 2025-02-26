package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ProviderInfo;

final class WebpProviderInfo extends ProviderInfo {
    WebpProviderInfo() {
        super(
            new String[] {"WebP", "webp"},
            new String[] {"webp"},
            new String[] {"image/webp"},
            "com.github.gotson.nightmonkeys.webp.imageio.plugins.WebpImageReader",
            new String[] {"com.github.gotson.nightmonkeys.webp.imageio.plugins.WebpImageReaderSpi"},
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
