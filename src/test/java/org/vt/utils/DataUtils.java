package org.vt.utils;

import org.vt.model.FileDto;
import org.vt.model.Storage;

import static org.vt.utils.ResourcesUtils.extractFileContent;

public class DataUtils {

    public static final String FILE_TEST_DATA_PATH = "./src/test/resources/input_data/unit-test-data.csv";

    public static final String EXPECTED_REPORT_CONTENT = extractFileContent("./src/test/resources/expected_data/data-report.txt");

    public static final FileDto FILE_DTO = FileDto.of("unit-test-data.csv", FILE_TEST_DATA_PATH, 1000, Storage.LOCAL);

}
