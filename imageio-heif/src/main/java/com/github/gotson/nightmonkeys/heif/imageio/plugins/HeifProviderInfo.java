package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ProviderInfo;

final class HeifProviderInfo extends ProviderInfo {
    HeifProviderInfo() {
        super(
            new String[] {"AVIF", "HEIF", "HEIC"},
            new String[] {"heif", "heifs", "heic", "heics", "avci", "avcs", "avif", "HIF"},
            new String[] {"image/avif", "image/heif", "image/heif-sequence", "image/heic", "image/heic-sequence"},
            "com.github.gotson.nightmonkeys.heif.imageio.plugins.HeifImageReader",
            new String[] {"com.github.gotson.nightmonkeys.heif.imageio.plugins.HeifImageReaderSpi"},
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
