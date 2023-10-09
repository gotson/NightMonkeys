// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.VarHandle;

/**
 * {@snippet :
 * struct {
 *     uint32_t xsize;
 *     uint32_t ysize;
 * };
 *}
 */
public class JxlPreviewHeader {

    public static MemoryLayout $LAYOUT() {
        return constants$6.const$4;
    }

    public static VarHandle xsize$VH() {
        return constants$6.const$5;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint32_t xsize;
     *}
     */
    public static int xsize$get(MemorySegment seg) {
        return (int) constants$6.const$5.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint32_t xsize;
     *}
     */
    public static void xsize$set(MemorySegment seg, int x) {
        constants$6.const$5.set(seg, x);
    }

    public static int xsize$get(MemorySegment seg, long index) {
        return (int) constants$6.const$5.get(seg.asSlice(index * sizeof()));
    }

    public static void xsize$set(MemorySegment seg, long index, int x) {
        constants$6.const$5.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle ysize$VH() {
        return constants$7.const$0;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint32_t ysize;
     *}
     */
    public static int ysize$get(MemorySegment seg) {
        return (int) constants$7.const$0.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint32_t ysize;
     *}
     */
    public static void ysize$set(MemorySegment seg, int x) {
        constants$7.const$0.set(seg, x);
    }

    public static int ysize$get(MemorySegment seg, long index) {
        return (int) constants$7.const$0.get(seg.asSlice(index * sizeof()));
    }

    public static void ysize$set(MemorySegment seg, long index, int x) {
        constants$7.const$0.set(seg.asSlice(index * sizeof()), x);
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

