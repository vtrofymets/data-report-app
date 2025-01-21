package org.vt.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.model.FileDto;
import org.vt.model.Storage;
import org.vt.service.ReportGeneration;
import org.vt.service.ReportUpload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReportOutputProcessing {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportOutputProcessing.class);

    private final Map<Storage, ReportGeneration> reportGenerationMap;
    private final ReportUpload reportUpload;

    public ReportOutputProcessing(List<ReportGeneration> reportGenerations, ReportUpload reportUpload) {
        Objects.requireNonNull(reportGenerations, "reportGenerations cannot be null!");
        this.reportUpload = Objects.requireNonNull(reportUpload, "ReportUpload cannot be null!");
        this.reportGenerationMap = reportGenerations.stream()
                .collect(Collectors.toMap(ReportGeneration::storage, Function.identity(), (v1, v2) -> v1));
    }

    public void reportOutput(final FileDto fileDto) {
        LOGGER.info("Start processing file {}", fileDto);
        ReportGeneration reportGeneration = Optional.ofNullable(reportGenerationMap.get(fileDto.getStorage()))
                .orElseThrow(() -> new IllegalStateException(
                        "No report generation found for storage " + fileDto.getStorage()));
        String report = reportGeneration.buildDataReport(fileDto);
        reportUpload.uploadReport(report);
    }
}
