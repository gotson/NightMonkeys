// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.VarHandle;

/**
 * {@snippet :
 * struct WebPAnimInfo {
 *     uint32_t canvas_width;
 *     uint32_t canvas_height;
 *     uint32_t loop_count;
 *     uint32_t bgcolor;
 *     uint32_t frame_count;
 *     uint32_t pad[4];
 * };
 *}
 */
public class WebPAnimInfo {

    public static MemoryLayout $LAYOUT() {
        return constants$41.const$5;
    }

    public static VarHandle canvas_width$VH() {
        return constants$42.const$0;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint32_t canvas_width;
     *}
     */
    public static int canvas_width$get(MemorySegment seg) {
        return (int) constants$42.const$0.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint32_t canvas_width;
     *}
     */
    public static void canvas_width$set(MemorySegment seg, int x) {
        constants$42.const$0.set(seg, x);
    }

    public static int canvas_width$get(MemorySegment seg, long index) {
        return (int) constants$42.const$0.get(seg.asSlice(index * sizeof()));
    }

    public static void canvas_width$set(MemorySegment seg, long index, int x) {
        constants$42.const$0.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle canvas_height$VH() {
        return constants$42.const$1;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint32_t canvas_height;
     *}
     */
    public static int canvas_height$get(MemorySegment seg) {
        return (int) constants$42.const$1.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint32_t canvas_height;
     *}
     */
    public static void canvas_height$set(MemorySegment seg, int x) {
        constants$42.const$1.set(seg, x);
    }

    public static int canvas_height$get(MemorySegment seg, long index) {
        return (int) constants$42.const$1.get(seg.asSlice(index * sizeof()));
    }

    public static void canvas_height$set(MemorySegment seg, long index, int x) {
        constants$42.const$1.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle loop_count$VH() {
        return constants$42.const$2;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint32_t loop_count;
     *}
     */
    public static int loop_count$get(MemorySegment seg) {
        return (int) constants$42.const$2.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint32_t loop_count;
     *}
     */
    public static void loop_count$set(MemorySegment seg, int x) {
        constants$42.const$2.set(seg, x);
    }

    public static int loop_count$get(MemorySegment seg, long index) {
        return (int) constants$42.const$2.get(seg.asSlice(index * sizeof()));
    }

    public static void loop_count$set(MemorySegment seg, long index, int x) {
        constants$42.const$2.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle bgcolor$VH() {
        return constants$42.const$3;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint32_t bgcolor;
     *}
     */
    public static int bgcolor$get(MemorySegment seg) {
        return (int) constants$42.const$3.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint32_t bgcolor;
     *}
     */
    public static void bgcolor$set(MemorySegment seg, int x) {
        constants$42.const$3.set(seg, x);
    }

    public static int bgcolor$get(MemorySegment seg, long index) {
        return (int) constants$42.const$3.get(seg.asSlice(index * sizeof()));
    }

    public static void bgcolor$set(MemorySegment seg, long index, int x) {
        constants$42.const$3.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle frame_count$VH() {
        return constants$42.const$4;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint32_t frame_count;
     *}
     */
    public static int frame_count$get(MemorySegment seg) {
        return (int) constants$42.const$4.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint32_t frame_count;
     *}
     */
    public static void frame_count$set(MemorySegment seg, int x) {
        constants$42.const$4.set(seg, x);
    }

    public static int frame_count$get(MemorySegment seg, long index) {
        return (int) constants$42.const$4.get(seg.asSlice(index * sizeof()));
    }

    public static void frame_count$set(MemorySegment seg, long index, int x) {
        constants$42.const$4.set(seg.asSlice(index * sizeof()), x);
    }

    public static MemorySegment pad$slice(MemorySegment seg) {
        return seg.asSlice(20, 16);
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


