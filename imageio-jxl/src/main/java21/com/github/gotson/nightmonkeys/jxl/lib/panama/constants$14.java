// Generated by jextract

package com.github.gotson.nightmonkeys.jxl.lib.panama;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

final class constants$14 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$14() {
    }

    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        constants$13.const$4
    );
    static final FunctionDescriptor const$1 = FunctionDescriptor.ofVoid(
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$2 = RuntimeHelper.upcallHandle(jpegxl_free_func.class, "apply", constants$14.const$1);
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        constants$14.const$1
    );
    static final StructLayout const$4 = MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("opaque"),
        RuntimeHelper.POINTER.withName("alloc"),
        RuntimeHelper.POINTER.withName("free")
    ).withName("JxlMemoryManagerStruct");
    static final VarHandle const$5 = constants$14.const$4.varHandle(MemoryLayout.PathElement.groupElement("opaque"));
}


