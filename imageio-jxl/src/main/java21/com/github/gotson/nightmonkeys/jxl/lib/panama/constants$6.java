// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;

final class constants$6 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$6() {
    }

    static final VarHandle const$0 = constants$5.const$3.varHandle(MemoryLayout.PathElement.groupElement("primaries"));
    static final VarHandle const$1 = constants$5.const$3.varHandle(MemoryLayout.PathElement.groupElement("transfer_function"));
    static final VarHandle const$2 = constants$5.const$3.varHandle(MemoryLayout.PathElement.groupElement("gamma"));
    static final VarHandle const$3 = constants$5.const$3.varHandle(MemoryLayout.PathElement.groupElement("rendering_intent"));
    static final StructLayout const$4 = MemoryLayout.structLayout(
        JAVA_INT.withName("xsize"),
        JAVA_INT.withName("ysize")
    ).withName("");
    static final VarHandle const$5 = constants$6.const$4.varHandle(MemoryLayout.PathElement.groupElement("xsize"));
}


