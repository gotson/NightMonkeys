// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.ValueLayout.*;
class constants$7 {

    static final FunctionDescriptor WebPInitDecoderConfig$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle WebPInitDecoderConfig$MH = RuntimeHelper.downcallHandle(
        "WebPInitDecoderConfig",
        constants$7.WebPInitDecoderConfig$FUNC, false
    );
    static final FunctionDescriptor WebPIDecode$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle WebPIDecode$MH = RuntimeHelper.downcallHandle(
        "WebPIDecode",
        constants$7.WebPIDecode$FUNC, false
    );
    static final FunctionDescriptor WebPDecode$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle WebPDecode$MH = RuntimeHelper.downcallHandle(
        "WebPDecode",
        constants$7.WebPDecode$FUNC, false
    );
    static final MemorySegment __DARWIN_SUF_64_BIT_INO_T$SEGMENT = RuntimeHelper.CONSTANT_ALLOCATOR.allocateUtf8String("$INODE64");
    static final MemorySegment __DARWIN_SUF_1050$SEGMENT = RuntimeHelper.CONSTANT_ALLOCATOR.allocateUtf8String("$1050");
    static final MemorySegment __DARWIN_SUF_EXTSN$SEGMENT = RuntimeHelper.CONSTANT_ALLOCATOR.allocateUtf8String("$DARWIN_EXTSN");
}

