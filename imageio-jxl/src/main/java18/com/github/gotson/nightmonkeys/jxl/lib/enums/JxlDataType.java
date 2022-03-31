package com.github.gotson.nightmonkeys.jxl.lib.enums;

import com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h;

/**
 * Data type for the sample values per channel per pixel.
 */
public enum JxlDataType {
    /**
     * Use 32-bit single-precision floating point values, with range 0.0-1.0
     * (within gamut, may go outside this range for wide color gamut). Floating
     * point output, either JXL_TYPE_FLOAT or JXL_TYPE_FLOAT16, is recommended
     * for HDR and wide gamut images when color profile conversion is required.
     */
    JXL_TYPE_FLOAT(decode_h.JXL_TYPE_FLOAT()),

    /**
     * Use 1-bit packed in uint8_t, first pixel in LSB, padded to uint8_t per
     * row.
     */
    JXL_TYPE_BOOLEAN(decode_h.JXL_TYPE_BOOLEAN()),

    /**
     * Use type uint8_t. May clip wide color gamut data.
     */
    JXL_TYPE_UINT8(decode_h.JXL_TYPE_UINT8()),

    /**
     * Use type uint16_t. May clip wide color gamut data.
     */
    JXL_TYPE_UINT16(decode_h.JXL_TYPE_UINT16()),

    /**
     * Use type uint32_t. May clip wide color gamut data.
     */
    JXL_TYPE_UINT32(decode_h.JXL_TYPE_UINT32()),

    /**
     * Use 16-bit IEEE 754 half-precision floating point values
     */
    JXL_TYPE_FLOAT16(decode_h.JXL_TYPE_FLOAT16());

    private final int val;

    JxlDataType(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public int sizeBytes() {
        return switch (this) {
            case JXL_TYPE_FLOAT, JXL_TYPE_UINT32 -> 4;
            case JXL_TYPE_BOOLEAN, JXL_TYPE_UINT8 -> 1;
            case JXL_TYPE_UINT16, JXL_TYPE_FLOAT16 -> 2;
        };
    }
}
