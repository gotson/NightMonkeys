// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class constants$10 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$10() {
    }

    static final FunctionDescriptor const$0 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_LONG,
        RuntimeHelper.POINTER,
        JAVA_LONG,
        JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_LONG,
        JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_LONG,
        JAVA_INT
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "WebPDecodeYUVInto",
        constants$10.const$0
    );
    static final StructLayout const$2 = MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("rgba"),
        JAVA_INT.withName("stride"),
        MemoryLayout.paddingLayout(4),
        JAVA_LONG.withName("size")
    ).withName("WebPRGBABuffer");
    static final VarHandle const$3 = constants$10.const$2.varHandle(MemoryLayout.PathElement.groupElement("rgba"));
    static final VarHandle const$4 = constants$10.const$2.varHandle(MemoryLayout.PathElement.groupElement("stride"));
    static final VarHandle const$5 = constants$10.const$2.varHandle(MemoryLayout.PathElement.groupElement("size"));
}


