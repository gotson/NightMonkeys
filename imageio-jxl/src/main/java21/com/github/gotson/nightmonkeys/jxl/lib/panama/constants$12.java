// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class constants$12 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$12() {
    }

    static final VarHandle const$0 = constants$11.const$1.varHandle(MemoryLayout.PathElement.groupElement("name_length"));
    static final VarHandle const$1 = constants$11.const$1.varHandle(MemoryLayout.PathElement.groupElement("alpha_premultiplied"));
    static final VarHandle const$2 = constants$11.const$1.varHandle(MemoryLayout.PathElement.groupElement("cfa_channel"));
    static final StructLayout const$3 = MemoryLayout.structLayout(
        JAVA_LONG.withName("extensions")
    ).withName("");
    static final VarHandle const$4 = constants$12.const$3.varHandle(MemoryLayout.PathElement.groupElement("extensions"));
    static final StructLayout const$5 = MemoryLayout.structLayout(
        JAVA_INT.withName("duration"),
        JAVA_INT.withName("timecode"),
        JAVA_INT.withName("name_length"),
        JAVA_INT.withName("is_last")
    ).withName("");
}


