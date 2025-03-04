package com.github.gotson.nightmonkeys.common.imageio;

import javax.imageio.spi.ImageWriterSpi;

public abstract class ImageWriterSpiBase extends ImageWriterSpi {
    protected ImageWriterSpiBase(final ProviderInfo provider) {
        super(
            provider.getVendorName(),
            provider.getVersion(),
            provider.getFormatNames(),
            provider.getSuffixes(),
            provider.getMimeTypes(),
            provider.getWriterClassName(),
            provider.getOutputTypes(),
            provider.getReaderSpiClassNames(),
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
