package org.vt.utils;

import org.vt.dto.InputFileDto;
import org.vt.dto.ReportType;
import org.vt.dto.StorageType;

import static org.vt.utils.ResourcesUtils.extractFileContent;

public class DataUtils {

    public static final String FILE_TEST_DATA_PATH = "./src/test/resources/input_data/";

    public static final InputFileDto FILE_DTO = InputFileDto.of("unit-test-data.csv", FILE_TEST_DATA_PATH, 1000, StorageType.LOCAL, ReportType.TEAM_AVG_ESTIMATE);

    public static final String EXPECTED_REPORT_CONTENT = extractFileContent("./src/test/resources/expected_data/data-report.txt");

}
