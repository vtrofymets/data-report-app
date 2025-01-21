package org.vt.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.vt.utils.DataUtils.EXPECTED_REPORT_CONTENT;
import static org.vt.utils.DataUtils.FILE_DTO;

public class ReportGenerationTest {

    private ReportGeneration sut;

    @Test
    public void streamsReportGeneration_expectedFileReportContent() {
        sut = new StreamsLocalReportGenerationImpl();

        String actual = sut.buildDataReport(FILE_DTO);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(EXPECTED_REPORT_CONTENT, actual);
    }

    @Test
    public void csvReportGeneration_expectedFileReportContent() {
        sut = new CSVReportLocalGenerationImpl();

        String actual = sut.buildDataReport(FILE_DTO);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(EXPECTED_REPORT_CONTENT, actual);
    }

}
