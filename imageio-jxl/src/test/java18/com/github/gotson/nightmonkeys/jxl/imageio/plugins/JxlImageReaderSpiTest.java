package com.github.gotson.nightmonkeys.jxl.imageio.plugins;

import com.github.gotson.nightmonkeys.jxl.TestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class JxlImageReaderSpiTest {
    private final JxlImageReaderSpi readerSpi = new JxlImageReaderSpi();

    @ParameterizedTest
    @MethodSource("com.github.gotson.nightmonkeys.jxl.TestUtils#provideAllFixtureFiles")
    void canDecode(String fixtureFile) throws Exception {
        try (InputStream stream = TestUtils.getResourceAsInputStream(fixtureFile)) {
            boolean canDecodeInput = readerSpi.canDecodeInput(stream);

            assertThat(canDecodeInput).isTrue();
        }
    }
}