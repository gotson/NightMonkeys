package com.github.gotson.nightmonkeys.heif.lib.enums;

import com.github.gotson.nightmonkeys.heif.lib.panama.heif_h;

public enum HeifCompressionFormat {
    HEIF_COMPRESSION_UNDEFINED(heif_h.heif_compression_undefined()),
    HEIF_COMPRESSION_HEVC(heif_h.heif_compression_HEVC()),
    HEIF_COMPRESSION_AVC(heif_h.heif_compression_AVC()),
    HEIF_COMPRESSION_JPEG(heif_h.heif_compression_JPEG()),
    HEIF_COMPRESSION_AV1(heif_h.heif_compression_AV1()),
    HEIF_COMPRESSION_VVC(heif_h.heif_compression_VVC()),
    HEIF_COMPRESSION_EVC(heif_h.heif_compression_EVC()),
    HEIF_COMPRESSION_JPEG2000(heif_h.heif_compression_JPEG2000()),
    HEIF_COMPRESSION_UNCOMPRESSED(heif_h.heif_compression_uncompressed()),
    ;

    private final int val;

    HeifCompressionFormat(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static HeifCompressionFormat fromId(int id) {
        for (HeifCompressionFormat type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
