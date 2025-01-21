package org.vt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public final class ResourcesUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesUtils.class);

    private ResourcesUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> readFileLines(String fileLocation) {
        LOGGER.info("Try to extract data from file: '{}'", fileLocation);
        try (Stream<String> lines = Files.lines(Paths.get(fileLocation))) {
            return lines.toList();
        } catch (IOException e) {
            LOGGER.error("Receive error when try to extract data from: {}", fileLocation, e);
            throw new RuntimeException(e);
        }
    }

    public static String extractFileContent(String fileLocation) {
        LOGGER.info("Try to extract content from file: '{}'", fileLocation);
        Path path = Paths.get(fileLocation);
        try {
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Receive error when try to extract data from: {}", fileLocation, e);
            throw new RuntimeException(e);
        }
    }
}
