// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public interface JxlImageOutCallback {

    void apply(java.lang.foreign.MemoryAddress opaque, long x, long y, long num_pixels, java.lang.foreign.MemoryAddress pixels);
    static MemorySegment allocate(JxlImageOutCallback fi, MemorySession session) {
        return RuntimeHelper.upcallStub(JxlImageOutCallback.class, fi, constants$6.JxlImageOutCallback$FUNC, session);
    }
    static JxlImageOutCallback ofAddress(MemoryAddress addr, MemorySession session) {
        MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
        return (java.lang.foreign.MemoryAddress _opaque, long _x, long _y, long _num_pixels, java.lang.foreign.MemoryAddress _pixels) -> {
            try {
                constants$6.JxlImageOutCallback$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)_opaque, _x, _y, _num_pixels, (java.lang.foreign.Addressable)_pixels);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}

