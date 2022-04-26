package com.github.gotson.nightmonkeys.webp;

public record BasicInfo(
    int width,
    int height,
    boolean hasAlpha,
    boolean hasAnimation,
    Format format) {
    enum Format {
        UNDEFINED_OR_MIXED,
        LOSSY,
        LOSSLESS
    }
}
