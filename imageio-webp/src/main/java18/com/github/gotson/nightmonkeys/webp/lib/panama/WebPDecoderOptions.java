// Generated by jextract

package com.github.gotson.nightmonkeys.webp.lib.panama;

import jdk.incubator.foreign.GroupLayout;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemoryLayout;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;
import jdk.incubator.foreign.SegmentAllocator;

import java.lang.invoke.VarHandle;

public class WebPDecoderOptions {

    static final GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("bypass_filtering"),
        Constants$root.C_INT$LAYOUT.withName("no_fancy_upsampling"),
        Constants$root.C_INT$LAYOUT.withName("use_cropping"),
        Constants$root.C_INT$LAYOUT.withName("crop_left"),
        Constants$root.C_INT$LAYOUT.withName("crop_top"),
        Constants$root.C_INT$LAYOUT.withName("crop_width"),
        Constants$root.C_INT$LAYOUT.withName("crop_height"),
        Constants$root.C_INT$LAYOUT.withName("use_scaling"),
        Constants$root.C_INT$LAYOUT.withName("scaled_width"),
        Constants$root.C_INT$LAYOUT.withName("scaled_height"),
        Constants$root.C_INT$LAYOUT.withName("use_threads"),
        Constants$root.C_INT$LAYOUT.withName("dithering_strength"),
        Constants$root.C_INT$LAYOUT.withName("flip"),
        Constants$root.C_INT$LAYOUT.withName("alpha_dithering_strength"),
        MemoryLayout.sequenceLayout(5, Constants$root.C_INT$LAYOUT).withName("pad")
    ).withName("WebPDecoderOptions");

    public static MemoryLayout $LAYOUT() {
        return WebPDecoderOptions.$struct$LAYOUT;
    }

    static final VarHandle bypass_filtering$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("bypass_filtering"));

    public static VarHandle bypass_filtering$VH() {
        return WebPDecoderOptions.bypass_filtering$VH;
    }

    public static int bypass_filtering$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.bypass_filtering$VH.get(seg);
    }

    public static void bypass_filtering$set(MemorySegment seg, int x) {
        WebPDecoderOptions.bypass_filtering$VH.set(seg, x);
    }

    public static int bypass_filtering$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.bypass_filtering$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void bypass_filtering$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.bypass_filtering$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle no_fancy_upsampling$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("no_fancy_upsampling"));

    public static VarHandle no_fancy_upsampling$VH() {
        return WebPDecoderOptions.no_fancy_upsampling$VH;
    }

    public static int no_fancy_upsampling$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.no_fancy_upsampling$VH.get(seg);
    }

    public static void no_fancy_upsampling$set(MemorySegment seg, int x) {
        WebPDecoderOptions.no_fancy_upsampling$VH.set(seg, x);
    }

    public static int no_fancy_upsampling$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.no_fancy_upsampling$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void no_fancy_upsampling$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.no_fancy_upsampling$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle use_cropping$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("use_cropping"));

    public static VarHandle use_cropping$VH() {
        return WebPDecoderOptions.use_cropping$VH;
    }

    public static int use_cropping$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.use_cropping$VH.get(seg);
    }

    public static void use_cropping$set(MemorySegment seg, int x) {
        WebPDecoderOptions.use_cropping$VH.set(seg, x);
    }

    public static int use_cropping$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.use_cropping$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void use_cropping$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.use_cropping$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle crop_left$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("crop_left"));

    public static VarHandle crop_left$VH() {
        return WebPDecoderOptions.crop_left$VH;
    }

    public static int crop_left$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.crop_left$VH.get(seg);
    }

    public static void crop_left$set(MemorySegment seg, int left) {
        WebPDecoderOptions.crop_left$VH.set(seg, left);
    }

    public static int crop_left$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.crop_left$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void crop_left$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.crop_left$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle crop_top$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("crop_top"));

    public static VarHandle crop_top$VH() {
        return WebPDecoderOptions.crop_top$VH;
    }

    public static int crop_top$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.crop_top$VH.get(seg);
    }

    public static void crop_top$set(MemorySegment seg, int top) {
        WebPDecoderOptions.crop_top$VH.set(seg, top);
    }

    public static int crop_top$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.crop_top$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void crop_top$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.crop_top$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle crop_width$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("crop_width"));

    public static VarHandle crop_width$VH() {
        return WebPDecoderOptions.crop_width$VH;
    }

    public static int crop_width$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.crop_width$VH.get(seg);
    }

    public static void crop_width$set(MemorySegment seg, int width) {
        WebPDecoderOptions.crop_width$VH.set(seg, width);
    }

    public static int crop_width$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.crop_width$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void crop_width$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.crop_width$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle crop_height$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("crop_height"));

    public static VarHandle crop_height$VH() {
        return WebPDecoderOptions.crop_height$VH;
    }

    public static int crop_height$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.crop_height$VH.get(seg);
    }

    public static void crop_height$set(MemorySegment seg, int height) {
        WebPDecoderOptions.crop_height$VH.set(seg, height);
    }

    public static int crop_height$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.crop_height$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void crop_height$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.crop_height$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle use_scaling$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("use_scaling"));

    public static VarHandle use_scaling$VH() {
        return WebPDecoderOptions.use_scaling$VH;
    }

    public static int use_scaling$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.use_scaling$VH.get(seg);
    }

    public static void use_scaling$set(MemorySegment seg, int x) {
        WebPDecoderOptions.use_scaling$VH.set(seg, x);
    }

    public static int use_scaling$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.use_scaling$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void use_scaling$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.use_scaling$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle scaled_width$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("scaled_width"));

    public static VarHandle scaled_width$VH() {
        return WebPDecoderOptions.scaled_width$VH;
    }

    public static int scaled_width$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.scaled_width$VH.get(seg);
    }

    public static void scaled_width$set(MemorySegment seg, int x) {
        WebPDecoderOptions.scaled_width$VH.set(seg, x);
    }

    public static int scaled_width$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.scaled_width$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void scaled_width$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.scaled_width$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle scaled_height$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("scaled_height"));

    public static VarHandle scaled_height$VH() {
        return WebPDecoderOptions.scaled_height$VH;
    }

    public static int scaled_height$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.scaled_height$VH.get(seg);
    }

    public static void scaled_height$set(MemorySegment seg, int x) {
        WebPDecoderOptions.scaled_height$VH.set(seg, x);
    }

    public static int scaled_height$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.scaled_height$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void scaled_height$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.scaled_height$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle use_threads$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("use_threads"));

    public static VarHandle use_threads$VH() {
        return WebPDecoderOptions.use_threads$VH;
    }

    public static int use_threads$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.use_threads$VH.get(seg);
    }

    public static void use_threads$set(MemorySegment seg, int x) {
        WebPDecoderOptions.use_threads$VH.set(seg, x);
    }

    public static int use_threads$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.use_threads$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void use_threads$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.use_threads$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle dithering_strength$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("dithering_strength"));

    public static VarHandle dithering_strength$VH() {
        return WebPDecoderOptions.dithering_strength$VH;
    }

    public static int dithering_strength$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.dithering_strength$VH.get(seg);
    }

    public static void dithering_strength$set(MemorySegment seg, int x) {
        WebPDecoderOptions.dithering_strength$VH.set(seg, x);
    }

    public static int dithering_strength$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.dithering_strength$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void dithering_strength$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.dithering_strength$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle flip$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("flip"));

    public static VarHandle flip$VH() {
        return WebPDecoderOptions.flip$VH;
    }

    public static int flip$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.flip$VH.get(seg);
    }

    public static void flip$set(MemorySegment seg, int x) {
        WebPDecoderOptions.flip$VH.set(seg, x);
    }

    public static int flip$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.flip$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void flip$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.flip$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle alpha_dithering_strength$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("alpha_dithering_strength"));

    public static VarHandle alpha_dithering_strength$VH() {
        return WebPDecoderOptions.alpha_dithering_strength$VH;
    }

    public static int alpha_dithering_strength$get(MemorySegment seg) {
        return (int) WebPDecoderOptions.alpha_dithering_strength$VH.get(seg);
    }

    public static void alpha_dithering_strength$set(MemorySegment seg, int x) {
        WebPDecoderOptions.alpha_dithering_strength$VH.set(seg, x);
    }

    public static int alpha_dithering_strength$get(MemorySegment seg, long index) {
        return (int) WebPDecoderOptions.alpha_dithering_strength$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void alpha_dithering_strength$set(MemorySegment seg, long index, int x) {
        WebPDecoderOptions.alpha_dithering_strength$VH.set(seg.asSlice(index * sizeof()), x);
    }

    public static MemorySegment pad$slice(MemorySegment seg) {
        return seg.asSlice(56, 20);
    }

    public static long sizeof() {
        return $LAYOUT().byteSize();
    }

    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate($LAYOUT());
    }

    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }

    public static MemorySegment allocate(ResourceScope scope) {
        return allocate(SegmentAllocator.nativeAllocator(scope));
    }

    public static MemorySegment allocateArray(int len, ResourceScope scope) {
        return allocateArray(len, SegmentAllocator.nativeAllocator(scope));
    }

    public static MemorySegment ofAddress(MemoryAddress addr, ResourceScope scope) {
        return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope);
    }
}


