// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.ValueLayout.*;
class constants$4 {

    static final FunctionDescriptor WebPIsRGBMode$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle WebPIsRGBMode$MH = RuntimeHelper.downcallHandle(
        "WebPIsRGBMode",
        constants$4.WebPIsRGBMode$FUNC, false
    );
    static final FunctionDescriptor WebPInitDecBufferInternal$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle WebPInitDecBufferInternal$MH = RuntimeHelper.downcallHandle(
        "WebPInitDecBufferInternal",
        constants$4.WebPInitDecBufferInternal$FUNC, false
    );
    static final FunctionDescriptor WebPInitDecBuffer$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle WebPInitDecBuffer$MH = RuntimeHelper.downcallHandle(
        "WebPInitDecBuffer",
        constants$4.WebPInitDecBuffer$FUNC, false
    );
    static final FunctionDescriptor WebPFreeDecBuffer$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle WebPFreeDecBuffer$MH = RuntimeHelper.downcallHandle(
        "WebPFreeDecBuffer",
        constants$4.WebPFreeDecBuffer$FUNC, false
    );
    static final FunctionDescriptor WebPINewDecoder$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle WebPINewDecoder$MH = RuntimeHelper.downcallHandle(
        "WebPINewDecoder",
        constants$4.WebPINewDecoder$FUNC, false
    );
    static final FunctionDescriptor WebPINewRGB$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle WebPINewRGB$MH = RuntimeHelper.downcallHandle(
        "WebPINewRGB",
        constants$4.WebPINewRGB$FUNC, false
    );
}


