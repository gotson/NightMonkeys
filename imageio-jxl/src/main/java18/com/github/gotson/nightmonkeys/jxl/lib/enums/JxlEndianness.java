package com.github.gotson.nightmonkeys.jxl.lib.enums;

import com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h;

/**
 * Ordering of multi-byte data.
 */
public enum JxlEndianness {
    /**
     * Use the endianness of the system, either little endian or big endian,
     * without forcing either specific endianness. Do not use if pixel data
     * should be exported to a well defined format.
     */
    JXL_NATIVE_ENDIAN(decode_h.JXL_NATIVE_ENDIAN()),

    /**
     * Force little endian
     */
    JXL_LITTLE_ENDIAN(decode_h.JXL_LITTLE_ENDIAN()),

    /**
     * Force big endian
     */
    JXL_BIG_ENDIAN(decode_h.JXL_BIG_ENDIAN());

    private final int val;

    JxlEndianness(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }
}
