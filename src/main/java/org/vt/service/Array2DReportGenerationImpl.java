package org.vt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.model.InputData;
import org.vt.model.ReportData;
import org.vt.utils.CSVUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.vt.utils.Constants.COMA_SPACE_DELIMITER;
import static org.vt.utils.Constants.REPORT_HEADERS;

public class Array2DReportGenerationImpl implements ReportGeneration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Array2DReportGenerationImpl.class);

    @Override
    public String buildDataReport(final String file) {
        List<InputData> inputData = CSVUtils.mapToObject(file, InputData.class);

        LOGGER.info("Mapped inputData: {}", inputData.size());
        LOGGER.debug("Mapped inputData: {}", inputData);

        var collect = inputData.stream()
                .map(ReportData::from)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collector.of(ReportData::new, ReportData::merge, (r1, r2) -> {
                            throw new UnsupportedOperationException("Parallel not supported!");
                        })));

        String[][] values = collect.values()
                .stream()
                .map(reportData -> new String[]{reportData.getTeam(), String.valueOf(reportData.getTotalEffort()), String.valueOf(reportData.getRemainingEffort())})
                .toArray(String[][]::new);

        return buildReport(values);
    }

    private static String buildReport(String[][] values) {
        StringBuilder report = new StringBuilder();

        for (int i = 0; i < REPORT_HEADERS.length; i++) {
            if (i == REPORT_HEADERS.length - 1) {
                report.append(REPORT_HEADERS[i]).append("\n");
            } else {
                report.append(REPORT_HEADERS[i]).append(COMA_SPACE_DELIMITER);
            }
        }

        for (String[] value : values) {
            for (int j = 0; j < values[j].length; j++) {
                if (j == values[j].length - 1) {
                    report.append(value[j]).append("\n");
                } else {
                    report.append(value[j]).append(COMA_SPACE_DELIMITER);
                }
            }
        }

        return report.toString();
    }

}


