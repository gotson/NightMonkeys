package com.github.gotson.nightmonkeys.heif.lib;

import com.github.gotson.nightmonkeys.heif.lib.enums.HeifErrorCode;
import com.github.gotson.nightmonkeys.heif.lib.enums.HeifSuberrorCode;
import com.github.gotson.nightmonkeys.heif.lib.panama.heif_error;

import java.lang.foreign.MemorySegment;

public record HeifError(
    HeifErrorCode code,
    HeifSuberrorCode subcode,
    String message
) {
    public static HeifError from(MemorySegment segment) {
        return new HeifError(
            HeifErrorCode.fromId(heif_error.code(segment)),
            HeifSuberrorCode.fromId(heif_error.subcode(segment)),
            heif_error.message(segment).getString(0)
        );
    }
}
