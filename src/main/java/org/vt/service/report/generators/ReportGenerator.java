package org.vt.service.report.generators;

import org.vt.dto.ReportType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.vt.utils.Constants.EMPTY_STRING;
import static org.vt.utils.Constants.NEW_LINE;

public interface ReportGenerator {

    ReportType reportType();

    String delimiter();

    List<String> headers();

    List<List<String>> values(Stream<String> inputData);

    default String buildReport(final Stream<String> inputData) {
        List<String> headers = headers();
        List<List<String>> values = values(inputData);
        String delimiter = delimiter();

        return Stream.concat(Stream.of(headers), values.stream())
                .map(list -> list.stream()
                        .collect(Collectors.joining(delimiter, EMPTY_STRING, NEW_LINE)))
                .collect(Collectors.joining());
    }
}
