package com.github.gotson.nightmonkeys.webp.lib.enums;


import com.github.gotson.nightmonkeys.webp.lib.panama.webpdemux.demux_h;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

public enum WebPFeatureFlags {
    ANIMATION_FLAG(demux_h.ANIMATION_FLAG()),
    XMP_FLAG(demux_h.XMP_FLAG()),
    EXIF_FLAG(demux_h.EXIF_FLAG()),
    ALPHA_FLAG(demux_h.ALPHA_FLAG()),
    ICCP_FLAG(demux_h.ICCP_FLAG()),
    ALL_VALID_FLAGS(demux_h.ALL_VALID_FLAGS());

    private final int val;

    WebPFeatureFlags(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static EnumSet<WebPFeatureFlags> parseInt(int flags) {
        return Arrays.stream(values())
            .filter(type -> (flags & type.intValue()) == type.intValue())
            .collect(Collectors.toCollection(() -> EnumSet.noneOf(WebPFeatureFlags.class)));
    }

    public static WebPFeatureFlags fromId(int id) {
        for (WebPFeatureFlags type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
