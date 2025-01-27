package org.vt.facade;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vt.service.GenerateReportProcess;
import org.vt.service.report.generators.TeamAvgReportGenerator;
import org.vt.service.storage.clients.LocalStorageClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.vt.utils.DataUtils.EXPECTED_REPORT_CONTENT;
import static org.vt.utils.DataUtils.FILE_DTO;

class GenerateReportProcessTest {

    private GenerateReportProcess sut;

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
        sut = new GenerateReportProcess(List.of(new LocalStorageClient()), List.of(new TeamAvgReportGenerator()));
    }

    @Test
    void stdoutOutPutProcess() {
        sut.startProcessReport(FILE_DTO);

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