// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$1 {

    static final FunctionDescriptor JxlParallelRunFunction$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JxlParallelRunFunction$MH = RuntimeHelper.downcallHandle(
        constants$1.JxlParallelRunFunction$FUNC
    );
    static final FunctionDescriptor JxlParallelRunner$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JxlParallelRunner$MH = RuntimeHelper.downcallHandle(
        constants$1.JxlParallelRunner$FUNC
    );
    static final FunctionDescriptor JxlDecoderVersion$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle JxlDecoderVersion$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderVersion",
        constants$1.JxlDecoderVersion$FUNC
    );
    static final FunctionDescriptor JxlSignatureCheck$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JxlSignatureCheck$MH = RuntimeHelper.downcallHandle(
        "JxlSignatureCheck",
        constants$1.JxlSignatureCheck$FUNC
    );
}

