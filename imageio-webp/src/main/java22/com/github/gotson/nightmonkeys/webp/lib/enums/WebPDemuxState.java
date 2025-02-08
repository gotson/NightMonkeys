package com.github.gotson.nightmonkeys.webp.lib.enums;


import com.github.gotson.nightmonkeys.webp.lib.panama.demux_h;

public enum WebPDemuxState {
    WEBP_DEMUX_PARSE_ERROR(demux_h.WEBP_DEMUX_PARSE_ERROR()),
    WEBP_DEMUX_PARSING_HEADER(demux_h.WEBP_DEMUX_PARSING_HEADER()),
    WEBP_DEMUX_PARSED_HEADER(demux_h.WEBP_DEMUX_PARSED_HEADER()),
    WEBP_DEMUX_DONE(demux_h.WEBP_DEMUX_DONE());

    private final int val;

    WebPDemuxState(int val) {
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static WebPDemuxState fromId(int id) {
        for (WebPDemuxState type : values()) {
            if (type.intValue() == id) {
                return type;
            }
        }
        return null;
    }
}
