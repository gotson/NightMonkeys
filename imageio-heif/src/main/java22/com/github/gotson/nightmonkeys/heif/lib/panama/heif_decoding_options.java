// Generated by jextract

package com.github.gotson.nightmonkeys.heif.lib.panama;

import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * struct heif_decoding_options {
 *     uint8_t version;
 *     uint8_t ignore_transformations;
 *     void (*start_progress)(enum heif_progress_step, int, void *);
 *     void (*on_progress)(enum heif_progress_step, int, void *);
 *     void (*end_progress)(enum heif_progress_step, void *);
 *     void *progress_user_data;
 *     uint8_t convert_hdr_to_8bit;
 *     uint8_t strict_decoding;
 *     const char *decoder_id;
 *     struct heif_color_conversion_options color_conversion_options;
 * }
 * }
 */
public class heif_decoding_options {

    heif_decoding_options() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        heif_h.C_CHAR.withName("version"),
        heif_h.C_CHAR.withName("ignore_transformations"),
        MemoryLayout.paddingLayout(6),
        heif_h.C_POINTER.withName("start_progress"),
        heif_h.C_POINTER.withName("on_progress"),
        heif_h.C_POINTER.withName("end_progress"),
        heif_h.C_POINTER.withName("progress_user_data"),
        heif_h.C_CHAR.withName("convert_hdr_to_8bit"),
        heif_h.C_CHAR.withName("strict_decoding"),
        MemoryLayout.paddingLayout(6),
        heif_h.C_POINTER.withName("decoder_id"),
        heif_color_conversion_options.layout().withName("color_conversion_options")
    ).withName("heif_decoding_options");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfByte version$LAYOUT = (OfByte)$LAYOUT.select(groupElement("version"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t version
     * }
     */
    public static final OfByte version$layout() {
        return version$LAYOUT;
    }

    private static final long version$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t version
     * }
     */
    public static final long version$offset() {
        return version$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t version
     * }
     */
    public static byte version(MemorySegment struct) {
        return struct.get(version$LAYOUT, version$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t version
     * }
     */
    public static void version(MemorySegment struct, byte fieldValue) {
        struct.set(version$LAYOUT, version$OFFSET, fieldValue);
    }

    private static final OfByte ignore_transformations$LAYOUT = (OfByte)$LAYOUT.select(groupElement("ignore_transformations"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t ignore_transformations
     * }
     */
    public static final OfByte ignore_transformations$layout() {
        return ignore_transformations$LAYOUT;
    }

    private static final long ignore_transformations$OFFSET = 1;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t ignore_transformations
     * }
     */
    public static final long ignore_transformations$offset() {
        return ignore_transformations$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t ignore_transformations
     * }
     */
    public static byte ignore_transformations(MemorySegment struct) {
        return struct.get(ignore_transformations$LAYOUT, ignore_transformations$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t ignore_transformations
     * }
     */
    public static void ignore_transformations(MemorySegment struct, byte fieldValue) {
        struct.set(ignore_transformations$LAYOUT, ignore_transformations$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * void (*start_progress)(enum heif_progress_step, int, void *)
     * }
     */
    public static class start_progress {

        start_progress() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            void apply(int _x0, int _x1, MemorySegment _x2);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.ofVoid(
            heif_h.C_INT,
            heif_h.C_INT,
            heif_h.C_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = heif_h.upcallHandle(start_progress.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(start_progress.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static void invoke(MemorySegment funcPtr,int _x0, int _x1, MemorySegment _x2) {
            try {
                 DOWN$MH.invokeExact(funcPtr, _x0, _x1, _x2);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        }
    }

    private static final AddressLayout start_progress$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("start_progress"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void (*start_progress)(enum heif_progress_step, int, void *)
     * }
     */
    public static final AddressLayout start_progress$layout() {
        return start_progress$LAYOUT;
    }

    private static final long start_progress$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void (*start_progress)(enum heif_progress_step, int, void *)
     * }
     */
    public static final long start_progress$offset() {
        return start_progress$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void (*start_progress)(enum heif_progress_step, int, void *)
     * }
     */
    public static MemorySegment start_progress(MemorySegment struct) {
        return struct.get(start_progress$LAYOUT, start_progress$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void (*start_progress)(enum heif_progress_step, int, void *)
     * }
     */
    public static void start_progress(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(start_progress$LAYOUT, start_progress$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * void (*on_progress)(enum heif_progress_step, int, void *)
     * }
     */
    public static class on_progress {

        on_progress() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            void apply(int _x0, int _x1, MemorySegment _x2);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.ofVoid(
            heif_h.C_INT,
            heif_h.C_INT,
            heif_h.C_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = heif_h.upcallHandle(on_progress.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(on_progress.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static void invoke(MemorySegment funcPtr,int _x0, int _x1, MemorySegment _x2) {
            try {
                 DOWN$MH.invokeExact(funcPtr, _x0, _x1, _x2);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        }
    }

    private static final AddressLayout on_progress$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("on_progress"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void (*on_progress)(enum heif_progress_step, int, void *)
     * }
     */
    public static final AddressLayout on_progress$layout() {
        return on_progress$LAYOUT;
    }

    private static final long on_progress$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void (*on_progress)(enum heif_progress_step, int, void *)
     * }
     */
    public static final long on_progress$offset() {
        return on_progress$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void (*on_progress)(enum heif_progress_step, int, void *)
     * }
     */
    public static MemorySegment on_progress(MemorySegment struct) {
        return struct.get(on_progress$LAYOUT, on_progress$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void (*on_progress)(enum heif_progress_step, int, void *)
     * }
     */
    public static void on_progress(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(on_progress$LAYOUT, on_progress$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * void (*end_progress)(enum heif_progress_step, void *)
     * }
     */
    public static class end_progress {

        end_progress() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            void apply(int _x0, MemorySegment _x1);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.ofVoid(
            heif_h.C_INT,
            heif_h.C_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = heif_h.upcallHandle(end_progress.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(end_progress.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static void invoke(MemorySegment funcPtr,int _x0, MemorySegment _x1) {
            try {
                 DOWN$MH.invokeExact(funcPtr, _x0, _x1);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        }
    }

    private static final AddressLayout end_progress$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("end_progress"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void (*end_progress)(enum heif_progress_step, void *)
     * }
     */
    public static final AddressLayout end_progress$layout() {
        return end_progress$LAYOUT;
    }

    private static final long end_progress$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void (*end_progress)(enum heif_progress_step, void *)
     * }
     */
    public static final long end_progress$offset() {
        return end_progress$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void (*end_progress)(enum heif_progress_step, void *)
     * }
     */
    public static MemorySegment end_progress(MemorySegment struct) {
        return struct.get(end_progress$LAYOUT, end_progress$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void (*end_progress)(enum heif_progress_step, void *)
     * }
     */
    public static void end_progress(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(end_progress$LAYOUT, end_progress$OFFSET, fieldValue);
    }

    private static final AddressLayout progress_user_data$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("progress_user_data"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void *progress_user_data
     * }
     */
    public static final AddressLayout progress_user_data$layout() {
        return progress_user_data$LAYOUT;
    }

    private static final long progress_user_data$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void *progress_user_data
     * }
     */
    public static final long progress_user_data$offset() {
        return progress_user_data$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void *progress_user_data
     * }
     */
    public static MemorySegment progress_user_data(MemorySegment struct) {
        return struct.get(progress_user_data$LAYOUT, progress_user_data$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void *progress_user_data
     * }
     */
    public static void progress_user_data(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(progress_user_data$LAYOUT, progress_user_data$OFFSET, fieldValue);
    }

    private static final OfByte convert_hdr_to_8bit$LAYOUT = (OfByte)$LAYOUT.select(groupElement("convert_hdr_to_8bit"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t convert_hdr_to_8bit
     * }
     */
    public static final OfByte convert_hdr_to_8bit$layout() {
        return convert_hdr_to_8bit$LAYOUT;
    }

    private static final long convert_hdr_to_8bit$OFFSET = 40;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t convert_hdr_to_8bit
     * }
     */
    public static final long convert_hdr_to_8bit$offset() {
        return convert_hdr_to_8bit$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t convert_hdr_to_8bit
     * }
     */
    public static byte convert_hdr_to_8bit(MemorySegment struct) {
        return struct.get(convert_hdr_to_8bit$LAYOUT, convert_hdr_to_8bit$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t convert_hdr_to_8bit
     * }
     */
    public static void convert_hdr_to_8bit(MemorySegment struct, byte fieldValue) {
        struct.set(convert_hdr_to_8bit$LAYOUT, convert_hdr_to_8bit$OFFSET, fieldValue);
    }

    private static final OfByte strict_decoding$LAYOUT = (OfByte)$LAYOUT.select(groupElement("strict_decoding"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t strict_decoding
     * }
     */
    public static final OfByte strict_decoding$layout() {
        return strict_decoding$LAYOUT;
    }

    private static final long strict_decoding$OFFSET = 41;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t strict_decoding
     * }
     */
    public static final long strict_decoding$offset() {
        return strict_decoding$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t strict_decoding
     * }
     */
    public static byte strict_decoding(MemorySegment struct) {
        return struct.get(strict_decoding$LAYOUT, strict_decoding$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t strict_decoding
     * }
     */
    public static void strict_decoding(MemorySegment struct, byte fieldValue) {
        struct.set(strict_decoding$LAYOUT, strict_decoding$OFFSET, fieldValue);
    }

    private static final AddressLayout decoder_id$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("decoder_id"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const char *decoder_id
     * }
     */
    public static final AddressLayout decoder_id$layout() {
        return decoder_id$LAYOUT;
    }

    private static final long decoder_id$OFFSET = 48;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const char *decoder_id
     * }
     */
    public static final long decoder_id$offset() {
        return decoder_id$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const char *decoder_id
     * }
     */
    public static MemorySegment decoder_id(MemorySegment struct) {
        return struct.get(decoder_id$LAYOUT, decoder_id$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const char *decoder_id
     * }
     */
    public static void decoder_id(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(decoder_id$LAYOUT, decoder_id$OFFSET, fieldValue);
    }

    private static final GroupLayout color_conversion_options$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("color_conversion_options"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * struct heif_color_conversion_options color_conversion_options
     * }
     */
    public static final GroupLayout color_conversion_options$layout() {
        return color_conversion_options$LAYOUT;
    }

    private static final long color_conversion_options$OFFSET = 56;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * struct heif_color_conversion_options color_conversion_options
     * }
     */
    public static final long color_conversion_options$offset() {
        return color_conversion_options$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * struct heif_color_conversion_options color_conversion_options
     * }
     */
    public static MemorySegment color_conversion_options(MemorySegment struct) {
        return struct.asSlice(color_conversion_options$OFFSET, color_conversion_options$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * struct heif_color_conversion_options color_conversion_options
     * }
     */
    public static void color_conversion_options(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, color_conversion_options$OFFSET, color_conversion_options$LAYOUT.byteSize());
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}

