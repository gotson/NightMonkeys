// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import jdk.incubator.foreign.FunctionDescriptor;

import java.lang.invoke.MethodHandle;

class constants$5 {

    static final FunctionDescriptor JxlDecoderGetColorAsICCProfile$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JxlDecoderGetColorAsICCProfile$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderGetColorAsICCProfile",
        constants$5.JxlDecoderGetColorAsICCProfile$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderSetPreferredColorProfile$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderSetPreferredColorProfile$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderSetPreferredColorProfile",
        constants$5.JxlDecoderSetPreferredColorProfile$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderPreviewOutBufferSize$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderPreviewOutBufferSize$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderPreviewOutBufferSize",
        constants$5.JxlDecoderPreviewOutBufferSize$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderSetPreviewOutBuffer$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JxlDecoderSetPreviewOutBuffer$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderSetPreviewOutBuffer",
        constants$5.JxlDecoderSetPreviewOutBuffer$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderGetFrameHeader$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderGetFrameHeader$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderGetFrameHeader",
        constants$5.JxlDecoderGetFrameHeader$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderGetFrameName$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JxlDecoderGetFrameName$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderGetFrameName",
        constants$5.JxlDecoderGetFrameName$FUNC, false
    );
}


