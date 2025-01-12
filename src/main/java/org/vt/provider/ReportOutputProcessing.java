package org.vt.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.service.ReportGeneration;
import org.vt.service.ReportUpload;

import java.util.Objects;

public class ReportOutputProcessing {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportOutputProcessing.class);

    private final ReportGeneration reportGeneration;
    private final ReportUpload reportUpload;

    public ReportOutputProcessing(ReportGeneration reportGeneration, ReportUpload reportUpload) {
        this.reportGeneration = Objects.requireNonNull(reportGeneration, "ReportGeneration cannot be null!");
        this.reportUpload = Objects.requireNonNull(reportUpload, "ReportUpload cannot be null!");

    }

    public void reportOutput(final String filePath) {
        LOGGER.info("Start processing file {}", filePath);
        String report = reportGeneration.buildDataReport(filePath);
        reportUpload.uploadReport(report);
    }
}
