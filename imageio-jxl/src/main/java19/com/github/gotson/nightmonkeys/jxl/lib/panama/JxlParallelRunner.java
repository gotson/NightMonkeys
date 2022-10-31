// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public interface JxlParallelRunner {

    int apply(java.lang.foreign.MemoryAddress runner_opaque, java.lang.foreign.MemoryAddress jpegxl_opaque, java.lang.foreign.MemoryAddress init, java.lang.foreign.MemoryAddress func, int start_range, int end_range);
    static MemorySegment allocate(JxlParallelRunner fi, MemorySession session) {
        return RuntimeHelper.upcallStub(JxlParallelRunner.class, fi, constants$1.JxlParallelRunner$FUNC, session);
    }
    static JxlParallelRunner ofAddress(MemoryAddress addr, MemorySession session) {
        MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
        return (java.lang.foreign.MemoryAddress _runner_opaque, java.lang.foreign.MemoryAddress _jpegxl_opaque, java.lang.foreign.MemoryAddress _init, java.lang.foreign.MemoryAddress _func, int _start_range, int _end_range) -> {
            try {
                return (int)constants$1.JxlParallelRunner$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)_runner_opaque, (java.lang.foreign.Addressable)_jpegxl_opaque, (java.lang.foreign.Addressable)_init, (java.lang.foreign.Addressable)_func, _start_range, _end_range);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


