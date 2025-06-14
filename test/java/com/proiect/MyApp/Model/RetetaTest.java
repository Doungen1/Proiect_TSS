package com.proiect.MyApp.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RetetaTest {
    private Reteta r;

    @BeforeEach
    void setup() {
        r = new Reteta();
    }

    // [Partiționare: constructor implicit]
    @Test
    void campuriImpliciteNull() {
        assertNull(r.getPacientId(), "pacientId ar trebui să fie null");
        assertNull(r.getMedicId(),   "medicId ar trebui să fie null");
        assertNull(r.getDiagnostic(),"diagnostic ar trebui să fie null");
        assertNull(r.getTratament(), "tratament ar trebui să fie null");
    }

    // [Partiționare: getter/setter non-empty]
    @Test
    void pacientIdSetatCorect() {
        r.setPacientId("P123");
        assertEquals("P123", r.getPacientId());
    }

    @Test
    void medicIdSetatCorect() {
        r.setMedicId("M456");
        assertEquals("M456", r.getMedicId());
    }

    @Test
    void diagnosticSetatCorect() {
        r.setDiagnostic("Diag");
        assertEquals("Diag", r.getDiagnostic());
    }

    @Test
    void tratamentSetatCorect() {
        r.setTratament("Trat");
        assertEquals("Trat", r.getTratament());
    }

    // [Frontiere și null: șir gol]
    @Test
    void diagnosticGolEsteAcceptat() {
        r.setDiagnostic("");
        assertEquals("", r.getDiagnostic());
    }

    // [Frontiere și null: null explicit]
    @Test
    void tratamentNullRamaneNull() {
        r.setTratament(null);
        assertNull(r.getTratament());
    }
    @Test
        void equalsCuNull() {
    // r este inițializat în @BeforeEach
    assertThat(r.equals(null)).isFalse();
        }
        @Test
    void equalsAceeasiReferinta() {
    // r este inițializat în @BeforeEach
    assertThat(r.equals(r)).isTrue();
    }
}
