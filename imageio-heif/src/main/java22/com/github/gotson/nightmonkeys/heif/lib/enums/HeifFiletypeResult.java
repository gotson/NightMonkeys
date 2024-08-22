package com.github.gotson.nightmonkeys.heif.lib.enums;

import com.github.gotson.nightmonkeys.heif.lib.panama.heif_h;


public enum HeifFiletypeResult {
    HEIF_FILETYPE_NO(heif_h.heif_filetype_no()),
    HEIF_FILETYPE_YES_SUPPORTED(heif_h.heif_filetype_yes_supported()),
    HEIF_FILETYPE_YES_UNSUPPORTED(heif_h.heif_filetype_yes_unsupported()),
    HEIF_FILETYPE_MAYBE(heif_h.heif_filetype_maybe());

    private final int val;

    HeifFiletypeResult(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static HeifFiletypeResult fromId(int id) {
        for (HeifFiletypeResult type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
