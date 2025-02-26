package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.webp.lib.enums.WebpLosslessPresets;
import com.github.gotson.nightmonkeys.webp.lib.enums.WebpPresets;

import javax.imageio.ImageWriteParam;

/**
 * Specialized {@link ImageWriteParam} for WebP encoding.
 */
public class WebpImageWriteParam extends ImageWriteParam {

	public static final String COMPRESSION_TYPE_LOSSY = "LOSSY";
	public static final String COMPRESSION_TYPE_LOSSLESS = "LOSSLESS";

	protected static final String[] COMPRESSION_TYPES = {COMPRESSION_TYPE_LOSSY, COMPRESSION_TYPE_LOSSLESS};

	private int method;
	private WebpPresets preset;
	private int timestamp;

	/**
	 * Constructs a {@code WebpImageWriteParam} with default values.
	 */
	public WebpImageWriteParam() {
		canWriteCompressed = true;
		compressionTypes = COMPRESSION_TYPES;
		setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		unsetCompression();
	}

	/**
	 * Unsets the compression settings and resets to default lossy compression.
	 */
	@Override
	public void unsetCompression() {
		super.unsetCompression();
		setCompressionType(COMPRESSION_TYPE_LOSSY);
		setCompressionQuality(0.75F);
		setMethod(4);
		setPreset(WebpPresets.DEFAULT);
	}

	/**
	 * Checks if the current compression type is lossless.
	 *
	 * @return true if the compression type is lossless, otherwise false.
	 */
	@Override
	public boolean isCompressionLossless() {
		return super.isCompressionLossless() && COMPRESSION_TYPE_LOSSLESS.equals(getCompressionType());
	}

	/**
	 * Sets the lossless preset configuration.
	 *
	 * @param presets the {@link WebpLosslessPresets} to be used.
	 */
	public void setLosslessPreset(WebpLosslessPresets presets) {
		setCompressionType(COMPRESSION_TYPE_LOSSLESS);
		setMethod(presets.getMethod());
		setCompressionQuality(presets.getQuality());
	}

	/**
	 * Gets the current method for quality/speed trade-off.
	 *
	 * @return the method value (0=fast, 6=slower-better).
	 */
	public int getMethod() {
		return method;
	}

	/**
	 * Sets the method for quality/speed trade-off.
	 *
	 * @param method the method value (0=fast, 6=slower-better).
	 */
	public void setMethod(int method) {
		if(method < 0 || method > 6) {
			throw new IllegalArgumentException("Method must be between 0 and 6");
		}
		this.method = method;
	}

	/**
	 * Gets the current preset for WebP encoding.
	 *
	 * @return the current {@link WebpPresets}.
	 */
	public WebpPresets getPreset() {
		return preset;
	}

	/**
	 * Sets the preset for WebP encoding.
	 *
	 * @param preset the {@link WebpPresets} to be used.
	 */
	public void setPreset(WebpPresets preset) {
		this.preset = preset;
	}

	/**
	 * Gets the timestamp (in ms) for the image.
	 *
	 * @return the timestamp value.
	 */
	public int getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the absolute timestamp (in ms) for when to display the image in.
	 * Only used for animated WebP images.
	 *
	 * @param timestamp the timestamp value.
	 */
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
}