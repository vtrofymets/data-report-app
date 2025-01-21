package org.vt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.facade.ReportOutputProcessing;
import org.vt.model.FileDto;
import org.vt.model.Storage;
import org.vt.service.Array2DLocalReportGenerationImpl;
import org.vt.service.CSVReportLocalGenerationImpl;
import org.vt.service.ReportGeneration;
import org.vt.service.ReportUpload;
import org.vt.service.StdoutReportUploadImpl;
import org.vt.service.StreamsLocalReportGenerationImpl;

import java.util.List;


public class ReportGenerationApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportGenerationApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting ReportGenerationApplication");

        String filePath = "./src/main/resources/input-data.csv";
        FileDto fileDto = FileDto.of("input-data.csv", filePath, 1000, Storage.LOCAL);

        ReportGeneration reportGeneration1 = new StreamsLocalReportGenerationImpl();
        Array2DLocalReportGenerationImpl reportGeneration2 = new Array2DLocalReportGenerationImpl();
        ReportGeneration reportGeneration3 = new CSVReportLocalGenerationImpl();

        List<ReportGeneration> reportGenerations = List.of(reportGeneration1, reportGeneration2, reportGeneration3);

        ReportUpload reportUpload = new StdoutReportUploadImpl();
        ReportOutputProcessing reportOutputProcessing = new ReportOutputProcessing(reportGenerations, reportUpload);

        reportOutputProcessing.reportOutput(fileDto);
    }
}
