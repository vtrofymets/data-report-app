package org.vt.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.vt.utils.DataUtils.EXPECTED_REPORT_CONTENT;
import static org.vt.utils.DataUtils.FILE_TEST_DATA_PATH;

public class ReportGenerationTest {

    private ReportGeneration sut;

    @Test
    public void streamsReportGeneration_expectedFileReportContent() {
        sut = new StreamsReportGenerationImpl();

        String actual = sut.buildDataReport(FILE_TEST_DATA_PATH);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(EXPECTED_REPORT_CONTENT, actual);
    }

    @Test
    public void csvReportGeneration_expectedFileReportContent() {
        sut = new CSVReportGenerationImpl();

        String actual = sut.buildDataReport(FILE_TEST_DATA_PATH);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(EXPECTED_REPORT_CONTENT, actual);
    }

}
