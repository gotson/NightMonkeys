package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import org.apache.commons.collections4.IteratorUtils;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

class JpegXlImageReaderTest {

    @Test
    public void testReaderIsNotRegistered() throws IOException {
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("java.library.path: " + System.getProperty("java.library.path"));
        Enumeration<URL> spiFiles = ClassLoader.getSystemResources("META-INF/services/javax.imageio.spi.ImageReaderSpi");
        Spliterator<URL> spliterator = Spliterators.spliteratorUnknownSize(IteratorUtils.asIterator(spiFiles), Spliterator.ORDERED);
        StreamSupport.stream(spliterator, false).forEachOrdered(x -> System.out.println("SPI File: " + x));

        ArrayList<ImageReader> readers = new ArrayList<>();
        ImageIO.getImageReadersBySuffix("jxl").forEachRemaining(readers::add);

        assertThat(readers).isEmpty();

        ImageIO.getImageReadersByMIMEType("image/jxl").forEachRemaining(readers::add);

        assertThat(readers).isEmpty();

        ImageIO.getImageReadersByFormatName("jxl").forEachRemaining(readers::add);

        assertThat(readers).isEmpty();
    }
}
