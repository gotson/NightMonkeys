// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import jdk.incubator.foreign.FunctionDescriptor;

import java.lang.invoke.MethodHandle;

class constants$0 {

    static final FunctionDescriptor jpegxl_alloc_func$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle jpegxl_alloc_func$MH = RuntimeHelper.downcallHandle(
        constants$0.jpegxl_alloc_func$FUNC, false
    );
    static final FunctionDescriptor jpegxl_free_func$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle jpegxl_free_func$MH = RuntimeHelper.downcallHandle(
        constants$0.jpegxl_free_func$FUNC, false
    );
    static final FunctionDescriptor JxlParallelRunInit$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JxlParallelRunInit$MH = RuntimeHelper.downcallHandle(
        constants$0.JxlParallelRunInit$FUNC, false
    );
}


