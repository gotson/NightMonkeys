package com.github.gotson.nightmonkeys.jxl.lib.enums;

import com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h;

/**
 * Image orientation metadata.
 * Values 1..8 match the EXIF definitions.
 * The name indicates the operation to perform to transform from the encoded
 * image to the display image.
 */
public enum JxlOrientation {
    JXL_ORIENT_IDENTITY(decode_h.JXL_ORIENT_IDENTITY()),
    JXL_ORIENT_FLIP_HORIZONTAL(decode_h.JXL_ORIENT_FLIP_HORIZONTAL()),
    JXL_ORIENT_ROTATE_180(decode_h.JXL_ORIENT_ROTATE_180()),
    JXL_ORIENT_FLIP_VERTICAL(decode_h.JXL_ORIENT_FLIP_VERTICAL()),
    JXL_ORIENT_TRANSPOSE(decode_h.JXL_ORIENT_TRANSPOSE()),
    JXL_ORIENT_ROTATE_90_CW(decode_h.JXL_ORIENT_ROTATE_90_CW()),
    JXL_ORIENT_ANTI_TRANSPOSE(decode_h.JXL_ORIENT_ANTI_TRANSPOSE()),
    JXL_ORIENT_ROTATE_90_CCW(decode_h.JXL_ORIENT_ROTATE_90_CCW());

    private final int val;

    JxlOrientation(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }
}
