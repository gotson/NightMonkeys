package com.github.gotson.nightmonkeys.webp.lib.enums;


import com.github.gotson.nightmonkeys.webp.lib.panama.webpdemux.demux_h;

public enum WebPFormatFeature {
    /**
     * bit-wise combination of WebPFeatureFlags
     * corresponding to the 'VP8X' chunk (if present).
     */
    WEBP_FF_FORMAT_FLAGS(demux_h.WEBP_FF_FORMAT_FLAGS()),
    WEBP_FF_CANVAS_WIDTH(demux_h.WEBP_FF_CANVAS_WIDTH()),
    WEBP_FF_CANVAS_HEIGHT(demux_h.WEBP_FF_CANVAS_HEIGHT()),

    /**
     * only relevant for animated file
     */
    WEBP_FF_LOOP_COUNT(demux_h.WEBP_FF_LOOP_COUNT()),

    /**
     * only relevant for animated file
     */
    WEBP_FF_BACKGROUND_COLOR(demux_h.WEBP_FF_BACKGROUND_COLOR()),

    /**
     * Number of frames present in the demux object.
     * In case of a partial demux, this is the number
     * of frames seen so far, with the last frame
     * possibly being partial.
     */
    WEBP_FF_FRAME_COUNT(demux_h.WEBP_FF_FRAME_COUNT());

    private final int val;

    WebPFormatFeature(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static WebPFormatFeature fromId(int id) {
        for (WebPFormatFeature type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
