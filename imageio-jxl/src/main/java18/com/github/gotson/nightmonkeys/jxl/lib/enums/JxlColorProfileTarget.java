package com.github.gotson.nightmonkeys.jxl.lib.enums;

import com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h;

/**
 * Defines which color profile to get: the profile from the codestream
 * metadata header, which represents the color profile of the original image,
 * or the color profile from the pixel data received by the decoder. Both are
 * the same if the basic has uses_original_profile set.
 */
public enum JxlColorProfileTarget {
    /**
     * Get the color profile of the original image from the metadata.
     */
    JXL_COLOR_PROFILE_TARGET_ORIGINAL(decode_h.JXL_COLOR_PROFILE_TARGET_ORIGINAL()),

    /**
     * Get the color profile of the pixel data the decoder outputs.
     */
    JXL_COLOR_PROFILE_TARGET_DATA(decode_h.JXL_COLOR_PROFILE_TARGET_DATA());

    private final int val;

    JxlColorProfileTarget(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }
}
