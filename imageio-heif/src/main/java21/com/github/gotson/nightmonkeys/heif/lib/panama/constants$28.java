// Generated by jextract

package com.github.gotson.nightmonkeys.heif.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;

import static java.lang.foreign.ValueLayout.*;

final class constants$28 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$28() {
    }

    static final VarHandle const$0 = constants$26.const$2.varHandle(MemoryLayout.PathElement.groupElement("color_primary_blue_x"));
    static final VarHandle const$1 = constants$26.const$2.varHandle(MemoryLayout.PathElement.groupElement("color_primary_blue_y"));
    static final VarHandle const$2 = constants$26.const$2.varHandle(MemoryLayout.PathElement.groupElement("color_primary_white_x"));
    static final VarHandle const$3 = constants$26.const$2.varHandle(MemoryLayout.PathElement.groupElement("color_primary_white_y"));
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(MemoryLayout.structLayout(
            JAVA_INT.withName("code"),
            JAVA_INT.withName("subcode"),
            RuntimeHelper.POINTER.withName("message")
        ).withName("heif_error"),
        RuntimeHelper.POINTER,
        JAVA_SHORT
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "heif_nclx_color_profile_set_color_primaries",
        constants$28.const$4
    );
}


