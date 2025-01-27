package org.vt.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vt.domain.InputData;

import java.util.List;
import java.util.stream.Collectors;

import static org.vt.utils.DataUtils.FILE_DTO;

class CSVUtilsTest {

    @Test
    void mapToObject() {
        List<InputData> inputData = CSVUtils.mapToObject(FILE_DTO.getFullPath(), InputData.class);

        Assertions.assertNotNull(inputData);
        Assertions.assertEquals(18, inputData.size());

        Assertions.assertLinesMatch(inputData.stream()
                .map(InputData::getTeam)
                .distinct()
                .collect(Collectors.toList()), List.of("London", "UI", "India"));
    }
}