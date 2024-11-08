package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.webp.ProgressCallback;
import com.github.gotson.nightmonkeys.webp.WebpException;
import com.github.gotson.nightmonkeys.webp.lib.enums.VP8StatusCode;
import com.github.gotson.nightmonkeys.webp.lib.panama.*;
import com.twelvemonkeys.imageio.ImageWriterBase;

import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

/**
 * ImageWriter for WebP format.
 */
public class WebpImageWriter extends ImageWriterBase {

	private Arena arena;
	private MemorySegment encoder;
	private int imageIndex;
	private WebpStreamMetadata webpStreamMetadata;

	WebpImageWriter(ImageWriterSpi provider) {
		super(provider);
	}

	@Override
	public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
		return null;
	}

	@Override
	public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
		return new WebpStreamMetadata();
	}

	@Override
	public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param) {
		return null;
	}

	@Override
	public void prepareWriteSequence(IIOMetadata streamMetadata) throws IOException {
		assertOutput();

		webpStreamMetadata = (WebpStreamMetadata) (streamMetadata != null ? streamMetadata : getDefaultStreamMetadata(null));

		arena = Arena.ofConfined();

		MemorySegment params = WebPMuxAnimParams.allocate(arena);
		WebPMuxAnimParams.bgcolor(params, webpStreamMetadata.getCanvasBackgroundColor());
		WebPMuxAnimParams.loop_count(params, webpStreamMetadata.getLoopCount());

		MemorySegment options = WebPAnimEncoderOptions.allocate(arena);
		if (mux_h.WebPAnimEncoderOptionsInitInternal(options, mux_h.WEBP_MUX_ABI_VERSION()) == 0) {
			throwException("Could not init encoder options");
		}
		WebPAnimEncoderOptions.anim_params(options, params);

		encoder = mux_h.WebPAnimEncoderNewInternal(webpStreamMetadata.getCanvasWidth(), webpStreamMetadata.getCanvasHeight(), options, mux_h.WEBP_MUX_ABI_VERSION());
	}

	@Override
	public void writeToSequence(IIOImage image, ImageWriteParam param) throws IOException {
		assertOutput();
		if (image == null) {
			throw new IllegalArgumentException("Image may not be null");
		}
		if (encoder == null) {
			throw new IllegalStateException("prepareWriteSequence() was not invoked!");
		}

		WebpImageWriteParam webpParam = (WebpImageWriteParam) (param != null ? param : getDefaultWriteParam());
		RenderedImage renderedImage = image.getRenderedImage();
		Raster raster = getRaster(renderedImage);

		clearAbortRequest();
		processImageStarted(imageIndex);
		if (abortRequested()) {
			processWriteAborted();
			return;
		}

		MemorySegment config = createWebpConfig(arena, webpParam);
		MemorySegment picture = createWebpPicture(raster, arena, raster.getWidth(), raster.getHeight(), raster.getNumBands(), progress -> {
			processImageProgress(progress);
			return !abortRequested();
		});

		if (mux_h.WebPAnimEncoderAdd(encoder, picture, webpParam.getTimestamp(), config) == 0) {
			throwException("Could not add picture to animation");
		}

		if (abortRequested()) {
			processWriteAborted();
			return;
		}

		processImageComplete();
		imageIndex++;
	}

	@Override
	public void endWriteSequence() throws IOException {
		assertOutput();
		if (encoder == null) {
			throw new IllegalStateException("prepareWriteSequence() was not invoked!");
		}

		try (Arena localArena = arena) {
			if (mux_h.WebPAnimEncoderAdd(encoder, MemorySegment.NULL, webpStreamMetadata.getTotalDuration(), MemorySegment.NULL) == 0) {
				throwException("Could not finalize animation");
			}
			MemorySegment webpData = WebPData.allocate(localArena);
			if (mux_h.WebPAnimEncoderAssemble(encoder, webpData) == 0) {
				throwException("Could not assemble animation");
			}
			mux_h.WebPAnimEncoderDelete(encoder);

			byte[] bytes = new byte[(int) WebPData.size(webpData)];
			WebPData.bytes(webpData).asSlice(0, bytes.length).asByteBuffer().get(bytes);
			imageOutput.write(bytes);
		} finally {
			encoder = null;
			arena = null;
			webpStreamMetadata = null;
		}
	}

	@Override
	public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IOException {
		assertOutput();

		if (image == null) {
			throw new IllegalArgumentException("image may not be null");
		}

		WebpImageWriteParam webpParam = (WebpImageWriteParam) (param != null ? param : getDefaultWriteParam());

		RenderedImage renderedImage = image.getRenderedImage();
		Raster raster = getRaster(renderedImage);

		processImageStarted(0);
		processImageProgress(0F);
		if (abortRequested()) {
			processWriteAborted();
			return;
		}

		try (Arena localArena = Arena.ofConfined()) {
			MemorySegment config = createWebpConfig(localArena, webpParam);

			MemorySegment picture = createWebpPicture(raster, localArena, raster.getWidth(), raster.getHeight(), raster.getNumBands(), progress -> {
				processImageProgress(progress);
				return !abortRequested();
			});

			MemorySegment writer = WebPMemoryWriter.allocate(localArena);
			encode_h.WebPMemoryWriterInit(writer);
			WebPPicture.writer(picture, WebPWriterFunction.allocate(encode_h::WebPMemoryWrite, localArena));
			WebPPicture.custom_ptr(picture, writer);

			if (encode_h.WebPEncode(config, picture) == 0) {
				throwException("Couldn't encode: " + VP8StatusCode.fromId(WebPPicture.error_code(picture)));
			}

			byte[] bytes = new byte[(int) WebPMemoryWriter.size(writer)];
			WebPMemoryWriter.mem(writer).asSlice(0, bytes.length).asByteBuffer().get(bytes);
			imageOutput.write(bytes);

			encode_h.WebPPictureFree(picture);
		}
		if (abortRequested()) {
			processWriteAborted();
		} else {
			processImageComplete();
		}
	}

	@Override
	public WebpImageWriteParam getDefaultWriteParam() {
		return new WebpImageWriteParam();
	}

	/**
	 * Creates and initializes a WebP configuration segment.
	 *
	 * @param arena The memory arena to allocate the configuration segment.
	 * @param webpParam The parameters for WebP image writing.
	 * @return The initialized WebP configuration segment.
	 * @throws IOException If the configuration initialization fails.
	 */
	private static MemorySegment createWebpConfig(Arena arena, WebpImageWriteParam webpParam) throws IOException {
		MemorySegment config = WebPConfig.allocate(arena);
		if (encode_h.WebPConfigInitInternal(config, webpParam.getPreset().ordinal(), webpParam.getCompressionQuality() * 100F, encode_h.WEBP_ENCODER_ABI_VERSION()) == 0) {
			throwException("Could not init encoder config");
		}
		WebPConfig.method(config, webpParam.getMethod());
		WebPConfig.lossless(config, webpParam.isCompressionLossless() ? 1 : 0);
		return config;
	}

	/**
	 * Creates and initializes a WebP picture segment.
	 *
	 * @param raster The raster containing the image data.
	 * @param arena The memory arena to allocate the picture segment.
	 * @param width The width of the image.
	 * @param height The height of the image.
	 * @param numBands The number of bands in the image.
	 * @param progressCallback The callback to report progress.
	 * @return The initialized WebP picture segment.
	 * @throws IOException If the picture initialization fails.
	 */
	private static MemorySegment createWebpPicture(Raster raster, Arena arena, int width, int height, int numBands, ProgressCallback progressCallback) throws IOException {
		MemorySegment picture = WebPPicture.allocate(arena);
		WebPPicture.width(picture, width);
		WebPPicture.height(picture, height);
		WebPPicture.use_argb(picture, 1);

		int[] intPixelArray = raster.getPixels(0, 0, width, height, (int[]) null);
		byte[] pixelArray = new byte[intPixelArray.length];
		for (int i = 0; i < intPixelArray.length; i++) {
			pixelArray[i] = (byte) intPixelArray[i];
		}
		MemorySegment data = arena.allocateFrom(encode_h.C_CHAR, pixelArray);

		if ((numBands > 3 ? encode_h.WebPPictureImportRGBA(picture, data, width * numBands) : encode_h.WebPPictureImportRGB(picture, data, width * numBands)) == 0) {
			throwException("Could not import picture");
		}

		MemorySegment progressHook = WebPProgressHook.allocate((percent, _) -> progressCallback.onProgress(percent / 100F) ? 1 : 0, arena);
		WebPPicture.progress_hook(picture, progressHook);

		return picture;
	}

	private static void throwException(String message) throws IOException {
		throw new IOException(new WebpException(message));
	}

	/**
	 * Retrieves the raster data from a RenderedImage.
	 *
	 * @param image The rendered image.
	 * @return The raster data of the image.
	 */
	private static Raster getRaster(final RenderedImage image) {
		if (image instanceof BufferedImage bi) {
			return bi.getRaster();
		}
		if (image.getNumXTiles() == 1 && image.getNumYTiles() == 1) {
			return image.getTile(0, 0);
		}
		return image.getData();
	}
}
