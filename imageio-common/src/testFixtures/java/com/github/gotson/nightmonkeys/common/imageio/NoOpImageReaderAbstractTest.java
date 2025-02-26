package com.github.gotson.nightmonkeys.common.imageio;

import org.apache.commons.collections4.IteratorUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class NoOpImageReaderAbstractTest<T extends ImageReader> {
    private final ProviderInfo provider = createProvider();

    protected abstract ProviderInfo createProvider();

    private List<String> getFormatNames() {
        return Arrays.asList(provider.getFormatNames());
    }

    private List<String> getSuffixes() {
        return Arrays.asList(provider.getSuffixes());
    }

    private List<String> getMIMETypes() {
        return Arrays.asList(provider.getMimeTypes());
    }

    @BeforeAll
    public void printInformation() throws IOException {
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("java.library.path: " + System.getProperty("java.library.path"));
        Enumeration<URL> spiFiles = ClassLoader.getSystemResources("META-INF/services/javax.imageio.spi.ImageReaderSpi");
        Spliterator<URL> spliterator = Spliterators.spliteratorUnknownSize(IteratorUtils.asIterator(spiFiles), Spliterator.ORDERED);
        StreamSupport.stream(spliterator, false).forEachOrdered(x -> System.out.println("SPI File: " + x));
    }

    @ParameterizedTest
    @MethodSource("getFormatNames")
    public void testReaderIsNotRegisteredByFormatName(String formatName) {
        ArrayList<ImageReader> readers = new ArrayList<>();
        ImageIO.getImageReadersByFormatName(formatName).forEachRemaining(readers::add);

        assertThat(readers).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("getSuffixes")
    public void testReaderIsNotRegisteredBySuffix(String suffix) {
        ArrayList<ImageReader> readers = new ArrayList<>();
        ImageIO.getImageReadersBySuffix(suffix).forEachRemaining(readers::add);

        assertThat(readers).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("getMIMETypes")
    public void testReaderIsNotRegisteredByMIMEType(String mimeType) {
        ArrayList<ImageReader> readers = new ArrayList<>();
        ImageIO.getImageReadersByMIMEType(mimeType).forEachRemaining(readers::add);

        assertThat(readers).isEmpty();
    }
}
