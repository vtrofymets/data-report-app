package org.vt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.model.FileDto;
import org.vt.model.InputData;
import org.vt.model.ReportData;
import org.vt.utils.CSVUtils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.vt.utils.Constants.COMA_SPACE_DELIMITER;
import static org.vt.utils.Constants.EMPTY_STRING;
import static org.vt.utils.Constants.REPORT_TITLE;

public class CSVReportLocalGenerationImpl extends AbstractLocalStorageReportGeneration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVReportLocalGenerationImpl.class);

    @Override
    public String buildDataReport(final FileDto fileDto) {
        List<InputData> inputData = CSVUtils.mapToObject(fileDto.getFilePath(), InputData.class);

        LOGGER.info("Mapped inputData: {}", inputData.size());
        LOGGER.debug("Mapped inputData: {}", inputData);

        return inputData.stream()
                .map(ReportData::from)
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(Function.identity(), Function.identity(), ReportData::merge),
                        map -> buildReport(map.values())));
    }

    private static String buildReport(Collection<ReportData> reportDataCollection) {
        return reportDataCollection.stream()
                .map(CSVReportLocalGenerationImpl::concatValues)
                .collect(Collectors.joining(EMPTY_STRING, REPORT_TITLE + "\n", EMPTY_STRING));
    }

    private static String concatValues(ReportData reportData) {
        return reportData.getTeam() + COMA_SPACE_DELIMITER + reportData.getTotalEffort() + COMA_SPACE_DELIMITER + reportData.getRemainingEffort() + "\n";
    }
}

