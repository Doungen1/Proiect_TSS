package com.proiect.MyApp.Model;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsoleUITest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @Mock
    private CabinetMedical cabinetMock;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void restore() throws Exception {
        System.setIn(originalIn);
        System.setOut(originalOut);
        closeable.close();
    }

    private void runUIWithInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ConsoleUI consoleUI = new ConsoleUI(cabinetMock);
        consoleUI.start();
    }

    @Test
    void testAdaugaMedicFlow() throws SQLException {
        runUIWithInput("1\nPopescu\nIon\nCardio\n0\n");

        verify(cabinetMock).adaugaMedic("Popescu", "Ion", "Cardio");
        assertThat(outContent.toString(StandardCharsets.UTF_8))
                .contains("Introduceți numele medicului:")
                .contains("Introduceți prenumele medicului:")
                .contains("Introduceți specializarea medicului:");
    }

    @Test
    void testAdaugaPacientFlow() throws SQLException {
        runUIWithInput("2\nIonescu\nAna\n1234567890123\nIstoric\n0\n");

        verify(cabinetMock).adaugaPacient("Ionescu", "Ana", "1234567890123", "Istoric");
        assertThat(outContent.toString(StandardCharsets.UTF_8))
                .contains("Introduceți CNP-ul pacientului:");

    }

    @Test
    void testAdaugaProgramareFlow() throws SQLException {
        runUIWithInput("3\nPac1\nMed1\n2025-06-15T14:00\n0\n");

        verify(cabinetMock).adaugaProgramare("Pac1", "Med1", "2025-06-15T14:00");
        assertThat(outContent.toString(StandardCharsets.UTF_8))
                .contains("Introduceți data și ora programării");
    }

    @Test
    void testAnuleazaProgramareFlow() throws SQLException {
        runUIWithInput("4\nProgID123\n0\n");

        verify(cabinetMock).anuleazaProgramare("ProgID123");
        assertThat(outContent.toString(StandardCharsets.UTF_8))
                .contains("Introduceți ID-ul programării pentru anulare:");
    }

    @Test
    void testEmiteRetetaFlow() throws SQLException {
        runUIWithInput("5\nP1\nM1\nDiag\nTrat\n0\n");

        verify(cabinetMock).emiteReteta("P1", "M1", "Diag", "Trat");
        assertThat(outContent.toString(StandardCharsets.UTF_8))
                .contains("Introduceți diagnosticul:");
    }

    @Test
    void optiuneInvalidaEroare() throws SQLException {
        RuntimeException exception = new RuntimeException("fail-medic");
        doThrow(exception).when(cabinetMock).adaugaMedic(anyString(), anyString(), anyString());

        runUIWithInput("9\n1\nX\nY\nZ\n0\n");

        String out = outContent.toString(StandardCharsets.UTF_8);
        assertThat(out).contains("Opțiune invalidă");
        assertThat(out).contains("Eroare: fail-medic");
    }

    @Test
    void optiuneNonNumerica() {
        runUIWithInput("abc\n0\n");
        String out = outContent.toString(StandardCharsets.UTF_8);
        assertThat(out).contains("Opțiune invalidă.");
    }

    @Test
    void testAfisareMeniuPrincipal() {
        runUIWithInput("0\n");

        String output = outContent.toString(StandardCharsets.UTF_8);
        assertThat(output).contains("Meniu Principal:");
        assertThat(output).contains("1. Adaugă medic");
        assertThat(output).contains("2. Adaugă pacient");
        assertThat(output).contains("3. Creează o programare");
        assertThat(output).contains("4. Anulează o programare");
        assertThat(output).contains("5. Emite o rețetă");
        assertThat(output).contains("0. Ieșire");
        assertThat(output).contains("Alege o opțiune:");
    }

    @Test
    void testToatePrompturileSuntAfisate() {
        String input = String.join("\n",
                "1", "Popescu", "Ion", "Cardio",
                "2", "Ionescu", "Ana", "1234567890123", "Istoric OK",
                "3", "Pac1", "Med1", "2025-06-15T14:00",
                "4", "ProgID123",
                "5", "P1", "M1", "Diagnostic", "Tratament",
                "0"
        ) + "\n";

        runUIWithInput(input);

        String output = outContent.toString(StandardCharsets.UTF_8);

        // Testare suplimentara pentru a elimina mutantii ramasi la ConsoleUI

        assertThat(output).contains("Introduceți numele medicului:");
        assertThat(output).contains("Introduceți prenumele medicului:");
        assertThat(output).contains("Introduceți specializarea medicului:");
        assertThat(output).contains("Introduceți numele pacientului:");
        assertThat(output).contains("Introduceți prenumele pacientului:");
        assertThat(output).contains("Introduceți CNP-ul pacientului:");
        assertThat(output).contains("Introduceți istoricul medical al pacientului");
        assertThat(output).contains("Introduceți numele pacientului pentru programare:");
        assertThat(output).contains("Introduceți numele medicului pentru programare:");
        assertThat(output).contains("Introduceți data și ora programării");
        assertThat(output).contains("Introduceți ID-ul programării pentru anulare:");
        assertThat(output).contains("Introduceți ID-ul pacientului pentru care se emite rețeta:");
        assertThat(output).contains("Introduceți ID-ul medicului care emite rețeta:");
        assertThat(output).contains("Introduceți diagnosticul:");
        assertThat(output).contains("Introduceți tratamentul:");
    }
}