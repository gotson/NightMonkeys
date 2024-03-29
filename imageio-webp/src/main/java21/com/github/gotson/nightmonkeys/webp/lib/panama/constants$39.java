// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class constants$39 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$39() {
    }

    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "WebPDemuxPrevFrame",
        constants$38.const$5
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "WebPDemuxReleaseIterator",
        constants$0.const$3
    );
    static final StructLayout const$2 = MemoryLayout.structLayout(
        JAVA_INT.withName("chunk_num"),
        JAVA_INT.withName("num_chunks"),
        MemoryLayout.structLayout(
            RuntimeHelper.POINTER.withName("bytes"),
            JAVA_LONG.withName("size")
        ).withName("chunk"),
        MemoryLayout.sequenceLayout(6, JAVA_INT).withName("pad"),
        RuntimeHelper.POINTER.withName("private_")
    ).withName("WebPChunkIterator");
    static final VarHandle const$3 = constants$39.const$2.varHandle(MemoryLayout.PathElement.groupElement("chunk_num"));
    static final VarHandle const$4 = constants$39.const$2.varHandle(MemoryLayout.PathElement.groupElement("num_chunks"));
    static final VarHandle const$5 = constants$39.const$2.varHandle(MemoryLayout.PathElement.groupElement("private_"));
}


