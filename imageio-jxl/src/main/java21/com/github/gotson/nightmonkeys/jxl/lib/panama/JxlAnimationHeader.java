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
 *     uint32_t tps_numerator;
 *     uint32_t tps_denominator;
 *     uint32_t num_loops;
 *     int have_timecodes;
 * };
 *}
 */
public class JxlAnimationHeader {

    public static MemoryLayout $LAYOUT() {
        return constants$7.const$1;
    }

    public static VarHandle tps_numerator$VH() {
        return constants$7.const$2;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint32_t tps_numerator;
     *}
     */
    public static int tps_numerator$get(MemorySegment seg) {
        return (int) constants$7.const$2.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint32_t tps_numerator;
     *}
     */
    public static void tps_numerator$set(MemorySegment seg, int x) {
        constants$7.const$2.set(seg, x);
    }

    public static int tps_numerator$get(MemorySegment seg, long index) {
        return (int) constants$7.const$2.get(seg.asSlice(index * sizeof()));
    }

    public static void tps_numerator$set(MemorySegment seg, long index, int x) {
        constants$7.const$2.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle tps_denominator$VH() {
        return constants$7.const$3;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint32_t tps_denominator;
     *}
     */
    public static int tps_denominator$get(MemorySegment seg) {
        return (int) constants$7.const$3.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint32_t tps_denominator;
     *}
     */
    public static void tps_denominator$set(MemorySegment seg, int x) {
        constants$7.const$3.set(seg, x);
    }

    public static int tps_denominator$get(MemorySegment seg, long index) {
        return (int) constants$7.const$3.get(seg.asSlice(index * sizeof()));
    }

    public static void tps_denominator$set(MemorySegment seg, long index, int x) {
        constants$7.const$3.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle num_loops$VH() {
        return constants$7.const$4;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint32_t num_loops;
     *}
     */
    public static int num_loops$get(MemorySegment seg) {
        return (int) constants$7.const$4.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint32_t num_loops;
     *}
     */
    public static void num_loops$set(MemorySegment seg, int x) {
        constants$7.const$4.set(seg, x);
    }

    public static int num_loops$get(MemorySegment seg, long index) {
        return (int) constants$7.const$4.get(seg.asSlice(index * sizeof()));
    }

    public static void num_loops$set(MemorySegment seg, long index, int x) {
        constants$7.const$4.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle have_timecodes$VH() {
        return constants$7.const$5;
    }

    /**
     * Getter for field:
     * {@snippet :
     * int have_timecodes;
     *}
     */
    public static int have_timecodes$get(MemorySegment seg) {
        return (int) constants$7.const$5.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * int have_timecodes;
     *}
     */
    public static void have_timecodes$set(MemorySegment seg, int x) {
        constants$7.const$5.set(seg, x);
    }

    public static int have_timecodes$get(MemorySegment seg, long index) {
        return (int) constants$7.const$5.get(seg.asSlice(index * sizeof()));
    }

    public static void have_timecodes$set(MemorySegment seg, long index, int x) {
        constants$7.const$5.set(seg.asSlice(index * sizeof()), x);
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


