// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import jdk.incubator.foreign.FunctionDescriptor;

import java.lang.invoke.MethodHandle;

class constants$2 {

    static final FunctionDescriptor JxlDecoderCreate$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderCreate$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderCreate",
        constants$2.JxlDecoderCreate$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderReset$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderReset$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderReset",
        constants$2.JxlDecoderReset$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderDestroy$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderDestroy$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderDestroy",
        constants$2.JxlDecoderDestroy$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderRewind$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderRewind$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderRewind",
        constants$2.JxlDecoderRewind$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderSkipFrames$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JxlDecoderSkipFrames$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderSkipFrames",
        constants$2.JxlDecoderSkipFrames$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderDefaultPixelFormat$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderDefaultPixelFormat$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderDefaultPixelFormat",
        constants$2.JxlDecoderDefaultPixelFormat$FUNC, false
    );
}


