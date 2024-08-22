package com.github.gotson.nightmonkeys.heif.lib.enums;

import com.github.gotson.nightmonkeys.heif.lib.panama.heif_h;

public enum HeifChannel {
    HEIF_CHANNEL_Y(heif_h.heif_channel_Y()),
    HEIF_CHANNEL_CB(heif_h.heif_channel_Cb()),
    HEIF_CHANNEL_CR(heif_h.heif_channel_Cr()),
    HEIF_CHANNEL_R(heif_h.heif_channel_R()),
    HEIF_CHANNEL_G(heif_h.heif_channel_G()),
    HEIF_CHANNEL_B(heif_h.heif_channel_B()),
    HEIF_CHANNEL_ALPHA(heif_h.heif_channel_Alpha()),
    HEIF_CHANNEL_INTERLEAVED(heif_h.heif_channel_interleaved()),
    ;

    private final int val;

    HeifChannel(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static HeifChannel fromId(int id) {
        for (HeifChannel type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
