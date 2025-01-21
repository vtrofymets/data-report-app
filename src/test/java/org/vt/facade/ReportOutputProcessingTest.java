package org.vt.facade;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vt.service.CSVReportLocalGenerationImpl;
import org.vt.service.StdoutReportUploadImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.vt.utils.DataUtils.EXPECTED_REPORT_CONTENT;
import static org.vt.utils.DataUtils.FILE_DTO;

class ReportOutputProcessingTest {

    private ReportOutputProcessing sut;

    private static final PrintStream MAIN_OUT = System.out;

    private static final ByteArrayOutputStream testConsole = new ByteArrayOutputStream();
    private static final PrintStream tesPrintStream = new PrintStream(testConsole);

    @BeforeAll
    static void beforeAll() {
        System.setOut(tesPrintStream);

        System.out.println("HELLO WORLD!");

        String testOutString = testConsole.toString();
        Assertions.assertNotNull(testOutString);
        Assertions.assertTrue(testOutString.contains("HELLO WORLD!"));
    }

    @BeforeEach
    void beforeEach() {
        sut = new ReportOutputProcessing(List.of(new CSVReportLocalGenerationImpl()), new StdoutReportUploadImpl());
    }

    @Test
    void stdoutOutPutProcess() {
        sut.reportOutput(FILE_DTO);

        String testOutConsole = testConsole.toString();

        Assertions.assertTrue(testOutConsole.contains(EXPECTED_REPORT_CONTENT));
    }

    @AfterAll
    static void afterAll() throws IOException {
        testConsole.close();
        tesPrintStream.close();

        System.setOut(MAIN_OUT);
    }

}