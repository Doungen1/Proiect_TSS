package com.proiect.MyApp.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ProgramareTest {
    private Programare p;

    @BeforeEach
    void setup() {
        p = new Programare();
    }

    // [Partiționare: getPacientId]
    @Test
    void pacientIdCorect() {
        p.setPacientId("A");
        assertThat(p.getPacientId()).isEqualTo("A");
    }

    // [Partiționare: getMedicId]
    @Test
    void medicIdCorect() {
        p.setMedicId("B");
        assertThat(p.getMedicId()).isEqualTo("B");
    }

    // [Analiza frontieră: hashCode non-zero după setări]
    @Test
    void hashCodeNonZero() {
        p.setPacientId("A");
        p.setMedicId("B");
        p.setDataOra("2025-06-12T17:00");
        assertThat(p.hashCode()).isNotEqualTo(0);
    }

    // [Circuit independent: toString() produce text nenul]
    @Test
    void toStringNenul() {
        assertThat(p.toString()).isNotEmpty();
    }

    // ────────────────────────────────────────────────────────────────────────
    // Teste pentru equals() – ramuri complete metoda equals (branch coverage)
    // ────────────────────────────────────────────────────────────────────────

    @Test
    void equalsAceeasiInstanta() {
        p.setPacientId("A");
        p.setMedicId("B");
        p.setDataOra("2025-06-12T17:00");
        assertThat(p.equals(p)).isTrue();
    }

    @Test
    void equalsCuNull() {
        p.setPacientId("A");
        p.setMedicId("B");
        p.setDataOra("2025-06-12T17:00");
        assertThat(p.equals(null)).isFalse();
    }

    @Test
    void equalsCuAltaClasa() {
        p.setPacientId("A");
        p.setMedicId("B");
        p.setDataOra("2025-06-12T17:00");
        assertThat(p.equals("altceva")).isFalse();
    }

    @Test
    void equalsPacientDiferit() {
        p.setPacientId("A");
        p.setMedicId("M");
        p.setDataOra("2025-01-01T10:00");

        Programare q = new Programare();
        q.setPacientId("B");
        q.setMedicId("M");
        q.setDataOra("2025-01-01T10:00");

        assertThat(p.equals(q)).isFalse();
    }
    

    @Test
    void equalsMedicDiferit() {
        p.setPacientId("A");
        p.setMedicId("M1");
        p.setDataOra("2025-01-01T10:00");

        Programare q = new Programare();
        q.setPacientId("A");
        q.setMedicId("M2");
        q.setDataOra("2025-01-01T10:00");

        assertThat(p.equals(q)).isFalse();
    }

    @Test
    void equalsDataDiferita() {
        p.setPacientId("A");
        p.setMedicId("B");
        p.setDataOra("2025-06-12T17:00");

        Programare q = new Programare();
        q.setPacientId("A");
        q.setMedicId("B");
        q.setDataOra("2025-06-13T17:00");

        assertThat(p.equals(q)).isFalse();
    }

    @Test
    void equalsToateEgale() {
        p.setPacientId("A");
        p.setMedicId("B");
        p.setDataOra("2025-06-12T17:00");

        Programare q = new Programare();
        q.setPacientId("A");
        q.setMedicId("B");
        q.setDataOra("2025-06-12T17:00");

        assertThat(p.equals(q)).isTrue();
    }

    @Test
    void hashCodeEgalCandEquals() {
        p.setPacientId("A");
        p.setMedicId("B");
        p.setDataOra("2025-06-12T17:00");

        Programare q = new Programare();
        q.setPacientId("A");
        q.setMedicId("B");
        q.setDataOra("2025-06-12T17:00");

        assertThat(p.hashCode()).isEqualTo(q.hashCode());
    }
    
}
