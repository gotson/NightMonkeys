// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class constants$16 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$16() {
    }

    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "WebPIDelete",
        constants$0.const$3
    );
    static final FunctionDescriptor const$1 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_LONG
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "WebPIAppend",
        constants$16.const$1
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "WebPIUpdate",
        constants$16.const$1
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "WebPIDecGetRGB",
        constants$16.const$4
    );
}


