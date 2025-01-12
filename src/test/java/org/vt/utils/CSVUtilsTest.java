package org.vt.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vt.model.InputData;

import java.util.List;
import java.util.stream.Collectors;

class CSVUtilsTest {

    @Test
    void mapToObject() {
        List<InputData> inputData = CSVUtils.mapToObject(DataUtils.FILE_TEST_DATA_PATH, InputData.class);

        Assertions.assertNotNull(inputData);
        Assertions.assertEquals(18, inputData.size());

        Assertions.assertLinesMatch(inputData.stream()
                .map(InputData::getTeam)
                .distinct()
                .collect(Collectors.toList()), List.of("London", "UI", "India"));
    }
}