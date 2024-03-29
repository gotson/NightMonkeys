// Generated by jextract

package com.github.gotson.nightmonkeys.heif.lib.panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;

import static java.lang.foreign.ValueLayout.*;

/**
 * {@snippet :
 * struct heif_decoding_options {
 *     uint8_t version;
 *     uint8_t ignore_transformations;
 *     void (*start_progress)(enum heif_progress_step,int,void*);
 *     void (*on_progress)(enum heif_progress_step,int,void*);
 *     void (*end_progress)(enum heif_progress_step,void*);
 *     void* progress_user_data;
 *     uint8_t convert_hdr_to_8bit;
 *     uint8_t strict_decoding;
 *     char* decoder_id;
 *     struct heif_color_conversion_options color_conversion_options;
 * };
 *}
 */
public class heif_decoding_options {

    public static MemoryLayout $LAYOUT() {
        return constants$34.const$3;
    }

    public static VarHandle version$VH() {
        return constants$34.const$4;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint8_t version;
     *}
     */
    public static byte version$get(MemorySegment seg) {
        return (byte) constants$34.const$4.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint8_t version;
     *}
     */
    public static void version$set(MemorySegment seg, byte x) {
        constants$34.const$4.set(seg, x);
    }

    public static byte version$get(MemorySegment seg, long index) {
        return (byte) constants$34.const$4.get(seg.asSlice(index * sizeof()));
    }

