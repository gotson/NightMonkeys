// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class constants$8 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$8() {
    }

    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "WebPDecodeARGB",
        constants$7.const$4
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "WebPDecodeBGRA",
        constants$7.const$4
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "WebPDecodeRGB",
        constants$7.const$4
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "WebPDecodeBGR",
        constants$7.const$4
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_LONG,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "WebPDecodeYUV",
        constants$8.const$4
    );
}


