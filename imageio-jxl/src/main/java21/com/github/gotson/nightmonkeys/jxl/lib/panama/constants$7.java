// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;

final class constants$7 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$7() {
    }

    static final VarHandle const$0 = constants$6.const$4.varHandle(MemoryLayout.PathElement.groupElement("ysize"));
    static final StructLayout const$1 = MemoryLayout.structLayout(
        JAVA_INT.withName("tps_numerator"),
        JAVA_INT.withName("tps_denominator"),
        JAVA_INT.withName("num_loops"),
        JAVA_INT.withName("have_timecodes")
    ).withName("");
    static final VarHandle const$2 = constants$7.const$1.varHandle(MemoryLayout.PathElement.groupElement("tps_numerator"));
    static final VarHandle const$3 = constants$7.const$1.varHandle(MemoryLayout.PathElement.groupElement("tps_denominator"));
    static final VarHandle const$4 = constants$7.const$1.varHandle(MemoryLayout.PathElement.groupElement("num_loops"));
    static final VarHandle const$5 = constants$7.const$1.varHandle(MemoryLayout.PathElement.groupElement("have_timecodes"));
}


