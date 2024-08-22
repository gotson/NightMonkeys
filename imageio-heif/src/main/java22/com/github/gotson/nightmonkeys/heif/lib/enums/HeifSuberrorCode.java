package com.github.gotson.nightmonkeys.heif.lib.enums;

import com.github.gotson.nightmonkeys.heif.lib.panama.heif_h;

public enum HeifSuberrorCode {
    HEIF_SUBERROR_UNSPECIFIED(heif_h.heif_suberror_Unspecified()),

    // --- Invalid_input ---

    // End of data reached unexpectedly.
    HEIF_SUBERROR_END_OF_DATA(heif_h.heif_suberror_End_of_data()),

    // Size of box (defined in header) is wrong
    HEIF_SUBERROR_INVALID_BOX_SIZE(heif_h.heif_suberror_Invalid_box_size()),

    // Mandatory 'ftyp' box is missing
    HEIF_SUBERROR_NO_FTYP_BOX(heif_h.heif_suberror_No_ftyp_box()),
    HEIF_SUBERROR_NO_IDAT_BOX(heif_h.heif_suberror_No_idat_box()),
    HEIF_SUBERROR_NO_META_BOX(heif_h.heif_suberror_No_meta_box()),
    HEIF_SUBERROR_NO_HDLR_BOX(heif_h.heif_suberror_No_hdlr_box()),
    HEIF_SUBERROR_NO_HVCC_BOX(heif_h.heif_suberror_No_hvcC_box()),
    HEIF_SUBERROR_NO_PITM_BOX(heif_h.heif_suberror_No_pitm_box()),
    HEIF_SUBERROR_NO_IPCO_BOX(heif_h.heif_suberror_No_ipco_box()),
    HEIF_SUBERROR_NO_IPMA_BOX(heif_h.heif_suberror_No_ipma_box()),
    HEIF_SUBERROR_NO_ILOC_BOX(heif_h.heif_suberror_No_iloc_box()),
    HEIF_SUBERROR_NO_IINF_BOX(heif_h.heif_suberror_No_iinf_box()),
    HEIF_SUBERROR_NO_IPRP_BOX(heif_h.heif_suberror_No_iprp_box()),
    HEIF_SUBERROR_NO_IREF_BOX(heif_h.heif_suberror_No_iref_box()),
    HEIF_SUBERROR_NO_PICT_HANDLER(heif_h.heif_suberror_No_pict_handler()),

    // An item property referenced in the 'ipma' box is not existing in the 'ipco' container.
    HEIF_SUBERROR_IPMA_BOX_REFERENCES_NONEXISTING_PROPERTY(heif_h.heif_suberror_Ipma_box_references_nonexisting_property()),

    // No properties have been assigned to an item.
    HEIF_SUBERROR_NO_PROPERTIES_ASSIGNED_TO_ITEM(heif_h.heif_suberror_No_properties_assigned_to_item()),

    // Image has no (compressed) data
    HEIF_SUBERROR_NO_ITEM_DATA(heif_h.heif_suberror_No_item_data()),

    // Invalid specification of image grid (tiled image)
    HEIF_SUBERROR_INVALID_GRID_DATA(heif_h.heif_suberror_Invalid_grid_data()),

    // Tile-images in a grid image are missing
    HEIF_SUBERROR_MISSING_GRID_IMAGES(heif_h.heif_suberror_Missing_grid_images()),
    HEIF_SUBERROR_INVALID_CLEAN_APERTURE(heif_h.heif_suberror_Invalid_clean_aperture()),

    // Invalid specification of overlay image
    HEIF_SUBERROR_INVALID_OVERLAY_DATA(heif_h.heif_suberror_Invalid_overlay_data()),

    // Overlay image completely outside of visible canvas area
    HEIF_SUBERROR_OVERLAY_IMAGE_OUTSIDE_OF_CANVAS(heif_h.heif_suberror_Overlay_image_outside_of_canvas()),
    HEIF_SUBERROR_AUXILIARY_IMAGE_TYPE_UNSPECIFIED(heif_h.heif_suberror_Auxiliary_image_type_unspecified()),
    HEIF_SUBERROR_NO_OR_INVALID_PRIMARY_ITEM(heif_h.heif_suberror_No_or_invalid_primary_item()),
    HEIF_SUBERROR_NO_INFE_BOX(heif_h.heif_suberror_No_infe_box()),
    HEIF_SUBERROR_UNKNOWN_COLOR_PROFILE_TYPE(heif_h.heif_suberror_Unknown_color_profile_type()),
    HEIF_SUBERROR_WRONG_TILE_IMAGE_CHROMA_FORMAT(heif_h.heif_suberror_Wrong_tile_image_chroma_format()),
    HEIF_SUBERROR_INVALID_FRACTIONAL_NUMBER(heif_h.heif_suberror_Invalid_fractional_number()),
    HEIF_SUBERROR_INVALID_IMAGE_SIZE(heif_h.heif_suberror_Invalid_image_size()),
    HEIF_SUBERROR_INVALID_PIXI_BOX(heif_h.heif_suberror_Invalid_pixi_box()),
    HEIF_SUBERROR_NO_AV1C_BOX(heif_h.heif_suberror_No_av1C_box()),
    HEIF_SUBERROR_WRONG_TILE_IMAGE_PIXEL_DEPTH(heif_h.heif_suberror_Wrong_tile_image_pixel_depth()),
    HEIF_SUBERROR_UNKNOWN_NCLX_COLOR_PRIMARIES(heif_h.heif_suberror_Unknown_NCLX_color_primaries()),
    HEIF_SUBERROR_UNKNOWN_NCLX_TRANSFER_CHARACTERISTICS(heif_h.heif_suberror_Unknown_NCLX_transfer_characteristics()),
    HEIF_SUBERROR_UNKNOWN_NCLX_MATRIX_COEFFICIENTS(heif_h.heif_suberror_Unknown_NCLX_matrix_coefficients()),

