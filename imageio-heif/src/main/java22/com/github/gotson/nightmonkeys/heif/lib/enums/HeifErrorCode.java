package com.github.gotson.nightmonkeys.heif.lib.enums;

import com.github.gotson.nightmonkeys.heif.lib.panama.heif_h;

public enum HeifErrorCode {
    HEIF_ERROR_OK(heif_h.heif_error_Ok()),
    HEIF_ERROR_INPUT_DOES_NOT_EXIST(heif_h.heif_error_Input_does_not_exist()),
    HEIF_ERROR_INVALID_INPUT(heif_h.heif_error_Invalid_input()),
    HEIF_ERROR_UNSUPPORTED_FILETYPE(heif_h.heif_error_Unsupported_filetype()),
    HEIF_ERROR_UNSUPPORTED_FEATURE(heif_h.heif_error_Unsupported_feature()),
    HEIF_ERROR_USAGE_ERROR(heif_h.heif_error_Usage_error()),
    HEIF_ERROR_MEMORY_ALLOCATION_ERROR(heif_h.heif_error_Memory_allocation_error()),
    HEIF_ERROR_DECODER_PLUGIN_ERROR(heif_h.heif_error_Decoder_plugin_error());

    private final int val;

    HeifErrorCode(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static HeifErrorCode fromId(int id) {
        for (HeifErrorCode type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
