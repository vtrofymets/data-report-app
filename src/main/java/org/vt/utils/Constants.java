package org.vt.utils;

import java.util.stream.Stream;

public interface Constants {

    String EMPTY_STRING = "";
    String COMA_SPACE_DELIMITER = ", ";
    String REPORT_TITLE = "Team, Total Effort, Remaining Effort";
    Stream<String> REPORT_HEADERS = Stream.of("Team", "Total Effort", "Remaining Effort");

}
