package org.vt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.facade.ReportOutputProcessing;
import org.vt.model.FileDto;
import org.vt.model.Storage;
import org.vt.service.Array2DLocalReportGenerationImpl;
import org.vt.service.ReportUpload;
import org.vt.service.StdoutReportUploadImpl;


public class ReportGenerationApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportGenerationApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting ReportGenerationApplication");

        String filePath = "./src/main/resources/input-data.csv";
        FileDto fileDto = FileDto.of("input-data.csv", filePath, 1000, Storage.LOCAL);

        //      ReportGeneration reportGeneration = new StreamsReportGenerationImpl();
        Array2DLocalReportGenerationImpl reportGeneration = new Array2DLocalReportGenerationImpl();
//        ReportGeneration reportGeneration = new CSVReportGenerationImpl();
        ReportUpload reportUpload = new StdoutReportUploadImpl();
        ReportOutputProcessing reportOutputProcessing = new ReportOutputProcessing(reportGeneration, reportUpload);
        reportOutputProcessing.reportOutput(fileDto);
    }
}
