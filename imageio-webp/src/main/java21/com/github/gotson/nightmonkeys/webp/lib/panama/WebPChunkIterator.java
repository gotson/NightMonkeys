// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.VarHandle;

/**
 * {@snippet :
 * struct WebPChunkIterator {
 *     int chunk_num;
 *     int num_chunks;
 *     WebPData chunk;
 *     uint32_t pad[6];
 *     void* private_;
 * };
 *}
 */
public class WebPChunkIterator {

    public static MemoryLayout $LAYOUT() {
        return constants$39.const$2;
    }

    public static VarHandle chunk_num$VH() {
        return constants$39.const$3;
    }

    /**
     * Getter for field:
     * {@snippet :
     * int chunk_num;
     *}
     */
    public static int chunk_num$get(MemorySegment seg) {
        return (int) constants$39.const$3.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * int chunk_num;
     *}
     */
    public static void chunk_num$set(MemorySegment seg, int x) {
        constants$39.const$3.set(seg, x);
    }

    public static int chunk_num$get(MemorySegment seg, long index) {
        return (int) constants$39.const$3.get(seg.asSlice(index * sizeof()));
    }

    public static void chunk_num$set(MemorySegment seg, long index, int x) {
        constants$39.const$3.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle num_chunks$VH() {
        return constants$39.const$4;
    }

    /**
     * Getter for field:
     * {@snippet :
     * int num_chunks;
     *}
     */
    public static int num_chunks$get(MemorySegment seg) {
        return (int) constants$39.const$4.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * int num_chunks;
     *}
     */
    public static void num_chunks$set(MemorySegment seg, int x) {
        constants$39.const$4.set(seg, x);
    }

    public static int num_chunks$get(MemorySegment seg, long index) {
        return (int) constants$39.const$4.get(seg.asSlice(index * sizeof()));
    }

    public static void num_chunks$set(MemorySegment seg, long index, int x) {
        constants$39.const$4.set(seg.asSlice(index * sizeof()), x);
    }

    public static MemorySegment chunk$slice(MemorySegment seg) {
        return seg.asSlice(8, 16);
    }

    public static MemorySegment pad$slice(MemorySegment seg) {
        return seg.asSlice(24, 24);
    }

    public static VarHandle private_$VH() {
        return constants$39.const$5;
    }

    /**
     * Getter for field:
     * {@snippet :
     * void* private_;
     *}
     */
    public static MemorySegment private_$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment) constants$39.const$5.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * void* private_;
     *}
     */
    public static void private_$set(MemorySegment seg, MemorySegment x) {
        constants$39.const$5.set(seg, x);
    }

    public static MemorySegment private_$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment) constants$39.const$5.get(seg.asSlice(index * sizeof()));
    }

    public static void private_$set(MemorySegment seg, long index, MemorySegment x) {
        constants$39.const$5.set(seg.asSlice(index * sizeof()), x);
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


