package org.vt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.model.FileDto;
import org.vt.utils.ResourcesUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.vt.utils.Constants.COMA_SPACE_DELIMITER;
import static org.vt.utils.Constants.EMPTY_STRING;
import static org.vt.utils.Constants.REPORT_TITLE;

public class StreamsLocalReportGenerationImpl extends AbstractLocalStorageReportGeneration {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsLocalReportGenerationImpl.class);

    public String buildDataReport(final FileDto fileDto) {
        Stream<String> inputData = ResourcesUtils.readFileLines(fileDto.getFilePath());

        Map<String, Map<Double, Integer>> collect = collectDataByTeamAvgEstimateAndSize(inputData);

        LOGGER.info("Collect report data: {}", collect.size());
        LOGGER.debug("Collect report data: {}", collect);

        String report = buildReport(collect);

        LOGGER.debug("Building report: {}", report);

        return report;
    }

    private static Map<String, Map<Double, Integer>> collectDataByTeamAvgEstimateAndSize(Stream<String> inputData) {
        return inputData.skip(1)
                .map(s -> s.split(","))
                .collect(Collectors.groupingBy(s -> s[s.length - 1], Collectors.mapping(s -> s[6],
                        Collectors.collectingAndThen(Collectors.toList(), l -> Stream.of(l)
                                .collect(Collectors.toMap(k -> k.stream()
                                        .collect(Collectors.averagingDouble(Double::valueOf)), List::size))))));
    }

    private static String buildReport(Map<String, Map<Double, Integer>> collect) {
        return collect.entrySet()
                .stream()
                .map(entry -> entry.getKey() + COMA_SPACE_DELIMITER + entry.getValue()
                        .entrySet()
                        .stream()
                        .map(innerEntry -> innerEntry.getValue() + COMA_SPACE_DELIMITER + innerEntry.getKey() + "\n")
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(EMPTY_STRING, REPORT_TITLE + "\n", EMPTY_STRING));
    }
}
