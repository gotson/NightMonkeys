package com.github.gotson.nightmonkeys.jxl;

import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestUtils {

    public static File getRessourceFile(String fixtureFile) {
        return new File(TestUtils.class.getResource(fixtureFile).getFile());
    }

    public static InputStream getResourceAsInputStream(String fixtureFile) {
        return TestUtils.class.getResourceAsStream(fixtureFile);
    }

    public static Stream<Arguments> provideAllFixtureFiles() throws Exception {
        Path resources = Paths.get(TestUtils.class.getResource("hills.jxl").toURI()).getParent();
        return Files.list(resources)
            .filter(x -> x.toString().endsWith(".jxl"))
            .map(x -> Arguments.of(x.getFileName().toString()));
    }
}
