// Generated by jextract

package com.github.gotson.nightmonkeys.heif.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;

import static java.lang.foreign.ValueLayout.*;

final class constants$40 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$40() {
    }

    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "heif_image_scale_image",
        constants$37.const$3
    );
    static final FunctionDescriptor const$1 = FunctionDescriptor.of(MemoryLayout.structLayout(
            JAVA_INT.withName("code"),
            JAVA_INT.withName("subcode"),
            RuntimeHelper.POINTER.withName("message")
        ).withName("heif_error"),
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_LONG
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "heif_image_set_raw_color_profile",
        constants$40.const$1
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "heif_image_set_nclx_color_profile",
        constants$8.const$1
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "heif_image_get_decoding_warnings",
        constants$23.const$1
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.ofVoid(
        RuntimeHelper.POINTER,
        MemoryLayout.structLayout(
            JAVA_INT.withName("code"),
            JAVA_INT.withName("subcode"),
            RuntimeHelper.POINTER.withName("message")
        ).withName("heif_error")
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "heif_image_add_decoding_warning",
        constants$40.const$5
    );
}


