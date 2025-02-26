package com.github.gotson.nightmonkeys.common.imageio;

import javax.imageio.spi.ImageReaderSpi;

public abstract class ImageReaderSpiBase extends ImageReaderSpi {
    protected ImageReaderSpiBase(final ProviderInfo provider) {
        super(
            provider.getVendorName(),
            provider.getVersion(),
            provider.getFormatNames(),
            provider.getSuffixes(),
            provider.getMimeTypes(),
            provider.getReaderClassName(),
            provider.getInputTypes(),
            provider.getWriterSpiClassNames(),
            provider.isSupportsStandardStreamMetadata(),
            provider.getNativeStreamMetadataFormatName(),
            provider.getNativeStreamMetadataFormatClassName(),
            provider.getExtraStreamMetadataFormatNames(),
            provider.getExtraStreamMetadataFormatClassNames(),
            provider.isSupportsStandardImageMetadata(),
            provider.getNativeImageMetadataFormatName(),
            provider.getNativeImageMetadataFormatClassName(),
            provider.getExtraImageMetadataFormatNames(),
            provider.getExtraImageMetadataFormatClassNames()
        );
    }
}
