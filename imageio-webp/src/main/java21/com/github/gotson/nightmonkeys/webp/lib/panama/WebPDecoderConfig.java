// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

/**
 * {@snippet :
 * struct WebPDecoderConfig {
 *     WebPBitstreamFeatures input;
 *     WebPDecBuffer output;
 *     WebPDecoderOptions options;
 * };
 *}
 */
public class WebPDecoderConfig {

    public static MemoryLayout $LAYOUT() {
        return constants$21.const$2;
    }

    public static MemorySegment input$slice(MemorySegment seg) {
        return seg.asSlice(0, 40);
    }

    public static MemorySegment output$slice(MemorySegment seg) {
        return seg.asSlice(40, 120);
    }

    public static MemorySegment options$slice(MemorySegment seg) {
        return seg.asSlice(160, 76);
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


