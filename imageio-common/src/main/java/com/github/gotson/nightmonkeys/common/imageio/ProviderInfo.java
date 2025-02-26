package com.github.gotson.nightmonkeys.common.imageio;

import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class ProviderInfo {
    private final String vendorName = "NightMonkeys";
    private final String version = "1.0.0";
    private final String[] formatNames;
    private final String[] suffixes;
    private final String[] mimeTypes;
    private final String readerClassName;
    private final String[] readerSpiClassNames;
    private final Class<?>[] inputTypes = new Class<?>[] {ImageInputStream.class};
    private final String writerClassName;
    private final String[] writerSpiClassNames;
    private final Class<?>[] outputTypes = new Class<?>[] {ImageOutputStream.class};
    private final boolean supportsStandardStreamMetadata;
    private final String nativeStreamMetadataFormatName;
    private final String nativeStreamMetadataFormatClassName;
    private final String[] extraStreamMetadataFormatNames;
    private final String[] extraStreamMetadataFormatClassNames;
    private final boolean supportsStandardImageMetadata;
    private final String nativeImageMetadataFormatName;
    private final String nativeImageMetadataFormatClassName;
    private final String[] extraImageMetadataFormatNames;
    private final String[] extraImageMetadataFormatClassNames;

    public ProviderInfo(
        String[] formatNames,
        String[] suffixes,
        String[] mimeTypes,
        String readerClassName,
        String[] readerSpiClassNames,
        String writerClassName,
        String[] writerSpiClassNames,
        boolean supportsStandardStreamMetadata,
        String nativeStreamMetadataFormatName,
        String nativeStreamMetadataFormatClassName,
        String[] extraStreamMetadataFormatNames,
        String[] extraStreamMetadataFormatClassNames,
        boolean supportsStandardImageMetadata,
        String nativeImageMetadataFormatName,
        String nativeImageMetadataFormatClassName,
        String[] extraImageMetadataFormatNames,
        String[] extraImageMetadataFormatClassNames
    ) {
        this.formatNames = formatNames;
        this.suffixes = suffixes;
        this.mimeTypes = mimeTypes;
        this.readerClassName = readerClassName;
        this.readerSpiClassNames = readerSpiClassNames;
        this.writerClassName = writerClassName;
        this.writerSpiClassNames = writerSpiClassNames;
        this.supportsStandardStreamMetadata = supportsStandardStreamMetadata;
        this.nativeStreamMetadataFormatName = nativeStreamMetadataFormatName;
        this.nativeStreamMetadataFormatClassName = nativeStreamMetadataFormatClassName;
        this.extraStreamMetadataFormatNames = extraStreamMetadataFormatNames;
        this.extraStreamMetadataFormatClassNames = extraStreamMetadataFormatClassNames;
        this.supportsStandardImageMetadata = supportsStandardImageMetadata;
        this.nativeImageMetadataFormatName = nativeImageMetadataFormatName;
        this.nativeImageMetadataFormatClassName = nativeImageMetadataFormatClassName;
        this.extraImageMetadataFormatNames = extraImageMetadataFormatNames;
        this.extraImageMetadataFormatClassNames = extraImageMetadataFormatClassNames;
    }

    public String[] getExtraImageMetadataFormatClassNames() {
        return extraImageMetadataFormatClassNames;
    }

    public String[] getExtraImageMetadataFormatNames() {
        return extraImageMetadataFormatNames;
    }

    public String[] getExtraStreamMetadataFormatClassNames() {
        return extraStreamMetadataFormatClassNames;
    }

    public String[] getExtraStreamMetadataFormatNames() {
        return extraStreamMetadataFormatNames;
    }

    public String[] getFormatNames() {
        return formatNames;
    }

    public Class<?>[] getInputTypes() {
        return inputTypes;
    }

    public String[] getMimeTypes() {
        return mimeTypes;
    }

    public String getNativeImageMetadataFormatClassName() {
        return nativeImageMetadataFormatClassName;
    }

    public String getNativeImageMetadataFormatName() {
        return nativeImageMetadataFormatName;
    }

    public String getNativeStreamMetadataFormatClassName() {
        return nativeStreamMetadataFormatClassName;
    }

    public String getNativeStreamMetadataFormatName() {
        return nativeStreamMetadataFormatName;
    }

    public Class<?>[] getOutputTypes() {
        return outputTypes;
    }

    public String getReaderClassName() {
        return readerClassName;
    }

    public String[] getReaderSpiClassNames() {
        return readerSpiClassNames;
    }

    public String[] getSuffixes() {
        return suffixes;
    }

    public boolean isSupportsStandardImageMetadata() {
        return supportsStandardImageMetadata;
    }

    public boolean isSupportsStandardStreamMetadata() {
        return supportsStandardStreamMetadata;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getVersion() {
        return version;
    }

    public String getWriterClassName() {
        return writerClassName;
    }

    public String[] getWriterSpiClassNames() {
        return writerSpiClassNames;
    }
}
