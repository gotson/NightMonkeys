// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import jdk.incubator.foreign.FunctionDescriptor;

import java.lang.invoke.MethodHandle;

class constants$7 {

    static final FunctionDescriptor JxlDecoderSetImageOutCallback$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderSetImageOutCallback$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderSetImageOutCallback",
        constants$7.JxlDecoderSetImageOutCallback$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderExtraChannelBufferSize$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JxlDecoderExtraChannelBufferSize$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderExtraChannelBufferSize",
        constants$7.JxlDecoderExtraChannelBufferSize$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderSetExtraChannelBuffer$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JxlDecoderSetExtraChannelBuffer$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderSetExtraChannelBuffer",
        constants$7.JxlDecoderSetExtraChannelBuffer$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderSetJPEGBuffer$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JxlDecoderSetJPEGBuffer$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderSetJPEGBuffer",
        constants$7.JxlDecoderSetJPEGBuffer$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderReleaseJPEGBuffer$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderReleaseJPEGBuffer$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderReleaseJPEGBuffer",
        constants$7.JxlDecoderReleaseJPEGBuffer$FUNC, false
    );
    static final FunctionDescriptor JxlDecoderFlushImage$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JxlDecoderFlushImage$MH = RuntimeHelper.downcallHandle(
        "JxlDecoderFlushImage",
        constants$7.JxlDecoderFlushImage$FUNC, false
    );
}


