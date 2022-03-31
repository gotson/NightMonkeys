package com.github.gotson.nightmonkeys.jxl.lib.enums;

import com.github.gotson.nightmonkeys.jxl.lib.panama.decode_h;

/**
 * Return value for JxlDecoderProcessInput.
 * The values above 0x40 are optional informal events that can be subscribed to,
 * they are never returned if they have not been registered with
 * JxlDecoderSubscribeEvents.
 */
public enum JxlDecoderStatus {
    /**
     * Function call finished successfully, or decoding is finished and there is
     * nothing more to be done.
     */
    JXL_DEC_SUCCESS(decode_h.JXL_DEC_SUCCESS()),

    /**
     * An error occurred, for example invalid input file or out of memory.
     */
    JXL_DEC_ERROR(decode_h.JXL_DEC_ERROR()),

    /**
     * The decoder needs more input bytes to continue. Before the next
     * JxlDecoderProcessInput call, more input data must be set, by calling
     * JxlDecoderReleaseInput (if input was set previously) and then calling
     * JxlDecoderSetInput. JxlDecoderReleaseInput returns how many bytes are
     * not yet processed, before a next call to JxlDecoderProcessInput all
     * unprocessed bytes must be provided again (the address need not match, but
     * the contents must), and more bytes must be concatenated after the
     * unprocessed bytes.
     */
    JXL_DEC_NEED_MORE_INPUT(decode_h.JXL_DEC_NEED_MORE_INPUT()),

    /**
     * The decoder is able to decode a preview image and requests setting a
     * preview output buffer using JxlDecoderSetPreviewOutBuffer. This occurs if
     * JXL_DEC_PREVIEW_IMAGE is requested and it is possible to decode a preview
     * image from the codestream and the preview out buffer was not yet set. There
     * is maximum one preview image in a codestream.
     */
    JXL_DEC_NEED_PREVIEW_OUT_BUFFER(decode_h.JXL_DEC_NEED_PREVIEW_OUT_BUFFER()),

    /**
     * The decoder is able to decode a DC image and requests setting a DC output
     * buffer using JxlDecoderSetDCOutBuffer. This occurs if JXL_DEC_DC_IMAGE is
     * requested and it is possible to decode a DC image from the codestream and
     * the DC out buffer was not yet set. This event re-occurs for new frames
     * if there are multiple animation frames.
     * DEPRECATED: the DC feature in this form will be removed. You can use
     * JxlDecoderFlushImage for progressive rendering.
     */
    JXL_DEC_NEED_DC_OUT_BUFFER(decode_h.JXL_DEC_NEED_DC_OUT_BUFFER()),

    /**
     * The decoder requests an output buffer to store the full resolution image,
     * which can be set with JxlDecoderSetImageOutBuffer or with
     * JxlDecoderSetImageOutCallback. This event re-occurs for new frames if there
     * are multiple animation frames and requires setting an output again.
     */
    JXL_DEC_NEED_IMAGE_OUT_BUFFER(decode_h.JXL_DEC_NEED_IMAGE_OUT_BUFFER()),

    /**
     * Informative event by JxlDecoderProcessInput: JPEG reconstruction buffer is
     * too small for reconstructed JPEG codestream to fit.
     * JxlDecoderSetJPEGBuffer must be called again to make room for remaining
     * bytes. This event may occur multiple times after
     * JXL_DEC_JPEG_RECONSTRUCTION
     */
    JXL_DEC_JPEG_NEED_MORE_OUTPUT(decode_h.JXL_DEC_JPEG_NEED_MORE_OUTPUT()),

    /**
     * Informative event by JxlDecoderProcessInput: basic information such as
     * image dimensions and extra channels. This event occurs max once per image.
     */
    JXL_DEC_BASIC_INFO(decode_h.JXL_DEC_BASIC_INFO()),

    /**
     * Informative event by JxlDecoderProcessInput: user extensions of the
     * codestream header. This event occurs max once per image and always later
     * than JXL_DEC_BASIC_INFO and earlier than any pixel data.
     */
    JXL_DEC_EXTENSIONS(decode_h.JXL_DEC_EXTENSIONS()),

    /**
     * Informative event by JxlDecoderProcessInput: color encoding or ICC
     * profile from the codestream header. This event occurs max once per image
     * and always later than JXL_DEC_BASIC_INFO and earlier than any pixel
     * data.
     */
    JXL_DEC_COLOR_ENCODING(decode_h.JXL_DEC_COLOR_ENCODING()),

    /**
     * Informative event by JxlDecoderProcessInput: Preview image, a small
     * frame, decoded. This event can only happen if the image has a preview
     * frame encoded. This event occurs max once for the codestream and always
     * later than JXL_DEC_COLOR_ENCODING and before JXL_DEC_FRAME.
     */
    JXL_DEC_PREVIEW_IMAGE(decode_h.JXL_DEC_PREVIEW_IMAGE()),

    /**
     * Informative event by JxlDecoderProcessInput: Beginning of a frame.
     * JxlDecoderGetFrameHeader can be used at this point. A note on frames:
     * a JPEG XL image can have internal frames that are not intended to be
     * displayed (e.g. used for compositing a final frame), but this only returns
     * displayed frames. A displayed frame either has an animation duration or is
     * the only or last frame in the image. This event occurs max once per
     * displayed frame, always later than JXL_DEC_COLOR_ENCODING, and always
     * earlier than any pixel data. While JPEG XL supports encoding a single frame
     * as the composition of multiple internal sub-frames also called frames, this
     * event is not indicated for the internal frames.
     */
    JXL_DEC_FRAME(decode_h.JXL_DEC_FRAME()),

    /**
     * Informative event by JxlDecoderProcessInput: DC image, 8x8 sub-sampled
     * frame, decoded. It is not guaranteed that the decoder will always return DC
     * separately, but when it does it will do so before outputting the full
     * frame. JxlDecoderSetDCOutBuffer must be used after getting the basic
     * image information to be able to get the DC pixels, if not this return
     * status only indicates we're past this point in the codestream. This event
     * occurs max once per frame and always later than JXL_DEC_FRAME_HEADER
     * and other header events and earlier than full resolution pixel data.
     * DEPRECATED: the DC feature in this form will be removed. You can use
     * JxlDecoderFlushImage for progressive rendering.
     */
    JXL_DEC_DC_IMAGE(decode_h.JXL_DEC_DC_IMAGE()),

    /**
     * Informative event by JxlDecoderProcessInput: full frame decoded.
     * JxlDecoderSetImageOutBuffer must be used after getting the basic image
     * information to be able to get the image pixels, if not this return status
     * only indicates we're past this point in the codestream. This event occurs
     * max once per frame and always later than JXL_DEC_DC_IMAGE.
     */
    JXL_DEC_FULL_IMAGE(decode_h.JXL_DEC_FULL_IMAGE()),

    /**
     * Informative event by JxlDecoderProcessInput: JPEG reconstruction data
     * decoded. JxlDecoderSetJPEGBuffer may be used to set a JPEG
     * reconstruction buffer after getting the JPEG reconstruction data. If a JPEG
     * reconstruction buffer is set a byte stream identical to the JPEG codestream
     * used to encode the image will be written to the JPEG reconstruction buffer
     * instead of pixels to the image out buffer. This event occurs max once per
     * image and always before JXL_DEC_FULL_IMAGE.
     */
    JXL_DEC_JPEG_RECONSTRUCTION(decode_h.JXL_DEC_JPEG_RECONSTRUCTION());

    private final int val;

    JxlDecoderStatus(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static JxlDecoderStatus fromId(int id) {
        for (JxlDecoderStatus type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
