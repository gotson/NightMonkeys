package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ImageReaderBaseTest;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class JpegXlImageReaderTest extends ImageReaderBaseTest<JxlImageReader> {

    @Test
    public void testReaderIsRegistered() {
        ArrayList<ImageReader> readers = new ArrayList<>();
        ImageIO.getImageReadersBySuffix("jxl").forEachRemaining(readers::add);

        assertThat(readers)
            .isNotEmpty()
            .hasAtLeastOneElementOfType(JxlImageReader.class);


        readers.clear();
        ImageIO.getImageReadersByMIMEType("image/jxl").forEachRemaining(readers::add);

        assertThat(readers)
            .isNotEmpty()
            .hasAtLeastOneElementOfType(JxlImageReader.class);


        readers.clear();
        ImageIO.getImageReadersByFormatName("jxl").forEachRemaining(readers::add);

        assertThat(readers)
            .isNotEmpty()
            .hasAtLeastOneElementOfType(JxlImageReader.class);
    }

    @Test
    public void testRead() throws IOException {
        BufferedImage img = getReader("/hills.jxl").read(0, null);
        assertThat(img.getWidth()).isEqualTo(610);
        assertThat(img.getHeight()).isEqualTo(407);
    }

    @Test
    public void testCanReuseReader() throws Exception {
        ImageReader reader = getReader("/hills.jxl");
        BufferedImage rgbImg = reader.read(0, null);

        reader.setInput(
            ImageIO.createImageInputStream(getClassLoaderResourceAsFile("/island.jxl")));
        BufferedImage bwImg = reader.read(0, null);

        assertThat(rgbImg.getRGB(256, 256)).isNotEqualTo(bwImg.getRGB(256, 256));
    }
}
