// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.ValueLayout.*;
class constants$3 {

    static final FunctionDescriptor WebPDecodeBGRAInto$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle WebPDecodeBGRAInto$MH = RuntimeHelper.downcallHandle(
        "WebPDecodeBGRAInto",
        constants$3.WebPDecodeBGRAInto$FUNC, false
    );
    static final FunctionDescriptor WebPDecodeRGBInto$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle WebPDecodeRGBInto$MH = RuntimeHelper.downcallHandle(
        "WebPDecodeRGBInto",
        constants$3.WebPDecodeRGBInto$FUNC, false
    );
    static final FunctionDescriptor WebPDecodeBGRInto$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle WebPDecodeBGRInto$MH = RuntimeHelper.downcallHandle(
        "WebPDecodeBGRInto",
        constants$3.WebPDecodeBGRInto$FUNC, false
    );
    static final FunctionDescriptor WebPDecodeYUVInto$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle WebPDecodeYUVInto$MH = RuntimeHelper.downcallHandle(
        "WebPDecodeYUVInto",
        constants$3.WebPDecodeYUVInto$FUNC, false
    );
    static final FunctionDescriptor WebPIsPremultipliedMode$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle WebPIsPremultipliedMode$MH = RuntimeHelper.downcallHandle(
        "WebPIsPremultipliedMode",
        constants$3.WebPIsPremultipliedMode$FUNC, false
    );
    static final FunctionDescriptor WebPIsAlphaMode$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle WebPIsAlphaMode$MH = RuntimeHelper.downcallHandle(
        "WebPIsAlphaMode",
        constants$3.WebPIsAlphaMode$FUNC, false
    );
}


