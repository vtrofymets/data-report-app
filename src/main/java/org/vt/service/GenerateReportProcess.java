package org.vt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.dto.InputFileDto;
import org.vt.dto.ReportType;
import org.vt.dto.StorageType;
import org.vt.service.report.generators.ReportGenerator;
import org.vt.service.storage.clients.StorageClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateReportProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportProcess.class);

    private final Map<StorageType, StorageClient> storageClients;
    private final Map<ReportType, ReportGenerator> reportGenerators;

    public GenerateReportProcess(List<StorageClient> storageClients, List<ReportGenerator> reportGenerators) {
        this.storageClients = storageClients.stream().collect(Collectors.toMap(StorageClient::storageType, Function.identity()));
        this.reportGenerators = reportGenerators.stream().collect(Collectors.toMap(ReportGenerator::reportType, Function.identity()));
    }

    public void startProcessReport(final InputFileDto fileDto) {
        LOGGER.info("Start processing fileDto: {}", fileDto);

        StorageType storage = fileDto.getStorage();
        ReportType reportType = fileDto.getReportType();
        StorageClient storageClient = Optional.ofNullable(storageClients.get(storage)).orElseThrow(() -> new UnsupportedOperationException("No storage client found for " + storage));
        ReportGenerator reportGenerator = Optional.ofNullable(reportGenerators.get(reportType)).orElseThrow(() -> new UnsupportedOperationException("No report generator found for " + reportType));

        try (InputStream inputStream = storageClient.receiveInputData(fileDto.getFileName(), fileDto.getFilePath());
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            Stream<String> lines = br.lines();
            String report = reportGenerator.buildReport(lines);
            storageClient.uploadReport(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
