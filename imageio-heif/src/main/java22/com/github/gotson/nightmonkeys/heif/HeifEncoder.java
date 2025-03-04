package com.github.gotson.nightmonkeys.heif;

import com.github.gotson.nightmonkeys.heif.lib.enums.HeifCompressionFormat;

public record HeifEncoder(
    String name,
    String id,
    HeifCompressionFormat format,
    boolean supportsLossless,
    boolean supportsLossy
) {
}
