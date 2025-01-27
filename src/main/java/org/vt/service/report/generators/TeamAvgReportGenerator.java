package org.vt.service.report.generators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vt.domain.TeamAvgReportData;
import org.vt.dto.ReportType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.vt.utils.Constants.COMA_SPACE_DELIMITER;

public class TeamAvgReportGenerator implements ReportGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamAvgReportGenerator.class);

    private static final List<String> HEADERS = List.of("Team", "Total Effort", "Remaining Effort");

    @Override
    public ReportType reportType() {
        return ReportType.TEAM_AVG_ESTIMATE;
    }

    @Override
    public String delimiter() {
        return COMA_SPACE_DELIMITER;
    }

    @Override
    public List<String> headers() {
        return HEADERS;
    }

    @Override
    public List<List<String>> values(Stream<String> inputData) {
        Map<TeamAvgReportData, TeamAvgReportData> collect = inputData.skip(1)
                .map(l -> l.split(","))
                .map(l -> TeamAvgReportData.of(l[l.length - 1], Double.parseDouble(l[l.length - 3])))
                .collect((Collectors.groupingBy(Function.identity(),
                        Collector.of(TeamAvgReportData::new, TeamAvgReportData::merge, (r1, r2) -> {
                            throw new UnsupportedOperationException("Parallel not supported!");
                        }))));
        LOGGER.info("Report: {}, values: {}", reportType(), collect);
        return collect.values()
                .stream()
                .map(reportDto -> List.of(reportDto.getTeam(), Long.toString(reportDto.getTotalEffort()),
                        Double.toString(reportDto.getAvgEffort())))
                .toList();
    }
}
