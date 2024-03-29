// Generated by jextract

package com.github.gotson.nightmonkeys.heif.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;

import static java.lang.foreign.ValueLayout.*;

/**
 * {@snippet :
 * struct heif_content_light_level {
 *     uint16_t max_content_light_level;
 *     uint16_t max_pic_average_light_level;
 * };
 *}
 */
public class heif_content_light_level {

    public static MemoryLayout $LAYOUT() {
        return constants$41.const$1;
    }

    public static VarHandle max_content_light_level$VH() {
        return constants$41.const$2;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint16_t max_content_light_level;
     *}
     */
    public static short max_content_light_level$get(MemorySegment seg) {
        return (short) constants$41.const$2.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint16_t max_content_light_level;
     *}
     */
    public static void max_content_light_level$set(MemorySegment seg, short x) {
        constants$41.const$2.set(seg, x);
    }

    public static short max_content_light_level$get(MemorySegment seg, long index) {
        return (short) constants$41.const$2.get(seg.asSlice(index * sizeof()));
    }

    public static void max_content_light_level$set(MemorySegment seg, long index, short x) {
        constants$41.const$2.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle max_pic_average_light_level$VH() {
        return constants$41.const$3;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint16_t max_pic_average_light_level;
     *}
     */
    public static short max_pic_average_light_level$get(MemorySegment seg) {
        return (short) constants$41.const$3.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint16_t max_pic_average_light_level;
     *}
     */
    public static void max_pic_average_light_level$set(MemorySegment seg, short x) {
        constants$41.const$3.set(seg, x);
    }

    public static short max_pic_average_light_level$get(MemorySegment seg, long index) {
        return (short) constants$41.const$3.get(seg.asSlice(index * sizeof()));
    }

    public static void max_pic_average_light_level$set(MemorySegment seg, long index, short x) {
        constants$41.const$3.set(seg.asSlice(index * sizeof()), x);
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


