// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.JAVA_BYTE;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class constants$1 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$1() {
    }

    static final VarHandle const$0 = constants$0.const$2.varHandle(MemoryLayout.PathElement.groupElement("__routine"));
    static final VarHandle const$1 = constants$0.const$2.varHandle(MemoryLayout.PathElement.groupElement("__arg"));
    static final VarHandle const$2 = constants$0.const$2.varHandle(MemoryLayout.PathElement.groupElement("__next"));
    static final StructLayout const$3 = MemoryLayout.structLayout(
        JAVA_LONG.withName("__sig"),
        MemoryLayout.sequenceLayout(56, JAVA_BYTE).withName("__opaque")
    ).withName("_opaque_pthread_attr_t");
    static final VarHandle const$4 = constants$1.const$3.varHandle(MemoryLayout.PathElement.groupElement("__sig"));
    static final StructLayout const$5 = MemoryLayout.structLayout(
        JAVA_LONG.withName("__sig"),
        MemoryLayout.sequenceLayout(40, JAVA_BYTE).withName("__opaque")
    ).withName("_opaque_pthread_cond_t");
}


