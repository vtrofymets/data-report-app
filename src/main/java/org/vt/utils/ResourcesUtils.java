package org.vt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class ResourcesUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesUtils.class);

    private ResourcesUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Stream<String> readFileLines(String fileLocation) {
        return readFileLines(Paths.get(fileLocation));
    }

    public static Stream<String> readFileLines(Path path) {
        LOGGER.info("Try to extract data from path: '{}'", path);
        try {
            return Files.lines(path);
        } catch (IOException e) {
            LOGGER.error("Receive error when try to extract data path: {}", path, e);
            throw new RuntimeException(e);
        }
    }

    public static String extractFileContent(String fileLocation) {
        LOGGER.info("Try to extract content from file: '{}'", fileLocation);
        try {
            return Files.readString(Paths.get(fileLocation), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Receive error when try to extract data from: {}", fileLocation, e);
            throw new RuntimeException(e);
        }
    }
}
