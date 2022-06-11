package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.twelvemonkeys.imageio.util.ImageReaderAbstractTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class WebpImageReaderTest extends ImageReaderAbstractTest<WebpImageReader> {

    @Override
    protected ImageReaderSpi createProvider() {
        return new WebpImageReaderSpi();
    }

    @Override
    protected List<TestData> getTestData() {
        return asList(
            // Original Google WebP sample files
            new TestData(getClassLoaderResource("/webp/1.webp"), new Dimension(550, 368)),
            new TestData(getClassLoaderResource("/webp/5.webp"), new Dimension(1024, 752)),
            // Various samples from javavp8codec project
            new TestData(getClassLoaderResource("/webp/bug3.webp"), new Dimension(95, 95)),
            new TestData(getClassLoaderResource("/webp/segment01.webp"), new Dimension(160, 160)),
            new TestData(getClassLoaderResource("/webp/segment02.webp"), new Dimension(160, 160)),
            new TestData(getClassLoaderResource("/webp/segment03.webp"), new Dimension(160, 160)),
            new TestData(getClassLoaderResource("/webp/small_1x1.webp"), new Dimension(1, 1)),
            new TestData(getClassLoaderResource("/webp/small_1x13.webp"), new Dimension(1, 13)),
            new TestData(getClassLoaderResource("/webp/small_13x1.webp"), new Dimension(13, 1)),
            new TestData(getClassLoaderResource("/webp/small_31x13.webp"), new Dimension(31, 13)),
            new TestData(getClassLoaderResource("/webp/test.webp"), new Dimension(128, 128)),
            new TestData(getClassLoaderResource("/webp/very_short.webp"), new Dimension(63, 66)),
            // Lossless
            new TestData(getClassLoaderResource("/webp/1_webp_ll.webp"), new Dimension(400, 301)),
            // Extended format: Alpha + VP8
            new TestData(getClassLoaderResource("/webp/1_webp_a.webp"), new Dimension(400, 301))
            // Extendad format: Anim
//            new TestData(getClassLoaderResource("/webp/animated-webp-supported.webp"), new Dimension(400, 400))
        );
    }

    @Override
    protected List<String> getFormatNames() {
        return List.of("webp", "WebP");
    }

    @Override
    protected List<String> getSuffixes() {
        return List.of("webp");
    }

    @Override
    protected List<String> getMIMETypes() {
        return List.of("image/webp");
    }

    @Test
    @Override
    public void testReadWithSourceRegionParamEqualImage() throws IOException {
        // when cropping, libwebp will snap to even values
        // this overrides the default rectangle to pass the test
        assertReadWithSourceRegionParamEqualImage(new Rectangle(4, 4, 9, 9), getTestData().get(0), 0);
    }

    @Test
    @Disabled("libwebp doesn't allow fine-grained control on subsampling")
    @Override
    public void testReadWithSubsampleParamPixels() throws IOException {
        super.testReadWithSubsampleParamPixels();
    }

    @Disabled("TODO: ICC profile is not handled yet")
    @Test
    public void testReadAndApplyICCProfile() throws IOException {
        WebpImageReader reader = createReader();

        try (ImageInputStream stream = ImageIO.createImageInputStream(getClassLoaderResource("/webp/photo-iccp-adobergb.webp"))) {
            reader.setInput(stream);

            // We'll read a small portion of the image into a destination type that use sRGB
            ImageReadParam param = new ImageReadParam();
            param.setDestinationType(ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_3BYTE_BGR));
            param.setSourceRegion(new Rectangle(20, 20));

            BufferedImage image = reader.read(0, param);
            assertRGBEquals("RGB values differ, incorrect ICC profile or conversion?", 0xFFEA9600, image.getRGB(10, 10), 8);
        } finally {
            reader.dispose();
        }
    }

    @Test
    public void testRec601ColorConversion() throws IOException {
        WebpImageReader reader = createReader();

        try (ImageInputStream stream = ImageIO.createImageInputStream(getClassLoaderResource("/webp/blue_tile.webp"))) {
            reader.setInput(stream);

            BufferedImage image = reader.read(0, null);
            assertRGBEquals("RGB values differ, incorrect Y'CbCr -> RGB conversion", 0xFF72AED5, image.getRGB(80, 80), 1);
        }
        finally {
            reader.dispose();
        }
    }
}
