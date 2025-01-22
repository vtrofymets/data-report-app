package org.vt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public final class ResourcesUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesUtils.class);

    private ResourcesUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Stream<String> readFileLines(String fileLocation) {
        LOGGER.info("Try to extract data from file: '{}'", fileLocation);
        try {
            return Files.lines(Paths.get(fileLocation));
        } catch (IOException e) {
            LOGGER.error("Receive error when try to extract data from: {}", fileLocation, e);
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

    public static File writeContentToFile(List<String> content) {
        try {
            Path path = Files.createTempFile(Path.of("."), UUID.randomUUID()
                    .toString(), ".txt");
            File tempFile = path.toFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            for (String line : content) {
                writer.write(line);
            }

            writer.close();

            return tempFile;

        } catch (IOException e) {
            throw new RuntimeException("While writeFileContent get error: " + e);
        }
    }
}
