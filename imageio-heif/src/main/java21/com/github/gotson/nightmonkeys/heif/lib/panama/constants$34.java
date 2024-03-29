// Generated by jextract

package com.github.gotson.nightmonkeys.heif.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;

import static java.lang.foreign.ValueLayout.*;

final class constants$34 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$34() {
    }

    static final VarHandle const$0 = constants$33.const$4.varHandle(MemoryLayout.PathElement.groupElement("preferred_chroma_downsampling_algorithm"));
    static final VarHandle const$1 = constants$33.const$4.varHandle(MemoryLayout.PathElement.groupElement("preferred_chroma_upsampling_algorithm"));
    static final VarHandle const$2 = constants$33.const$4.varHandle(MemoryLayout.PathElement.groupElement("only_use_preferred_chroma_algorithm"));
    static final StructLayout const$3 = MemoryLayout.structLayout(
        JAVA_BYTE.withName("version"),
        JAVA_BYTE.withName("ignore_transformations"),
        MemoryLayout.paddingLayout(6),
        RuntimeHelper.POINTER.withName("start_progress"),
        RuntimeHelper.POINTER.withName("on_progress"),
        RuntimeHelper.POINTER.withName("end_progress"),
        RuntimeHelper.POINTER.withName("progress_user_data"),
        JAVA_BYTE.withName("convert_hdr_to_8bit"),
        JAVA_BYTE.withName("strict_decoding"),
        MemoryLayout.paddingLayout(6),
        RuntimeHelper.POINTER.withName("decoder_id"),
        MemoryLayout.structLayout(
            JAVA_BYTE.withName("version"),
            MemoryLayout.paddingLayout(3),
            JAVA_INT.withName("preferred_chroma_downsampling_algorithm"),
            JAVA_INT.withName("preferred_chroma_upsampling_algorithm"),
            JAVA_BYTE.withName("only_use_preferred_chroma_algorithm"),
            MemoryLayout.paddingLayout(3)
        ).withName("color_conversion_options")
    ).withName("heif_decoding_options");
    static final VarHandle const$4 = constants$34.const$3.varHandle(MemoryLayout.PathElement.groupElement("version"));
    static final VarHandle const$5 = constants$34.const$3.varHandle(MemoryLayout.PathElement.groupElement("ignore_transformations"));
}


