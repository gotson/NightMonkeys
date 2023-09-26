// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.VarHandle;

/**
 * {@snippet :
 * struct _opaque_pthread_t {
 *     long __sig;
 *     struct __darwin_pthread_handler_rec* __cleanup_stack;
 *     char __opaque[8176];
 * };
 *}
 */
public class _opaque_pthread_t {

    public static MemoryLayout $LAYOUT() {
        return constants$4.const$1;
    }

    public static VarHandle __sig$VH() {
        return constants$4.const$2;
    }

    /**
     * Getter for field:
     * {@snippet :
     * long __sig;
     *}
     */
    public static long __sig$get(MemorySegment seg) {
        return (long) constants$4.const$2.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * long __sig;
     *}
     */
    public static void __sig$set(MemorySegment seg, long x) {
        constants$4.const$2.set(seg, x);
    }

    public static long __sig$get(MemorySegment seg, long index) {
        return (long) constants$4.const$2.get(seg.asSlice(index * sizeof()));
    }

    public static void __sig$set(MemorySegment seg, long index, long x) {
        constants$4.const$2.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle __cleanup_stack$VH() {
        return constants$4.const$3;
    }

    /**
     * Getter for field:
     * {@snippet :
     * struct __darwin_pthread_handler_rec* __cleanup_stack;
     *}
     */
    public static MemorySegment __cleanup_stack$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment) constants$4.const$3.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * struct __darwin_pthread_handler_rec* __cleanup_stack;
     *}
     */
    public static void __cleanup_stack$set(MemorySegment seg, MemorySegment x) {
        constants$4.const$3.set(seg, x);
    }

    public static MemorySegment __cleanup_stack$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment) constants$4.const$3.get(seg.asSlice(index * sizeof()));
    }

    public static void __cleanup_stack$set(MemorySegment seg, long index, MemorySegment x) {
        constants$4.const$3.set(seg.asSlice(index * sizeof()), x);
    }

    public static MemorySegment __opaque$slice(MemorySegment seg) {
        return seg.asSlice(16, 8176);
    }

    public static long sizeof() {
        return $LAYOUT().byteSize();
    }

    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate($LAYOUT());
    }

    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }

    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) {
        return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena);
    }
}


