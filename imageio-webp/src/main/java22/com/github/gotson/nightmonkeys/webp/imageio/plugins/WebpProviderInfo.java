package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.twelvemonkeys.imageio.spi.ReaderWriterProviderInfo;

final class WebpProviderInfo extends ReaderWriterProviderInfo {

	WebpProviderInfo() {
		super(
			WebpProviderInfo.class,
			new String[] {"webp", "WEBP"},
			new String[] {"webp"},
			new String[] {"image/webp"},
			"com.github.gotson.nightmonkeys.webp.imageio.WebPImageReader",
			new String[] {"com.github.gotson.nightmonkeys.webp.imageio.plugins.WebpImageReaderSpi"},
			"com.github.gotson.nightmonkeys.webp.imageio.WebPImageWriter",
			new String[] {"com.github.gotson.nightmonkeys.webp.imageio.plugins.WebpImageWriterSpi"},
			false,
			null, null, null, null,
			false,
			null, null, null, null
		);
	}
}
