// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class constants$15 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$15() {
    }

    static final FunctionDescriptor const$0 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_LONG,
        JAVA_INT
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "WebPINewRGB",
        constants$15.const$0
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_LONG,
        JAVA_INT,
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
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "WebPINewYUVA",
        constants$15.const$2
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(RuntimeHelper.POINTER,
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
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "WebPINewYUV",
        constants$15.const$4
    );
}


