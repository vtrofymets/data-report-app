package org.vt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.dto.InputFileDto;
import org.vt.dto.ReportType;
import org.vt.dto.StorageType;
import org.vt.service.GenerateReportProcess;
import org.vt.service.report.generators.ReportGenerator;
import org.vt.service.report.generators.TeamAvgReportGenerator;
import org.vt.service.storage.clients.LocalStorageClient;
import org.vt.service.storage.clients.StorageClient;


public class ReportGenerationApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportGenerationApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting ReportGenerationApplication V2");

        InputFileDto fileDto = InputFileDto.of("input-data.csv", "./src/main/resources/", 1000, StorageType.LOCAL,
                ReportType.TEAM_AVG_ESTIMATE);

        StorageClient localReportClient = new LocalStorageClient();
        ReportGenerator teamAvgReportGenerator = new TeamAvgReportGenerator();
        GenerateReportProcess generateReportProcess = new GenerateReportProcess(localReportClient,
                teamAvgReportGenerator);

        generateReportProcess.generateProcess(fileDto);
    }
}
