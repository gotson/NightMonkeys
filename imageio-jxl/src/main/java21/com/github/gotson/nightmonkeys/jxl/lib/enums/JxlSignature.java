package com.github.gotson.nightmonkeys.jxl.lib.enums;

import com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h;

/**
 * The result of JxlSignatureCheck.
 */
public enum JxlSignature {
    /**
     * Not enough bytes were passed to determine if a valid signature was found.
     */
    JXL_SIG_NOT_ENOUGH_BYTES(decode_h.JXL_SIG_NOT_ENOUGH_BYTES()),
    /**
     * No valid JPEGXL header was found.
     */
    JXL_SIG_INVALID(decode_h.JXL_SIG_INVALID()),
    /**
     * A valid JPEG XL codestream signature was found, that is a JPEG XL image
     * without container.
     */
    JXL_SIG_CODESTREAM(decode_h.JXL_SIG_CODESTREAM()),
    /**
     * A valid container signature was found, that is a JPEG XL image embedded
     * in a box format container.
     */
    JXL_SIG_CONTAINER(decode_h.JXL_SIG_CONTAINER());

    private final int val;

    JxlSignature(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static JxlSignature fromId(int id) {
        for (JxlSignature type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
