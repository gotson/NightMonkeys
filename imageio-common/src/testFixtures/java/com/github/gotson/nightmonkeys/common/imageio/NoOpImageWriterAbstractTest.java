package com.github.gotson.nightmonkeys.common.imageio;

import org.apache.commons.collections4.IteratorUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class NoOpImageWriterAbstractTest {
    protected abstract List<String> getFormatNames();

    protected abstract List<String> getSuffixes();

    protected abstract List<String> getMIMETypes();

    @BeforeAll
    public void printInformation() throws IOException {
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("java.library.path: " + System.getProperty("java.library.path"));
        Enumeration<URL> spiFiles = ClassLoader.getSystemResources("META-INF/services/javax.imageio.spi.ImageWriterSpi");
        Spliterator<URL> spliterator = Spliterators.spliteratorUnknownSize(IteratorUtils.asIterator(spiFiles), Spliterator.ORDERED);
        StreamSupport.stream(spliterator, false).forEachOrdered(x -> System.out.println("SPI File: " + x));
    }

    @ParameterizedTest
    @MethodSource("getFormatNames")
    public void testWriterIsNotRegisteredByFormatName(String formatName) {
        ArrayList<ImageWriter> writers = new ArrayList<>();
        ImageIO.getImageWritersByFormatName(formatName).forEachRemaining(writers::add);

        assertThat(writers).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("getSuffixes")
    public void testWriterIsNotRegisteredBySuffix(String suffix) {
        ArrayList<ImageWriter> writers = new ArrayList<>();
        ImageIO.getImageWritersBySuffix(suffix).forEachRemaining(writers::add);

        assertThat(writers).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("getMIMETypes")
    public void testWriterIsNotRegisteredByMIMEType(String mimeType) {
        ArrayList<ImageWriter> writers = new ArrayList<>();
        ImageIO.getImageWritersByMIMEType(mimeType).forEachRemaining(writers::add);

        assertThat(writers).isEmpty();
    }
}
