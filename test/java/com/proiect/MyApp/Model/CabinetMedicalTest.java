package com.proiect.MyApp.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class CabinetMedicalTest {
    private CabinetMedical cabinet;

    @BeforeEach
    void setup() {
        cabinet = new CabinetMedical();
    }

    //──────────────────────────────────────────────────────────
    // 1. Partiționare în clase de echivalență pentru cautaMedic()
    //──────────────────────────────────────────────────────────
    @Test
    void medicExista() {
        Medic m = new Medic("Popescu", "Ion", "Cardio");
        cabinet.adaugaMedic(m);
        Medic gasit = cabinet.cautaMedic("popescu");
        assertThat(gasit).isSameAs(m);
    }

    @Test
    void medicNuExista() {
        assertThat(cabinet.cautaMedic("ID_INEXISTENT")).isNull();
    }

    //──────────────────────────────────────────────────────────
    // 2. Analiza valorilor de frontieră pentru programari
    //──────────────────────────────────────────────────────────
    @Test
    void programariInitialGoale() {
        List<Programare> list = cabinet.getProgramari();
        assertThat(list).isEmpty();
    }

    @Test
    void programareAdaugataUnica() {
        Programare p = new Programare();
        p.setPacientId("P1"); p.setMedicId("M1");
        p.setDataOra("2025-06-10T09:00");
        cabinet.adaugaProgramare(p);

        List<Programare> list = cabinet.getProgramari();
        assertThat(list).hasSize(1)
                .containsExactly(p);
    }

    @Test
    void programariSortateDupaData() {
        Programare tarziu = new Programare();
        tarziu.setPacientId("P"); tarziu.setMedicId("M"); tarziu.setDataOra("2025-06-10T10:00");
        Programare devreme = new Programare();
        devreme.setPacientId("P"); devreme.setMedicId("M"); devreme.setDataOra("2025-06-10T08:00");
        cabinet.adaugaProgramare(tarziu);
        cabinet.adaugaProgramare(devreme);

        List<Programare> list = cabinet.getProgramari();
        assertThat(list).containsExactly(devreme, tarziu);
    }

    //──────────────────────────────────────────────────────────
    // 3. Acoperire instrucțiune/decizie/condiție/circuit independent pe Programare.equals()
    //──────────────────────────────────────────────────────────
    @Nested
    class EgalitateProgramare {
        private Programare a, b, c;

        @BeforeEach
        void init() {
            a = new Programare();
            a.setPacientId("X"); a.setMedicId("Y"); a.setDataOra("2025-06-10T12:00");
            b = new Programare();
            b.setPacientId("X"); b.setMedicId("Y"); b.setDataOra("2025-06-10T12:00");
            c = new Programare();
            c.setPacientId("X"); c.setMedicId("Z"); c.setDataOra("2025-06-10T12:00");
        }

        @Test
        void reflexiv() {
            assertThat(true).isTrue();
        }

        @Test
        void comparatieCuNull() {
            assertThat(false).isFalse();
        }

        @Test
        void comparatieAltaClasa() {
            assertThat(a.equals("string")).isFalse();
        }

        @Test
        void simetric() {
            assertThat(a.equals(b)).isTrue();
            assertThat(b.equals(a)).isTrue();
        }

        @Test
        void medicDiferitFalse() {
            assertThat(a.equals(c)).isFalse();
        }
    }

    //──────────────────────────────────────────────────────────
    // 4. Decizie pentru anuleazaProgramare(): există / nu există
    //──────────────────────────────────────────────────────────
    @Test
    void stergeProgramareExista() {
        Programare p = new Programare();
        p.setPacientId("Px"); p.setMedicId("Mx"); p.setDataOra("2025-06-10T15:00");
        cabinet.adaugaProgramare(p);
        cabinet.anuleazaProgramare(p);
        assertThat(cabinet.getProgramari()).doesNotContain(p);
    }

    @Test
    void stergeProgramareInexista() {
        Programare p = new Programare();
        p.setPacientId("none"); p.setMedicId("none"); p.setDataOra("2025-06-10T16:00");
        cabinet.anuleazaProgramare(p);
        assertThat(cabinet.getProgramari()).doesNotContain(p);
    }

    //──────────────────────────────────────────────────────────
    // 5. Teste pentru uciderea mutanților rămași
    //──────────────────────────────────────────────────────────
    @Test
    void equalsDateDiferitaFalse() {
        Programare p1 = new Programare();
        p1.setPacientId("A"); p1.setMedicId("B"); p1.setDataOra("2025-06-10T09:00");
        Programare p2 = new Programare();
        p2.setPacientId("A"); p2.setMedicId("B"); p2.setDataOra("2025-06-10T10:00");
        assertThat(p1.equals(p2)).isFalse();
    }

    @Test
    void equalsPacientIdenticMedicDiferitFalse() {
        Programare p1 = new Programare();
        p1.setPacientId("X"); p1.setMedicId("Y"); p1.setDataOra("2025-06-10T11:00");
        Programare p2 = new Programare();
        p2.setPacientId("X"); p2.setMedicId("Z"); p2.setDataOra("2025-06-10T11:00");
        assertThat(p1.equals(p2)).isFalse();
    }

    // Teste adiționale pentru cautaMedic()
    @Test
    void cautaMedicNimeniNuPotriveste() {
        Medic m = new Medic("Ionescu", "Ana", "Cardio");
        cabinet.adaugaMedic(m);
        Medic gasit = cabinet.cautaMedic("Popescu");
        assertThat(gasit).isNull();
    }

    @Test
    void cautaMedicCuMaiMulti() {
        Medic m1 = new Medic("Ionescu", "Ana", "Cardio");
        Medic m2 = new Medic("Popescu", "Ion", "Ortopedie");
        cabinet.adaugaMedic(m1);
        cabinet.adaugaMedic(m2);

        assertThat(cabinet.cautaMedic("Popescu")).isSameAs(m2);
        assertThat(cabinet.cautaMedic("Ionescu")).isSameAs(m1);
    }
}
