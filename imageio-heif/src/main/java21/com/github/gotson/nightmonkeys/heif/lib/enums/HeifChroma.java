package com.github.gotson.nightmonkeys.heif.lib.enums;

import com.github.gotson.nightmonkeys.heif.lib.panama.heif_h;

public enum HeifChroma {
    HEIF_CHROMA_UNDEFINED(heif_h.heif_chroma_undefined()),
    HEIF_CHROMA_MONOCHROME(heif_h.heif_chroma_monochrome()),
    HEIF_CHROMA_420(heif_h.heif_chroma_420()),
    HEIF_CHROMA_422(heif_h.heif_chroma_422()),
    HEIF_CHROMA_444(heif_h.heif_chroma_444()),
    HEIF_CHROMA_INTERLEAVED_RGB(heif_h.heif_chroma_interleaved_RGB()),
    HEIF_CHROMA_INTERLEAVED_RGBA(heif_h.heif_chroma_interleaved_RGBA()),
    HEIF_CHROMA_INTERLEAVED_RRGGBB_BE(heif_h.heif_chroma_interleaved_RRGGBB_BE()),
    HEIF_CHROMA_INTERLEAVED_RRGGBBAA_BE(heif_h.heif_chroma_interleaved_RRGGBBAA_BE()),
    HEIF_CHROMA_INTERLEAVED_RRGGBB_LE(heif_h.heif_chroma_interleaved_RRGGBB_LE()),
    HEIF_CHROMA_INTERLEAVED_RRGGBBAA_LE(heif_h.heif_chroma_interleaved_RRGGBBAA_LE());

    private final int val;

    HeifChroma(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static HeifChroma fromId(int id) {
        for (HeifChroma type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
