// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$9 {

    static final FunctionDescriptor strcpy$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle strcpy$MH = RuntimeHelper.downcallHandle(
        "strcpy",
        constants$9.strcpy$FUNC
    );
    static final FunctionDescriptor strcspn$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle strcspn$MH = RuntimeHelper.downcallHandle(
        "strcspn",
        constants$9.strcspn$FUNC
    );
    static final FunctionDescriptor strerror$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle strerror$MH = RuntimeHelper.downcallHandle(
        "strerror",
        constants$9.strerror$FUNC
    );
    static final FunctionDescriptor strlen$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle strlen$MH = RuntimeHelper.downcallHandle(
        "strlen",
        constants$9.strlen$FUNC
    );
    static final FunctionDescriptor strncat$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle strncat$MH = RuntimeHelper.downcallHandle(
        "strncat",
        constants$9.strncat$FUNC
    );
    static final FunctionDescriptor strncmp$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle strncmp$MH = RuntimeHelper.downcallHandle(
        "strncmp",
        constants$9.strncmp$FUNC
    );
}

