// Generated by jextract

package com.github.gotson.nightmonkeys.heif.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;

import static java.lang.foreign.ValueLayout.*;

final class constants$49 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$49() {
    }

    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "heif_have_encoder_for_format",
        constants$48.const$4
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "heif_context_get_encoder_for_format",
        constants$16.const$2
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "heif_encoder_release",
        constants$0.const$3
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "heif_encoder_get_name",
        constants$46.const$3
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(MemoryLayout.structLayout(
            JAVA_INT.withName("code"),
            JAVA_INT.withName("subcode"),
            RuntimeHelper.POINTER.withName("message")
        ).withName("heif_error"),
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "heif_encoder_set_lossy_quality",
        constants$49.const$4
    );
}


