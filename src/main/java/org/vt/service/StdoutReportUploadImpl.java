package org.vt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StdoutReportUploadImpl implements ReportUpload {

    private static final Logger LOGGER = LoggerFactory.getLogger(StdoutReportUploadImpl.class);

    @Override
    public void uploadReport(final String report) {
        LOGGER.info("Start uploading report: {} to stdout", report.length());
        LOGGER.debug("Start uploading report: {} to stdout", report);
        System.out.println(report);
    }
}
