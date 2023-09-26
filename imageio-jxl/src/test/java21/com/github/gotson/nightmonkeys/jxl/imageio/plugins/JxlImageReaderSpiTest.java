package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ImageReaderSpiBaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class JxlImageReaderSpiTest extends ImageReaderSpiBaseTest {
    private final JxlImageReaderSpi readerSpi = new JxlImageReaderSpi();

    public static Stream<Arguments> provideAllFixtureFiles() throws Exception {
        Path resources = Paths.get(getClassLoaderResource("/hills.jxl").toURI()).getParent();
        return Files.list(resources)
            .filter(x -> x.toString().endsWith(".jxl"))
            .map(x -> Arguments.of("/" + x.getFileName().toString()));
    }

    @ParameterizedTest
    @MethodSource("provideAllFixtureFiles")
    void canDecode(String fixtureFile) throws Exception {
        try (InputStream stream = getClassLoaderResourceAsInputStream(fixtureFile)) {
            boolean canDecodeInput = readerSpi.canDecodeInput(stream);

            assertThat(canDecodeInput).isTrue();
        }
    }
}