    // Invalid specification of region item
    HEIF_SUBERROR_INVALID_REGION_DATA(heif_h.heif_suberror_Invalid_region_data()),

    // --- Memory_allocation_error ---

    // A security limit preventing unreasonable memory allocations was exceeded by the input file.
    // Please check whether the file is valid. If it is, contact us so that we could increase the
    // security limits further.
    HEIF_SUBERROR_SECURITY_LIMIT_EXCEEDED(heif_h.heif_suberror_Security_limit_exceeded()),

    // --- Usage_error ---

    // An item ID was used that is not present in the file.
    HEIF_SUBERROR_NONEXISTING_ITEM_REFERENCED(heif_h.heif_suberror_Nonexisting_item_referenced()), // also used for Invalid_input

    // An API argument was given a NULL pointer, which is not allowed for that function.
    HEIF_SUBERROR_NULL_POINTER_ARGUMENT(heif_h.heif_suberror_Null_pointer_argument()),

    // Image channel referenced that does not exist in the image
    HEIF_SUBERROR_NONEXISTING_IMAGE_CHANNEL_REFERENCED(heif_h.heif_suberror_Nonexisting_image_channel_referenced()),

    // The version of the passed plugin is not supported.
    HEIF_SUBERROR_UNSUPPORTED_PLUGIN_VERSION(heif_h.heif_suberror_Unsupported_plugin_version()),

    // The version of the passed writer is not supported.
    HEIF_SUBERROR_UNSUPPORTED_WRITER_VERSION(heif_h.heif_suberror_Unsupported_writer_version()),

    // The given (encoder) parameter name does not exist.
    HEIF_SUBERROR_UNSUPPORTED_PARAMETER(heif_h.heif_suberror_Unsupported_parameter()),

    // The value for the given parameter is not in the valid range.
    HEIF_SUBERROR_INVALID_PARAMETER_VALUE(heif_h.heif_suberror_Invalid_parameter_value()),

    // Error in property specification
    HEIF_SUBERROR_INVALID_PROPERTY(heif_h.heif_suberror_Invalid_property()),

    // Image reference cycle found in iref
    HEIF_SUBERROR_ITEM_REFERENCE_CYCLE(heif_h.heif_suberror_Item_reference_cycle()),


    // --- Unsupported_feature ---

    // Image was coded with an unsupported compression method.
    HEIF_SUBERROR_UNSUPPORTED_CODEC(heif_h.heif_suberror_Unsupported_codec()),

    // Image is specified in an unknown way, e.g. as tiled grid image (which is supported)
    HEIF_SUBERROR_UNSUPPORTED_IMAGE_TYPE(heif_h.heif_suberror_Unsupported_image_type()),
    HEIF_SUBERROR_UNSUPPORTED_DATA_VERSION(heif_h.heif_suberror_Unsupported_data_version()),

    // The conversion of the source image to the requested chroma / colorspace is not supported.
    HEIF_SUBERROR_UNSUPPORTED_COLOR_CONVERSION(heif_h.heif_suberror_Unsupported_color_conversion()),
    HEIF_SUBERROR_UNSUPPORTED_ITEM_CONSTRUCTION_METHOD(heif_h.heif_suberror_Unsupported_item_construction_method()),
    HEIF_SUBERROR_UNSUPPORTED_HEADER_COMPRESSION_METHOD(heif_h.heif_suberror_Unsupported_header_compression_method()),

    // --- Encoder_plugin_error ---
    HEIF_SUBERROR_UNSUPPORTED_BIT_DEPTH(heif_h.heif_suberror_Unsupported_bit_depth()),

    // --- Encoding_error ---
    HEIF_SUBERROR_CANNOT_WRITE_OUTPUT_DATA(heif_h.heif_suberror_Cannot_write_output_data()),
    HEIF_SUBERROR_ENCODER_INITIALIZATION(heif_h.heif_suberror_Encoder_initialization()),
    HEIF_SUBERROR_ENCODER_ENCODING(heif_h.heif_suberror_Encoder_encoding()),
    HEIF_SUBERROR_ENCODER_CLEANUP(heif_h.heif_suberror_Encoder_cleanup()),
    HEIF_SUBERROR_TOO_MANY_REGIONS(heif_h.heif_suberror_Too_many_regions()),

    // --- Plugin loading error ---
    HEIF_SUBERROR_PLUGIN_LOADING_ERROR(heif_h.heif_suberror_Plugin_loading_error()),        // a specific plugin file cannot be loaded
    HEIF_SUBERROR_PLUGIN_IS_NOT_LOADED(heif_h.heif_suberror_Plugin_is_not_loaded()),        // trying to remove a plugin that is not loaded
    HEIF_SUBERROR_CANNOT_READ_PLUGIN_DIRECTORY(heif_h.heif_suberror_Cannot_read_plugin_directory()); // error while scanning the directory for plugins

    private final int val;

    HeifSuberrorCode(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static HeifSuberrorCode fromId(int id) {
        for (HeifSuberrorCode type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
