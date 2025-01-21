package org.vt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.model.FileDto;
import org.vt.model.InputData;
import org.vt.model.ReportData;
import org.vt.utils.CSVUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.vt.utils.Constants.COMA_SPACE_DELIMITER;
import static org.vt.utils.Constants.EMPTY_STRING;
import static org.vt.utils.Constants.REPORT_HEADERS;

public class Array2DLocalReportGenerationImpl extends AbstractLocalStorageReportGeneration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Array2DLocalReportGenerationImpl.class);

    public static final Stream<List<String>> HEADERS_LIST = Stream.of(REPORT_HEADERS.toList());

    @Override
    public String buildDataReport(final FileDto fileDto) {
        List<InputData> inputData = CSVUtils.mapToObject(fileDto.getFilePath(), InputData.class);

        LOGGER.info("Mapped inputData: {}", inputData.size());
        LOGGER.debug("Mapped inputData: {}", inputData);

        Map<ReportData, ReportData> collect = inputData.stream()
                .map(ReportData::from)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collector.of(ReportData::new, ReportData::merge, (r1, r2) -> {
                            throw new UnsupportedOperationException("Parallel not supported!");
                        })));

        return Stream.concat(HEADERS_LIST, collect.values()
                        .stream()
                        .map(reportData -> List.of(reportData.getTeam(), String.valueOf(reportData.getTotalEffort()),
                                String.valueOf(reportData.getRemainingEffort()))))
                .map(list -> list.stream()
                        .collect(Collectors.joining(COMA_SPACE_DELIMITER, EMPTY_STRING, "\n")))
                .collect(Collectors.joining());
    }

}


