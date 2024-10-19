// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

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
 * struct WebPPicture {
 *     int use_argb;
 *     WebPEncCSP colorspace;
 *     int width;
 *     int height;
 *     uint8_t *y;
 *     uint8_t *u;
 *     uint8_t *v;
 *     int y_stride;
 *     int uv_stride;
 *     uint8_t *a;
 *     int a_stride;
 *     uint32_t pad1[2];
 *     uint32_t *argb;
 *     int argb_stride;
 *     uint32_t pad2[3];
 *     WebPWriterFunction writer;
 *     void *custom_ptr;
 *     int extra_info_type;
 *     uint8_t *extra_info;
 *     WebPAuxStats *stats;
 *     WebPEncodingError error_code;
 *     WebPProgressHook progress_hook;
 *     void *user_data;
 *     uint32_t pad3[3];
 *     uint8_t *pad4;
 *     uint8_t *pad5;
 *     uint32_t pad6[8];
 *     void *memory_;
 *     void *memory_argb_;
 *     void *pad7[2];
 * }
 * }
 */
public class WebPPicture {

    WebPPicture() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        encode_h.C_INT.withName("use_argb"),
        encode_h.C_INT.withName("colorspace"),
        encode_h.C_INT.withName("width"),
        encode_h.C_INT.withName("height"),
        encode_h.C_POINTER.withName("y"),
        encode_h.C_POINTER.withName("u"),
        encode_h.C_POINTER.withName("v"),
        encode_h.C_INT.withName("y_stride"),
        encode_h.C_INT.withName("uv_stride"),
        encode_h.C_POINTER.withName("a"),
        encode_h.C_INT.withName("a_stride"),
        MemoryLayout.sequenceLayout(2, encode_h.C_INT).withName("pad1"),
        MemoryLayout.paddingLayout(4),
        encode_h.C_POINTER.withName("argb"),
        encode_h.C_INT.withName("argb_stride"),
        MemoryLayout.sequenceLayout(3, encode_h.C_INT).withName("pad2"),
        encode_h.C_POINTER.withName("writer"),
        encode_h.C_POINTER.withName("custom_ptr"),
        encode_h.C_INT.withName("extra_info_type"),
        MemoryLayout.paddingLayout(4),
        encode_h.C_POINTER.withName("extra_info"),
        encode_h.C_POINTER.withName("stats"),
        encode_h.C_INT.withName("error_code"),
        MemoryLayout.paddingLayout(4),
        encode_h.C_POINTER.withName("progress_hook"),
        encode_h.C_POINTER.withName("user_data"),
        MemoryLayout.sequenceLayout(3, encode_h.C_INT).withName("pad3"),
        MemoryLayout.paddingLayout(4),
        encode_h.C_POINTER.withName("pad4"),
        encode_h.C_POINTER.withName("pad5"),
        MemoryLayout.sequenceLayout(8, encode_h.C_INT).withName("pad6"),
        encode_h.C_POINTER.withName("memory_"),
        encode_h.C_POINTER.withName("memory_argb_"),
        MemoryLayout.sequenceLayout(2, encode_h.C_POINTER).withName("pad7")
    ).withName("WebPPicture");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt use_argb$LAYOUT = (OfInt)$LAYOUT.select(groupElement("use_argb"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int use_argb
     * }
     */
    public static final OfInt use_argb$layout() {
        return use_argb$LAYOUT;
    }

    private static final long use_argb$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int use_argb
     * }
     */
    public static final long use_argb$offset() {
        return use_argb$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int use_argb
     * }
     */
    public static int use_argb(MemorySegment struct) {
        return struct.get(use_argb$LAYOUT, use_argb$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int use_argb
     * }
     */
    public static void use_argb(MemorySegment struct, int fieldValue) {
        struct.set(use_argb$LAYOUT, use_argb$OFFSET, fieldValue);
    }

    private static final OfInt colorspace$LAYOUT = (OfInt)$LAYOUT.select(groupElement("colorspace"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WebPEncCSP colorspace
     * }
     */
    public static final OfInt colorspace$layout() {
        return colorspace$LAYOUT;
    }

    private static final long colorspace$OFFSET = 4;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WebPEncCSP colorspace
     * }
     */
    public static final long colorspace$offset() {
        return colorspace$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WebPEncCSP colorspace
     * }
     */
    public static int colorspace(MemorySegment struct) {
        return struct.get(colorspace$LAYOUT, colorspace$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WebPEncCSP colorspace
     * }
     */
    public static void colorspace(MemorySegment struct, int fieldValue) {
        struct.set(colorspace$LAYOUT, colorspace$OFFSET, fieldValue);
    }

    private static final OfInt width$LAYOUT = (OfInt)$LAYOUT.select(groupElement("width"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int width
     * }
     */
    public static final OfInt width$layout() {
        return width$LAYOUT;
    }

    private static final long width$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int width
     * }
     */
    public static final long width$offset() {
        return width$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int width
     * }
     */
    public static int width(MemorySegment struct) {
        return struct.get(width$LAYOUT, width$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int width
     * }
     */
    public static void width(MemorySegment struct, int fieldValue) {
        struct.set(width$LAYOUT, width$OFFSET, fieldValue);
    }

    private static final OfInt height$LAYOUT = (OfInt)$LAYOUT.select(groupElement("height"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int height
     * }
     */
    public static final OfInt height$layout() {
        return height$LAYOUT;
    }

    private static final long height$OFFSET = 12;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int height
     * }
     */
    public static final long height$offset() {
        return height$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int height
     * }
     */
    public static int height(MemorySegment struct) {
        return struct.get(height$LAYOUT, height$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int height
     * }
     */
    public static void height(MemorySegment struct, int fieldValue) {
        struct.set(height$LAYOUT, height$OFFSET, fieldValue);
    }

    private static final AddressLayout y$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("y"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t *y
     * }
     */
    public static final AddressLayout y$layout() {
        return y$LAYOUT;
    }

    private static final long y$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t *y
     * }
     */
    public static final long y$offset() {
        return y$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t *y
     * }
     */
    public static MemorySegment y(MemorySegment struct) {
        return struct.get(y$LAYOUT, y$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t *y
     * }
     */
    public static void y(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(y$LAYOUT, y$OFFSET, fieldValue);
    }

    private static final AddressLayout u$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("u"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t *u
     * }
     */
    public static final AddressLayout u$layout() {
        return u$LAYOUT;
    }

    private static final long u$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t *u
     * }
     */
    public static final long u$offset() {
        return u$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t *u
     * }
     */
    public static MemorySegment u(MemorySegment struct) {
        return struct.get(u$LAYOUT, u$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t *u
     * }
     */
    public static void u(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(u$LAYOUT, u$OFFSET, fieldValue);
    }

    private static final AddressLayout v$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("v"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t *v
     * }
     */
    public static final AddressLayout v$layout() {
        return v$LAYOUT;
    }

    private static final long v$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t *v
     * }
     */
    public static final long v$offset() {
        return v$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t *v
     * }
     */
    public static MemorySegment v(MemorySegment struct) {
        return struct.get(v$LAYOUT, v$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t *v
     * }
     */
    public static void v(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(v$LAYOUT, v$OFFSET, fieldValue);
    }

    private static final OfInt y_stride$LAYOUT = (OfInt)$LAYOUT.select(groupElement("y_stride"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int y_stride
     * }
     */
    public static final OfInt y_stride$layout() {
        return y_stride$LAYOUT;
    }

    private static final long y_stride$OFFSET = 40;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int y_stride
     * }
     */
    public static final long y_stride$offset() {
        return y_stride$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int y_stride
     * }
     */
    public static int y_stride(MemorySegment struct) {
        return struct.get(y_stride$LAYOUT, y_stride$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int y_stride
     * }
     */
    public static void y_stride(MemorySegment struct, int fieldValue) {
        struct.set(y_stride$LAYOUT, y_stride$OFFSET, fieldValue);
    }

    private static final OfInt uv_stride$LAYOUT = (OfInt)$LAYOUT.select(groupElement("uv_stride"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int uv_stride
     * }
     */
    public static final OfInt uv_stride$layout() {
        return uv_stride$LAYOUT;
    }

    private static final long uv_stride$OFFSET = 44;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int uv_stride
     * }
     */
    public static final long uv_stride$offset() {
        return uv_stride$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int uv_stride
     * }
     */
    public static int uv_stride(MemorySegment struct) {
        return struct.get(uv_stride$LAYOUT, uv_stride$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int uv_stride
     * }
     */
    public static void uv_stride(MemorySegment struct, int fieldValue) {
        struct.set(uv_stride$LAYOUT, uv_stride$OFFSET, fieldValue);
    }

    private static final AddressLayout a$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("a"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t *a
     * }
     */
    public static final AddressLayout a$layout() {
        return a$LAYOUT;
    }

    private static final long a$OFFSET = 48;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t *a
     * }
     */
    public static final long a$offset() {
        return a$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t *a
     * }
     */
    public static MemorySegment a(MemorySegment struct) {
        return struct.get(a$LAYOUT, a$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t *a
     * }
     */
    public static void a(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(a$LAYOUT, a$OFFSET, fieldValue);
    }

    private static final OfInt a_stride$LAYOUT = (OfInt)$LAYOUT.select(groupElement("a_stride"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int a_stride
     * }
     */
    public static final OfInt a_stride$layout() {
        return a_stride$LAYOUT;
    }

    private static final long a_stride$OFFSET = 56;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int a_stride
     * }
     */
    public static final long a_stride$offset() {
        return a_stride$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int a_stride
     * }
     */
    public static int a_stride(MemorySegment struct) {
        return struct.get(a_stride$LAYOUT, a_stride$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int a_stride
     * }
     */
    public static void a_stride(MemorySegment struct, int fieldValue) {
        struct.set(a_stride$LAYOUT, a_stride$OFFSET, fieldValue);
    }

    private static final SequenceLayout pad1$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("pad1"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint32_t pad1[2]
     * }
     */
    public static final SequenceLayout pad1$layout() {
        return pad1$LAYOUT;
    }

    private static final long pad1$OFFSET = 60;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint32_t pad1[2]
     * }
     */
    public static final long pad1$offset() {
        return pad1$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint32_t pad1[2]
     * }
     */
    public static MemorySegment pad1(MemorySegment struct) {
        return struct.asSlice(pad1$OFFSET, pad1$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint32_t pad1[2]
     * }
     */
    public static void pad1(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, pad1$OFFSET, pad1$LAYOUT.byteSize());
    }

    private static long[] pad1$DIMS = { 2 };

    /**
     * Dimensions for array field:
     * {@snippet lang=c :
     * uint32_t pad1[2]
     * }
     */
    public static long[] pad1$dimensions() {
        return pad1$DIMS;
    }
    private static final VarHandle pad1$ELEM_HANDLE = pad1$LAYOUT.varHandle(sequenceElement());

    /**
     * Indexed getter for field:
     * {@snippet lang=c :
     * uint32_t pad1[2]
     * }
     */
    public static int pad1(MemorySegment struct, long index0) {
        return (int)pad1$ELEM_HANDLE.get(struct, 0L, index0);
    }

    /**
     * Indexed setter for field:
     * {@snippet lang=c :
     * uint32_t pad1[2]
     * }
     */
    public static void pad1(MemorySegment struct, long index0, int fieldValue) {
        pad1$ELEM_HANDLE.set(struct, 0L, index0, fieldValue);
    }

    private static final AddressLayout argb$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("argb"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint32_t *argb
     * }
     */
    public static final AddressLayout argb$layout() {
        return argb$LAYOUT;
    }

    private static final long argb$OFFSET = 72;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint32_t *argb
     * }
     */
    public static final long argb$offset() {
        return argb$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint32_t *argb
     * }
     */
    public static MemorySegment argb(MemorySegment struct) {
        return struct.get(argb$LAYOUT, argb$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint32_t *argb
     * }
     */
    public static void argb(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(argb$LAYOUT, argb$OFFSET, fieldValue);
    }

    private static final OfInt argb_stride$LAYOUT = (OfInt)$LAYOUT.select(groupElement("argb_stride"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int argb_stride
     * }
     */
    public static final OfInt argb_stride$layout() {
        return argb_stride$LAYOUT;
    }

    private static final long argb_stride$OFFSET = 80;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int argb_stride
     * }
     */
    public static final long argb_stride$offset() {
        return argb_stride$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int argb_stride
     * }
     */
    public static int argb_stride(MemorySegment struct) {
        return struct.get(argb_stride$LAYOUT, argb_stride$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int argb_stride
     * }
     */
    public static void argb_stride(MemorySegment struct, int fieldValue) {
        struct.set(argb_stride$LAYOUT, argb_stride$OFFSET, fieldValue);
    }

    private static final SequenceLayout pad2$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("pad2"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint32_t pad2[3]
     * }
     */
    public static final SequenceLayout pad2$layout() {
        return pad2$LAYOUT;
    }

    private static final long pad2$OFFSET = 84;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint32_t pad2[3]
     * }
     */
    public static final long pad2$offset() {
        return pad2$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint32_t pad2[3]
     * }
     */
    public static MemorySegment pad2(MemorySegment struct) {
        return struct.asSlice(pad2$OFFSET, pad2$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint32_t pad2[3]
     * }
     */
    public static void pad2(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, pad2$OFFSET, pad2$LAYOUT.byteSize());
    }

    private static long[] pad2$DIMS = { 3 };

    /**
     * Dimensions for array field:
     * {@snippet lang=c :
     * uint32_t pad2[3]
     * }
     */
    public static long[] pad2$dimensions() {
        return pad2$DIMS;
    }
    private static final VarHandle pad2$ELEM_HANDLE = pad2$LAYOUT.varHandle(sequenceElement());

    /**
     * Indexed getter for field:
     * {@snippet lang=c :
     * uint32_t pad2[3]
     * }
     */
    public static int pad2(MemorySegment struct, long index0) {
        return (int)pad2$ELEM_HANDLE.get(struct, 0L, index0);
    }

    /**
     * Indexed setter for field:
     * {@snippet lang=c :
     * uint32_t pad2[3]
     * }
     */
    public static void pad2(MemorySegment struct, long index0, int fieldValue) {
        pad2$ELEM_HANDLE.set(struct, 0L, index0, fieldValue);
    }

    private static final AddressLayout writer$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("writer"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WebPWriterFunction writer
     * }
     */
    public static final AddressLayout writer$layout() {
        return writer$LAYOUT;
    }

    private static final long writer$OFFSET = 96;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WebPWriterFunction writer
     * }
     */
    public static final long writer$offset() {
        return writer$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WebPWriterFunction writer
     * }
     */
    public static MemorySegment writer(MemorySegment struct) {
        return struct.get(writer$LAYOUT, writer$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WebPWriterFunction writer
     * }
     */
    public static void writer(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(writer$LAYOUT, writer$OFFSET, fieldValue);
    }

    private static final AddressLayout custom_ptr$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("custom_ptr"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void *custom_ptr
     * }
     */
    public static final AddressLayout custom_ptr$layout() {
        return custom_ptr$LAYOUT;
    }

    private static final long custom_ptr$OFFSET = 104;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void *custom_ptr
     * }
     */
    public static final long custom_ptr$offset() {
        return custom_ptr$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void *custom_ptr
     * }
     */
    public static MemorySegment custom_ptr(MemorySegment struct) {
        return struct.get(custom_ptr$LAYOUT, custom_ptr$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void *custom_ptr
     * }
     */
    public static void custom_ptr(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(custom_ptr$LAYOUT, custom_ptr$OFFSET, fieldValue);
    }

    private static final OfInt extra_info_type$LAYOUT = (OfInt)$LAYOUT.select(groupElement("extra_info_type"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int extra_info_type
     * }
     */
    public static final OfInt extra_info_type$layout() {
        return extra_info_type$LAYOUT;
    }

    private static final long extra_info_type$OFFSET = 112;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int extra_info_type
     * }
     */
    public static final long extra_info_type$offset() {
        return extra_info_type$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int extra_info_type
     * }
     */
    public static int extra_info_type(MemorySegment struct) {
        return struct.get(extra_info_type$LAYOUT, extra_info_type$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int extra_info_type
     * }
     */
    public static void extra_info_type(MemorySegment struct, int fieldValue) {
        struct.set(extra_info_type$LAYOUT, extra_info_type$OFFSET, fieldValue);
    }

    private static final AddressLayout extra_info$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("extra_info"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t *extra_info
     * }
     */
    public static final AddressLayout extra_info$layout() {
        return extra_info$LAYOUT;
    }

    private static final long extra_info$OFFSET = 120;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t *extra_info
     * }
     */
    public static final long extra_info$offset() {
        return extra_info$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t *extra_info
     * }
     */
    public static MemorySegment extra_info(MemorySegment struct) {
        return struct.get(extra_info$LAYOUT, extra_info$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t *extra_info
     * }
     */
    public static void extra_info(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(extra_info$LAYOUT, extra_info$OFFSET, fieldValue);
    }

    private static final AddressLayout stats$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("stats"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WebPAuxStats *stats
     * }
     */
    public static final AddressLayout stats$layout() {
        return stats$LAYOUT;
    }

    private static final long stats$OFFSET = 128;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WebPAuxStats *stats
     * }
     */
    public static final long stats$offset() {
        return stats$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WebPAuxStats *stats
     * }
     */
    public static MemorySegment stats(MemorySegment struct) {
        return struct.get(stats$LAYOUT, stats$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WebPAuxStats *stats
     * }
     */
    public static void stats(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(stats$LAYOUT, stats$OFFSET, fieldValue);
    }

    private static final OfInt error_code$LAYOUT = (OfInt)$LAYOUT.select(groupElement("error_code"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WebPEncodingError error_code
     * }
     */
    public static final OfInt error_code$layout() {
        return error_code$LAYOUT;
    }

    private static final long error_code$OFFSET = 136;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WebPEncodingError error_code
     * }
     */
    public static final long error_code$offset() {
        return error_code$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WebPEncodingError error_code
     * }
     */
    public static int error_code(MemorySegment struct) {
        return struct.get(error_code$LAYOUT, error_code$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WebPEncodingError error_code
     * }
     */
    public static void error_code(MemorySegment struct, int fieldValue) {
        struct.set(error_code$LAYOUT, error_code$OFFSET, fieldValue);
    }

    private static final AddressLayout progress_hook$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("progress_hook"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WebPProgressHook progress_hook
     * }
     */
    public static final AddressLayout progress_hook$layout() {
        return progress_hook$LAYOUT;
    }

    private static final long progress_hook$OFFSET = 144;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WebPProgressHook progress_hook
     * }
     */
    public static final long progress_hook$offset() {
        return progress_hook$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WebPProgressHook progress_hook
     * }
     */
    public static MemorySegment progress_hook(MemorySegment struct) {
        return struct.get(progress_hook$LAYOUT, progress_hook$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WebPProgressHook progress_hook
     * }
     */
    public static void progress_hook(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(progress_hook$LAYOUT, progress_hook$OFFSET, fieldValue);
    }

    private static final AddressLayout user_data$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("user_data"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void *user_data
     * }
     */
    public static final AddressLayout user_data$layout() {
        return user_data$LAYOUT;
    }

    private static final long user_data$OFFSET = 152;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void *user_data
     * }
     */
    public static final long user_data$offset() {
        return user_data$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void *user_data
     * }
     */
    public static MemorySegment user_data(MemorySegment struct) {
        return struct.get(user_data$LAYOUT, user_data$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void *user_data
     * }
     */
    public static void user_data(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(user_data$LAYOUT, user_data$OFFSET, fieldValue);
    }

    private static final SequenceLayout pad3$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("pad3"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint32_t pad3[3]
     * }
     */
    public static final SequenceLayout pad3$layout() {
        return pad3$LAYOUT;
    }

    private static final long pad3$OFFSET = 160;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint32_t pad3[3]
     * }
     */
    public static final long pad3$offset() {
        return pad3$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint32_t pad3[3]
     * }
     */
    public static MemorySegment pad3(MemorySegment struct) {
        return struct.asSlice(pad3$OFFSET, pad3$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint32_t pad3[3]
     * }
     */
    public static void pad3(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, pad3$OFFSET, pad3$LAYOUT.byteSize());
    }

    private static long[] pad3$DIMS = { 3 };

    /**
     * Dimensions for array field:
     * {@snippet lang=c :
     * uint32_t pad3[3]
     * }
     */
    public static long[] pad3$dimensions() {
        return pad3$DIMS;
    }
    private static final VarHandle pad3$ELEM_HANDLE = pad3$LAYOUT.varHandle(sequenceElement());

    /**
     * Indexed getter for field:
     * {@snippet lang=c :
     * uint32_t pad3[3]
     * }
     */
    public static int pad3(MemorySegment struct, long index0) {
        return (int)pad3$ELEM_HANDLE.get(struct, 0L, index0);
    }

    /**
     * Indexed setter for field:
     * {@snippet lang=c :
     * uint32_t pad3[3]
     * }
     */
    public static void pad3(MemorySegment struct, long index0, int fieldValue) {
        pad3$ELEM_HANDLE.set(struct, 0L, index0, fieldValue);
    }

    private static final AddressLayout pad4$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("pad4"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t *pad4
     * }
     */
    public static final AddressLayout pad4$layout() {
        return pad4$LAYOUT;
    }

    private static final long pad4$OFFSET = 176;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t *pad4
     * }
     */
    public static final long pad4$offset() {
        return pad4$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t *pad4
     * }
     */
    public static MemorySegment pad4(MemorySegment struct) {
        return struct.get(pad4$LAYOUT, pad4$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t *pad4
     * }
     */
    public static void pad4(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(pad4$LAYOUT, pad4$OFFSET, fieldValue);
    }

    private static final AddressLayout pad5$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("pad5"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint8_t *pad5
     * }
     */
    public static final AddressLayout pad5$layout() {
        return pad5$LAYOUT;
    }

    private static final long pad5$OFFSET = 184;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint8_t *pad5
     * }
     */
    public static final long pad5$offset() {
        return pad5$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint8_t *pad5
     * }
     */
    public static MemorySegment pad5(MemorySegment struct) {
        return struct.get(pad5$LAYOUT, pad5$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint8_t *pad5
     * }
     */
    public static void pad5(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(pad5$LAYOUT, pad5$OFFSET, fieldValue);
    }

    private static final SequenceLayout pad6$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("pad6"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint32_t pad6[8]
     * }
     */
    public static final SequenceLayout pad6$layout() {
        return pad6$LAYOUT;
    }

    private static final long pad6$OFFSET = 192;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint32_t pad6[8]
     * }
     */
    public static final long pad6$offset() {
        return pad6$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint32_t pad6[8]
     * }
     */
    public static MemorySegment pad6(MemorySegment struct) {
        return struct.asSlice(pad6$OFFSET, pad6$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint32_t pad6[8]
     * }
     */
    public static void pad6(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, pad6$OFFSET, pad6$LAYOUT.byteSize());
    }

    private static long[] pad6$DIMS = { 8 };

    /**
     * Dimensions for array field:
     * {@snippet lang=c :
     * uint32_t pad6[8]
     * }
     */
    public static long[] pad6$dimensions() {
        return pad6$DIMS;
    }
    private static final VarHandle pad6$ELEM_HANDLE = pad6$LAYOUT.varHandle(sequenceElement());

    /**
     * Indexed getter for field:
     * {@snippet lang=c :
     * uint32_t pad6[8]
     * }
     */
    public static int pad6(MemorySegment struct, long index0) {
        return (int)pad6$ELEM_HANDLE.get(struct, 0L, index0);
    }

    /**
     * Indexed setter for field:
     * {@snippet lang=c :
     * uint32_t pad6[8]
     * }
     */
    public static void pad6(MemorySegment struct, long index0, int fieldValue) {
        pad6$ELEM_HANDLE.set(struct, 0L, index0, fieldValue);
    }

    private static final AddressLayout memory_$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("memory_"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void *memory_
     * }
     */
    public static final AddressLayout memory_$layout() {
        return memory_$LAYOUT;
    }

    private static final long memory_$OFFSET = 224;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void *memory_
     * }
     */
    public static final long memory_$offset() {
        return memory_$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void *memory_
     * }
     */
    public static MemorySegment memory_(MemorySegment struct) {
        return struct.get(memory_$LAYOUT, memory_$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void *memory_
     * }
     */
    public static void memory_(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(memory_$LAYOUT, memory_$OFFSET, fieldValue);
    }

    private static final AddressLayout memory_argb_$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("memory_argb_"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void *memory_argb_
     * }
     */
    public static final AddressLayout memory_argb_$layout() {
        return memory_argb_$LAYOUT;
    }

    private static final long memory_argb_$OFFSET = 232;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void *memory_argb_
     * }
     */
    public static final long memory_argb_$offset() {
        return memory_argb_$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void *memory_argb_
     * }
     */
    public static MemorySegment memory_argb_(MemorySegment struct) {
        return struct.get(memory_argb_$LAYOUT, memory_argb_$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void *memory_argb_
     * }
     */
    public static void memory_argb_(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(memory_argb_$LAYOUT, memory_argb_$OFFSET, fieldValue);
    }

    private static final SequenceLayout pad7$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("pad7"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void *pad7[2]
     * }
     */
    public static final SequenceLayout pad7$layout() {
        return pad7$LAYOUT;
    }

    private static final long pad7$OFFSET = 240;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void *pad7[2]
     * }
     */
    public static final long pad7$offset() {
        return pad7$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void *pad7[2]
     * }
     */
    public static MemorySegment pad7(MemorySegment struct) {
        return struct.asSlice(pad7$OFFSET, pad7$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void *pad7[2]
     * }
     */
    public static void pad7(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, pad7$OFFSET, pad7$LAYOUT.byteSize());
    }

    private static long[] pad7$DIMS = { 2 };

    /**
     * Dimensions for array field:
     * {@snippet lang=c :
     * void *pad7[2]
     * }
     */
    public static long[] pad7$dimensions() {
        return pad7$DIMS;
    }
    private static final VarHandle pad7$ELEM_HANDLE = pad7$LAYOUT.varHandle(sequenceElement());

    /**
     * Indexed getter for field:
     * {@snippet lang=c :
     * void *pad7[2]
     * }
     */
    public static MemorySegment pad7(MemorySegment struct, long index0) {
        return (MemorySegment)pad7$ELEM_HANDLE.get(struct, 0L, index0);
    }

    /**
     * Indexed setter for field:
     * {@snippet lang=c :
     * void *pad7[2]
     * }
     */
    public static void pad7(MemorySegment struct, long index0, MemorySegment fieldValue) {
        pad7$ELEM_HANDLE.set(struct, 0L, index0, fieldValue);
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

