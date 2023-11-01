package com.github.gotson.nightmonkeys.heif.lib.enums;

import com.github.gotson.nightmonkeys.heif.lib.panama.heif_h;

public enum HeifColorSpace {
    HEIF_COLOR_SPACE_UNDEFINED(heif_h.heif_colorspace_undefined()),
    HEIF_COLOR_SPACE_YCBCR(heif_h.heif_colorspace_YCbCr()),
    HEIF_COLOR_SPACE_RGB(heif_h.heif_colorspace_RGB()),
    HEIF_COLOR_SPACE_MONOCHROME(heif_h.heif_colorspace_monochrome());

    private final int val;

    HeifColorSpace(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static HeifColorSpace fromId(int id) {
        for (HeifColorSpace type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
