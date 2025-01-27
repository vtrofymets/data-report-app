package org.vt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.dto.InputFileDto;
import org.vt.service.report.generators.ReportGenerator;
import org.vt.service.storage.clients.StorageClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class GenerateReportProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportProcess.class);

    private final StorageClient storageClient;
    private final ReportGenerator reportGenerator;

    public GenerateReportProcess(StorageClient storageClient, ReportGenerator reportGenerator) {
        this.storageClient = storageClient;
        this.reportGenerator = reportGenerator;
    }

    public void generateProcess(InputFileDto fileDto) {
        LOGGER.info("Start processing fileDto: {}", fileDto);
        try (InputStream inputStream = storageClient.receiveInputData(fileDto.getFileName(),
                fileDto.getFilePath()); BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            Stream<String> lines = br.lines();
            String report = reportGenerator.buildReport(lines);
            storageClient.uploadReport(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
