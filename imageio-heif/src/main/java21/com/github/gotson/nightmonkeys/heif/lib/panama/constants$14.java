// Generated by jextract

package com.github.gotson.nightmonkeys.heif.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;

import static java.lang.foreign.ValueLayout.*;

final class constants$14 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$14() {
    }

    static final VarHandle const$0 = constants$11.const$5.varHandle(MemoryLayout.PathElement.groupElement("seek"));
    static final MethodHandle const$1 = RuntimeHelper.upcallHandle(heif_reader.wait_for_file_size.class, "apply", constants$13.const$3);
    static final VarHandle const$2 = constants$11.const$5.varHandle(MemoryLayout.PathElement.groupElement("wait_for_file_size"));
    static final FunctionDescriptor const$3 = FunctionDescriptor.of(MemoryLayout.structLayout(
            JAVA_INT.withName("code"),
            JAVA_INT.withName("subcode"),
            RuntimeHelper.POINTER.withName("message")
        ).withName("heif_error"),
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "heif_context_read_from_file",
        constants$14.const$3
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.of(MemoryLayout.structLayout(
            JAVA_INT.withName("code"),
            JAVA_INT.withName("subcode"),
            RuntimeHelper.POINTER.withName("message")
        ).withName("heif_error"),
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_LONG,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "heif_context_read_from_memory",
        constants$14.const$5
    );
}