    public static void version$set(MemorySegment seg, long index, byte x) {
        constants$34.const$4.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle ignore_transformations$VH() {
        return constants$34.const$5;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint8_t ignore_transformations;
     *}
     */
    public static byte ignore_transformations$get(MemorySegment seg) {
        return (byte) constants$34.const$5.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint8_t ignore_transformations;
     *}
     */
    public static void ignore_transformations$set(MemorySegment seg, byte x) {
        constants$34.const$5.set(seg, x);
    }

    public static byte ignore_transformations$get(MemorySegment seg, long index) {
        return (byte) constants$34.const$5.get(seg.asSlice(index * sizeof()));
    }

    public static void ignore_transformations$set(MemorySegment seg, long index, byte x) {
        constants$34.const$5.set(seg.asSlice(index * sizeof()), x);
    }

    /**
     * {@snippet :
     * void (*start_progress)(enum heif_progress_step,int,void*);
     *}
     */
    public interface start_progress {

        void apply(int _x0, int _x1, java.lang.foreign.MemorySegment _x2);

        static MemorySegment allocate(start_progress fi, Arena scope) {
            return RuntimeHelper.upcallStub(constants$35.const$1, fi, constants$35.const$0, scope);
        }

        static start_progress ofAddress(MemorySegment addr, Arena arena) {
            MemorySegment symbol = addr.reinterpret(arena, null);
            return (int __x0, int __x1, java.lang.foreign.MemorySegment __x2) -> {
                try {
                    constants$35.const$2.invokeExact(symbol, __x0, __x1, __x2);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    public static VarHandle start_progress$VH() {
        return constants$35.const$3;
    }

    /**
     * Getter for field:
     * {@snippet :
     * void (*start_progress)(enum heif_progress_step,int,void*);
     *}
     */
    public static MemorySegment start_progress$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment) constants$35.const$3.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * void (*start_progress)(enum heif_progress_step,int,void*);
     *}
     */
    public static void start_progress$set(MemorySegment seg, MemorySegment x) {
        constants$35.const$3.set(seg, x);
    }

    public static MemorySegment start_progress$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment) constants$35.const$3.get(seg.asSlice(index * sizeof()));
    }

    public static void start_progress$set(MemorySegment seg, long index, MemorySegment x) {
        constants$35.const$3.set(seg.asSlice(index * sizeof()), x);
    }

    public static start_progress start_progress(MemorySegment segment, Arena scope) {
        return start_progress.ofAddress(start_progress$get(segment), scope);
    }

    /**
     * {@snippet :
     * void (*on_progress)(enum heif_progress_step,int,void*);
     *}
     */
    public interface on_progress {

        void apply(int _x0, int _x1, java.lang.foreign.MemorySegment _x2);

        static MemorySegment allocate(on_progress fi, Arena scope) {
            return RuntimeHelper.upcallStub(constants$35.const$4, fi, constants$35.const$0, scope);
        }

        static on_progress ofAddress(MemorySegment addr, Arena arena) {
            MemorySegment symbol = addr.reinterpret(arena, null);
            return (int __x0, int __x1, java.lang.foreign.MemorySegment __x2) -> {
                try {
                    constants$35.const$2.invokeExact(symbol, __x0, __x1, __x2);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    public static VarHandle on_progress$VH() {
        return constants$35.const$5;
    }

    /**
     * Getter for field:
     * {@snippet :
     * void (*on_progress)(enum heif_progress_step,int,void*);
     *}
     */
    public static MemorySegment on_progress$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment) constants$35.const$5.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * void (*on_progress)(enum heif_progress_step,int,void*);
     *}
     */
    public static void on_progress$set(MemorySegment seg, MemorySegment x) {
        constants$35.const$5.set(seg, x);
    }

    public static MemorySegment on_progress$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment) constants$35.const$5.get(seg.asSlice(index * sizeof()));
    }

    public static void on_progress$set(MemorySegment seg, long index, MemorySegment x) {
        constants$35.const$5.set(seg.asSlice(index * sizeof()), x);
    }

    public static on_progress on_progress(MemorySegment segment, Arena scope) {
        return on_progress.ofAddress(on_progress$get(segment), scope);
    }

    /**
     * {@snippet :
     * void (*end_progress)(enum heif_progress_step,void*);
     *}
     */
    public interface end_progress {

        void apply(int _x0, java.lang.foreign.MemorySegment _x1);

        static MemorySegment allocate(end_progress fi, Arena scope) {
            return RuntimeHelper.upcallStub(constants$36.const$0, fi, constants$10.const$0, scope);
        }

        static end_progress ofAddress(MemorySegment addr, Arena arena) {
            MemorySegment symbol = addr.reinterpret(arena, null);
            return (int __x0, java.lang.foreign.MemorySegment __x1) -> {
                try {
                    constants$36.const$1.invokeExact(symbol, __x0, __x1);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    public static VarHandle end_progress$VH() {
        return constants$36.const$2;
    }

    /**
     * Getter for field:
     * {@snippet :
     * void (*end_progress)(enum heif_progress_step,void*);
     *}
     */
    public static MemorySegment end_progress$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment) constants$36.const$2.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * void (*end_progress)(enum heif_progress_step,void*);
     *}
     */
    public static void end_progress$set(MemorySegment seg, MemorySegment x) {
        constants$36.const$2.set(seg, x);
    }

    public static MemorySegment end_progress$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment) constants$36.const$2.get(seg.asSlice(index * sizeof()));
    }

    public static void end_progress$set(MemorySegment seg, long index, MemorySegment x) {
        constants$36.const$2.set(seg.asSlice(index * sizeof()), x);
    }

    public static end_progress end_progress(MemorySegment segment, Arena scope) {
        return end_progress.ofAddress(end_progress$get(segment), scope);
    }

    public static VarHandle progress_user_data$VH() {
        return constants$36.const$3;
    }

    /**
     * Getter for field:
     * {@snippet :
     * void* progress_user_data;
     *}
     */
    public static MemorySegment progress_user_data$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment) constants$36.const$3.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * void* progress_user_data;
     *}
     */
    public static void progress_user_data$set(MemorySegment seg, MemorySegment x) {
        constants$36.const$3.set(seg, x);
    }

    public static MemorySegment progress_user_data$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment) constants$36.const$3.get(seg.asSlice(index * sizeof()));
    }

    public static void progress_user_data$set(MemorySegment seg, long index, MemorySegment x) {
        constants$36.const$3.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle convert_hdr_to_8bit$VH() {
        return constants$36.const$4;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint8_t convert_hdr_to_8bit;
     *}
     */
    public static byte convert_hdr_to_8bit$get(MemorySegment seg) {
        return (byte) constants$36.const$4.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint8_t convert_hdr_to_8bit;
     *}
     */
    public static void convert_hdr_to_8bit$set(MemorySegment seg, byte x) {
        constants$36.const$4.set(seg, x);
    }

    public static byte convert_hdr_to_8bit$get(MemorySegment seg, long index) {
        return (byte) constants$36.const$4.get(seg.asSlice(index * sizeof()));
    }

    public static void convert_hdr_to_8bit$set(MemorySegment seg, long index, byte x) {
        constants$36.const$4.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle strict_decoding$VH() {
        return constants$36.const$5;
    }

    /**
     * Getter for field:
     * {@snippet :
     * uint8_t strict_decoding;
     *}
     */
    public static byte strict_decoding$get(MemorySegment seg) {
        return (byte) constants$36.const$5.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * uint8_t strict_decoding;
     *}
     */
    public static void strict_decoding$set(MemorySegment seg, byte x) {
        constants$36.const$5.set(seg, x);
    }

    public static byte strict_decoding$get(MemorySegment seg, long index) {
        return (byte) constants$36.const$5.get(seg.asSlice(index * sizeof()));
    }

    public static void strict_decoding$set(MemorySegment seg, long index, byte x) {
        constants$36.const$5.set(seg.asSlice(index * sizeof()), x);
    }

    public static VarHandle decoder_id$VH() {
        return constants$37.const$0;
    }

    /**
     * Getter for field:
     * {@snippet :
     * char* decoder_id;
     *}
     */
    public static MemorySegment decoder_id$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment) constants$37.const$0.get(seg);
    }

    /**
     * Setter for field:
     * {@snippet :
     * char* decoder_id;
     *}
     */
    public static void decoder_id$set(MemorySegment seg, MemorySegment x) {
        constants$37.const$0.set(seg, x);
    }

    public static MemorySegment decoder_id$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment) constants$37.const$0.get(seg.asSlice(index * sizeof()));
    }

    public static void decoder_id$set(MemorySegment seg, long index, MemorySegment x) {
        constants$37.const$0.set(seg.asSlice(index * sizeof()), x);
    }

    public static MemorySegment color_conversion_options$slice(MemorySegment seg) {
        return seg.asSlice(56, 16);
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


