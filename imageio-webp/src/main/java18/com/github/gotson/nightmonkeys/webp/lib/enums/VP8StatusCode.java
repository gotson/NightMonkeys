package com.github.gotson.nightmonkeys.webp.lib.enums;


import com.github.gotson.nightmonkeys.webp.lib.panama.decode_h;

public enum VP8StatusCode {
    VP8_STATUS_OK(decode_h.VP8_STATUS_OK()),
    VP8_STATUS_OUT_OF_MEMORY(decode_h.VP8_STATUS_OUT_OF_MEMORY()),
    VP8_STATUS_INVALID_PARAM(decode_h.VP8_STATUS_INVALID_PARAM()),
    VP8_STATUS_BITSTREAM_ERROR(decode_h.VP8_STATUS_BITSTREAM_ERROR()),
    VP8_STATUS_UNSUPPORTED_FEATURE(decode_h.VP8_STATUS_UNSUPPORTED_FEATURE()),
    VP8_STATUS_SUSPENDED(decode_h.VP8_STATUS_SUSPENDED()),
    VP8_STATUS_USER_ABORT(decode_h.VP8_STATUS_USER_ABORT()),
    VP8_STATUS_NOT_ENOUGH_DATA(decode_h.VP8_STATUS_NOT_ENOUGH_DATA());

    private final int val;

    VP8StatusCode(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static VP8StatusCode fromId(int id) {
        for (VP8StatusCode type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
