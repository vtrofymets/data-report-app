package org.vt.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vt.service.report.generators.TeamAvgReportGenerator;
import org.vt.utils.ResourcesUtils;

import java.util.stream.Stream;

import static org.vt.utils.DataUtils.EXPECTED_REPORT_CONTENT;
import static org.vt.utils.DataUtils.FILE_DTO;

public class ReportGenerationTest {

    private TeamAvgReportGenerator sut;

    @Test
    public void streamsReportGeneration_expectedFileReportContent() {
        sut = new TeamAvgReportGenerator();


        Stream<String> stringStream = ResourcesUtils.readFileLines(FILE_DTO.getFilePath() + FILE_DTO.getFileName());

        String actual = sut.buildReport(stringStream);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(EXPECTED_REPORT_CONTENT, actual);
    }

}
