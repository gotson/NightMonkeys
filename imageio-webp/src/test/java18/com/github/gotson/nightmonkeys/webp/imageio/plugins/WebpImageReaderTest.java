package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ImageReaderBaseTest;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class WebpImageReaderTest extends ImageReaderBaseTest<WebpImageReader> {

    @Test
    public void testReaderIsRegistered() {
        ArrayList<ImageReader> readers = new ArrayList<>();
        ImageIO.getImageReadersBySuffix("webp").forEachRemaining(readers::add);

        assertThat(readers)
            .isNotEmpty()
            .hasAtLeastOneElementOfType(WebpImageReader.class);


        readers.clear();
        ImageIO.getImageReadersByMIMEType("image/webp").forEachRemaining(readers::add);

        assertThat(readers)
            .isNotEmpty()
            .hasAtLeastOneElementOfType(WebpImageReader.class);


        readers.clear();
        ImageIO.getImageReadersByFormatName("webp").forEachRemaining(readers::add);

        assertThat(readers)
            .isNotEmpty()
            .hasAtLeastOneElementOfType(WebpImageReader.class);
    }

    @Test
    public void testRead() throws IOException {
        BufferedImage img = ((ImageReader) getReader("/lossless.webp")).read(0, null);
        assertThat(img.getWidth()).isEqualTo(400);
        assertThat(img.getHeight()).isEqualTo(301);
    }

    @Test
    public void testCanReuseReader() throws Exception {
        ImageReader reader = getReader("/lossy.webp");
        BufferedImage rgbImg = reader.read(0, null);

        reader.setInput(
            ImageIO.createImageInputStream(getClassLoaderResourceAsFile("/lossless.webp")));
        BufferedImage bwImg = reader.read(0, null);

        assertThat(rgbImg.getRGB(256, 256)).isNotEqualTo(bwImg.getRGB(256, 256));
    }
}
