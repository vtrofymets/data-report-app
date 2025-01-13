package org.vt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.facade.ReportOutputProcessing;
import org.vt.service.Array2DReportGenerationImpl;
import org.vt.service.CSVReportGenerationImpl;
import org.vt.service.ReportGeneration;
import org.vt.service.ReportUpload;
import org.vt.service.StdoutReportUploadImpl;


public class ReportGenerationApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportGenerationApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting ReportGenerationApplication");

        String file = "./src/main/resources/input-data.csv";
//      ReportGeneration reportGeneration = new StreamsReportGenerationImpl();
        Array2DReportGenerationImpl reportGeneration = new Array2DReportGenerationImpl();
//        ReportGeneration reportGeneration = new CSVReportGenerationImpl();
        ReportUpload reportUpload = new StdoutReportUploadImpl();
        ReportOutputProcessing reportOutputProcessing = new ReportOutputProcessing(reportGeneration, reportUpload);
        reportOutputProcessing.reportOutput(file);
    }
}
