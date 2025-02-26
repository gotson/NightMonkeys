package com.github.gotson.nightmonkeys.webp.lib.enums;

/**
 * Enum representing different WebP lossless presets.
 * @see <a href="https://github.com/webmproject/libwebp/blob/dfdcb7f95ca280b2555020115b8f288a5a3453c2/src/enc/config_enc.c">lossless presets</a>
 */
public enum WebpLosslessPresets {
    LEVEL_0(0, 0.0F),
    LEVEL_1(1, 0.2F),
    LEVEL_2(3, 0.25F),
    LEVEL_3(3, 0.3F),
    LEVEL_4(3, 0.5F),
    LEVEL_5(4, 0.5F),
    LEVEL_6(4, 0.75F),
    LEVEL_7(4, 0.9F),
    LEVEL_8(5, 0.9F),
    LEVEL_9(6, 1.0F);

    private final int method;
    private final float quality;

    WebpLosslessPresets(int method, float quality) {
        this.method = method;
        this.quality = quality;
    }

    public int getMethod() {
        return method;
    }

    public float getQuality() {
        return quality;
    }
}
