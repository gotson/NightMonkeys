// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

/**
 * {@snippet :
 * int (*JxlParallelRunInit)(void* jpegxl_opaque,unsigned long num_threads);
 *}
 */
public interface JxlParallelRunInit {

    int apply(java.lang.foreign.MemorySegment jpegxl_opaque, long num_threads);

    static MemorySegment allocate(JxlParallelRunInit fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$15.const$3, fi, constants$15.const$2, scope);
    }

    static JxlParallelRunInit ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return (java.lang.foreign.MemorySegment _jpegxl_opaque, long _num_threads) -> {
            try {
                return (int) constants$15.const$4.invokeExact(symbol, _jpegxl_opaque, _num_threads);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


