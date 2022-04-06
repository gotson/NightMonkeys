package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import com.github.gotson.nightmonkeys.jxl.TestUtils;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class JpegXlImageReaderTest {

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

    private JxlImageReader getReader(String fixtureFile) throws IOException {
        File inFile = TestUtils.getRessourceFile(fixtureFile);
        ImageInputStream is = ImageIO.createImageInputStream(inFile);
        ImageReader reader = ImageIO.getImageReaders(is).next();
        assertThat(reader).isInstanceOf(JxlImageReader.class);
        reader.setInput(is);
        return (JxlImageReader) reader;
    }

    @Test
    public void testRead() throws IOException {
        BufferedImage img = ((ImageReader) getReader("hills.jxl")).read(0, null);
        assertThat(img.getWidth()).isEqualTo(610);
        assertThat(img.getHeight()).isEqualTo(407);
    }

    @Test
    public void testCanReuseReader() throws Exception {
        ImageReader reader = getReader("hills.jxl");
        BufferedImage rgbImg = reader.read(0, null);

        reader.setInput(
            ImageIO.createImageInputStream(TestUtils.getRessourceFile("island.jxl")));
        BufferedImage bwImg = reader.read(0, null);

        assertThat(rgbImg.getRGB(256, 256)).isNotEqualTo(bwImg.getRGB(256, 256));
    }
}
