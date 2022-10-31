// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public interface JxlParallelRunFunction {

    void apply(java.lang.foreign.MemoryAddress jpegxl_opaque, int value, long thread_id);
    static MemorySegment allocate(JxlParallelRunFunction fi, MemorySession session) {
        return RuntimeHelper.upcallStub(JxlParallelRunFunction.class, fi, constants$1.JxlParallelRunFunction$FUNC, session);
    }
    static JxlParallelRunFunction ofAddress(MemoryAddress addr, MemorySession session) {
        MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
        return (java.lang.foreign.MemoryAddress _jpegxl_opaque, int _value, long _thread_id) -> {
            try {
                constants$1.JxlParallelRunFunction$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)_jpegxl_opaque, _value, _thread_id);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


