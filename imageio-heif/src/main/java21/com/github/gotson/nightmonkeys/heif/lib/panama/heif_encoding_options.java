// Generated by jextract

package com.github.gotson.nightmonkeys.heif.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;

import static java.lang.foreign.ValueLayout.*;

/**
 * {@snippet :
 * struct heif_encoding_options {
 *     uint8_t version;
 *     uint8_t save_alpha_channel;
 *     uint8_t macOS_compatibility_workaround;
 *     uint8_t save_two_colr_boxes_when_ICC_and_nclx_available;
 *     struct heif_color_profile_nclx* output_nclx_profile;
 *     uint8_t macOS_compatibility_workaround_no_nclx_profile;
 *     enum heif_orientation image_orientation;
 *     struct heif_color_conversion_options color_conversion_options;
 * };
 *}
 */
public class heif_encoding_options {

    public static MemoryLayout $LAYOUT() {
        return constants$54.const$0;
    }

    public static VarHandle version$VH() {
        return constants$54.const$1;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint8_t version;
     *}
     */
    public static byte version$get(MemorySegment seg) {
        return (byte) constants$54.const$1.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint8_t version;
     *}
     */
    public static void version$set(MemorySegment seg, byte x) {
        constants$54.const$1.set(seg, x);
    }

    public static byte version$get(MemorySegment seg, long index) {
        return (byte) constants$54.const$1.get(seg.asSlice(index * sizeof()));
    }

    public static void version$set(MemorySegment seg, long index, byte x) {
        constants$54.const$1.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle save_alpha_channel$VH() {
        return constants$54.const$2;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint8_t save_alpha_channel;
     *}
     */
    public static byte save_alpha_channel$get(MemorySegment seg) {
        return (byte) constants$54.const$2.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint8_t save_alpha_channel;
     *}
     */
    public static void save_alpha_channel$set(MemorySegment seg, byte x) {
        constants$54.const$2.set(seg, x);
    }

    public static byte save_alpha_channel$get(MemorySegment seg, long index) {
        return (byte) constants$54.const$2.get(seg.asSlice(index * sizeof()));
    }

    public static void save_alpha_channel$set(MemorySegment seg, long index, byte x) {
        constants$54.const$2.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle macOS_compatibility_workaround$VH() {
        return constants$54.const$3;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint8_t macOS_compatibility_workaround;
     *}
     */
    public static byte macOS_compatibility_workaround$get(MemorySegment seg) {
        return (byte) constants$54.const$3.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint8_t macOS_compatibility_workaround;
     *}
     */
    public static void macOS_compatibility_workaround$set(MemorySegment seg, byte x) {
        constants$54.const$3.set(seg, x);
    }

    public static byte macOS_compatibility_workaround$get(MemorySegment seg, long index) {
        return (byte) constants$54.const$3.get(seg.asSlice(index * sizeof()));
    }

    public static void macOS_compatibility_workaround$set(MemorySegment seg, long index, byte x) {
        constants$54.const$3.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle save_two_colr_boxes_when_ICC_and_nclx_available$VH() {
        return constants$54.const$4;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint8_t save_two_colr_boxes_when_ICC_and_nclx_available;
     *}
     */
    public static byte save_two_colr_boxes_when_ICC_and_nclx_available$get(MemorySegment seg) {
        return (byte) constants$54.const$4.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint8_t save_two_colr_boxes_when_ICC_and_nclx_available;
     *}
     */
    public static void save_two_colr_boxes_when_ICC_and_nclx_available$set(MemorySegment seg, byte x) {
        constants$54.const$4.set(seg, x);
    }

    public static byte save_two_colr_boxes_when_ICC_and_nclx_available$get(MemorySegment seg, long index) {
        return (byte) constants$54.const$4.get(seg.asSlice(index * sizeof()));
    }

    public static void save_two_colr_boxes_when_ICC_and_nclx_available$set(MemorySegment seg, long index, byte x) {
        constants$54.const$4.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle output_nclx_profile$VH() {
        return constants$54.const$5;
    }

    /**
     * Getter for field:
     * {@snippet :
     * struct heif_color_profile_nclx* output_nclx_profile;
     *}
     */
    public static MemorySegment output_nclx_profile$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment) constants$54.const$5.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * struct heif_color_profile_nclx* output_nclx_profile;
     *}
     */
    public static void output_nclx_profile$set(MemorySegment seg, MemorySegment x) {
        constants$54.const$5.set(seg, x);
    }

    public static MemorySegment output_nclx_profile$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment) constants$54.const$5.get(seg.asSlice(index * sizeof()));
    }

    public static void output_nclx_profile$set(MemorySegment seg, long index, MemorySegment x) {
        constants$54.const$5.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle macOS_compatibility_workaround_no_nclx_profile$VH() {
        return constants$55.const$0;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint8_t macOS_compatibility_workaround_no_nclx_profile;
     *}
     */
    public static byte macOS_compatibility_workaround_no_nclx_profile$get(MemorySegment seg) {
        return (byte) constants$55.const$0.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint8_t macOS_compatibility_workaround_no_nclx_profile;
     *}
     */
    public static void macOS_compatibility_workaround_no_nclx_profile$set(MemorySegment seg, byte x) {
        constants$55.const$0.set(seg, x);
    }

    public static byte macOS_compatibility_workaround_no_nclx_profile$get(MemorySegment seg, long index) {
        return (byte) constants$55.const$0.get(seg.asSlice(index * sizeof()));
    }

    public static void macOS_compatibility_workaround_no_nclx_profile$set(MemorySegment seg, long index, byte x) {
        constants$55.const$0.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle image_orientation$VH() {
        return constants$55.const$1;
    }

    /**
     * Getter for field:
     * {@snippet :
     * enum heif_orientation image_orientation;
     *}
     */
    public static int image_orientation$get(MemorySegment seg) {
        return (int) constants$55.const$1.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * enum heif_orientation image_orientation;
     *}
     */
    public static void image_orientation$set(MemorySegment seg, int x) {
        constants$55.const$1.set(seg, x);
    }

    public static int image_orientation$get(MemorySegment seg, long index) {
        return (int) constants$55.const$1.get(seg.asSlice(index * sizeof()));
    }

    public static void image_orientation$set(MemorySegment seg, long index, int x) {
        constants$55.const$1.set(seg.asSlice(index * sizeof()), x);
    }

    public static MemorySegment color_conversion_options$slice(MemorySegment seg) {
        return seg.asSlice(24, 16);
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


