// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

final class constants$22 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$22() {
    }

    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "JxlDecoderSetPreferredColorProfile",
        constants$18.const$2
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "JxlDecoderPreviewOutBufferSize",
        constants$18.const$4
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_LONG
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "JxlDecoderSetPreviewOutBuffer",
        constants$22.const$2
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "JxlDecoderGetFrameHeader",
        constants$18.const$2
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "JxlDecoderGetFrameName",
        constants$20.const$0
    );
}


