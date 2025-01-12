package org.vt.utils;

import static org.vt.utils.ResourcesUtils.extractFileContent;

public class DataUtils {

    public static final String FILE_TEST_DATA_PATH = "./src/test/resources/input_data/unit-test-data.csv";

    public static final String EXPECTED_REPORT_CONTENT = extractFileContent("./src/test/resources/expected_data/data-report.txt");

}
