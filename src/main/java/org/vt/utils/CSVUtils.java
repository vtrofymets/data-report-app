package org.vt.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class CSVUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtils.class);

    private CSVUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> List<T> mapToObject(String file, Class<T> clazz) {
        try (BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(file, "file is null")))) {
            return new CsvToBeanBuilder<T>(br).withType(clazz)
                    .build()
                    .parse();
        } catch (IOException ex) {
            LOGGER.error("Receive error when try to map csv data from file: '{}' to object: {}", file, clazz.getName(),
                    ex);
            throw new RuntimeException(ex);
        }
    }

}
