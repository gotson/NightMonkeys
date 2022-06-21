package com.github.gotson.nightmonkeys.webp;

import java.awt.color.ICC_Profile;

public record BasicInfo(
    int width,
    int height,
    boolean hasAlpha,
    boolean hasAnimation,
    Format format,
    ICC_Profile iccProfile
) {
    enum Format {
        UNDEFINED_OR_MIXED,
        LOSSY,
        LOSSLESS
    }
}
