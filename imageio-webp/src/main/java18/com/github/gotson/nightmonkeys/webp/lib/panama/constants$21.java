// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.MemorySegment;

import java.lang.invoke.MethodHandle;

class constants$21 {

    static final FunctionDescriptor WebPAnimDecoderHasMoreFrames$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle WebPAnimDecoderHasMoreFrames$MH = RuntimeHelper.downcallHandle(
        "WebPAnimDecoderHasMoreFrames",
        constants$21.WebPAnimDecoderHasMoreFrames$FUNC, false
    );
    static final FunctionDescriptor WebPAnimDecoderReset$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle WebPAnimDecoderReset$MH = RuntimeHelper.downcallHandle(
        "WebPAnimDecoderReset",
        constants$21.WebPAnimDecoderReset$FUNC, false
    );
    static final FunctionDescriptor WebPAnimDecoderGetDemuxer$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle WebPAnimDecoderGetDemuxer$MH = RuntimeHelper.downcallHandle(
        "WebPAnimDecoderGetDemuxer",
        constants$21.WebPAnimDecoderGetDemuxer$FUNC, false
    );
    static final FunctionDescriptor WebPAnimDecoderDelete$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle WebPAnimDecoderDelete$MH = RuntimeHelper.downcallHandle(
        "WebPAnimDecoderDelete",
        constants$21.WebPAnimDecoderDelete$FUNC, false
    );
    static final MemorySegment __DARWIN_SUF_64_BIT_INO_T$SEGMENT = RuntimeHelper.CONSTANT_ALLOCATOR.allocateUtf8String("$INODE64");
    static final MemorySegment __DARWIN_SUF_1050$SEGMENT = RuntimeHelper.CONSTANT_ALLOCATOR.allocateUtf8String("$1050");
}


