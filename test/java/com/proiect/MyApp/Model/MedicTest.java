package com.proiect.MyApp.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class MedicTest {
    private Medic medic;

    @BeforeEach
    void setup() {
        medic = new Medic();
    }

    // [Partiționare: constructor parametrizat]
    @Test
    void constructorParametrizat() {
        Medic m = new Medic("Ionescu", "Ana", "Cardio");
        assertThat(m.getNume()).isEqualTo("Ionescu");
        assertThat(m.getPrenume()).isEqualTo("Ana");
        assertThat(m.getSpecializare()).isEqualTo("Cardio");
    }

    // [Frontiere: id implicit]
    @Test
    void idImplicitZero() {
        assertThat(medic.getId()).isZero();
    }

    // [Frontiere: setId valori diverse]
    @Test
    void seteazaIdValori() {
        medic.setId(0);
        assertThat(medic.getId()).isZero();
        medic.setId(123);
        assertThat(medic.getId()).isEqualTo(123);
        medic.setId(-5);
        assertThat(medic.getId()).isEqualTo(-5);
    }

    // [Acoperire: getter și setter]
    @Test
    void gettersSetters() {
        medic.setNume("Popescu");
        medic.setPrenume("Ion");
        medic.setSpecializare("Pediatrie");
        assertThat(medic.getNume()).isEqualTo("Popescu");
        assertThat(medic.getPrenume()).isEqualTo("Ion");
        assertThat(medic.getSpecializare()).isEqualTo("Pediatrie");
    }

    // [Circuit independent: toString()]
    @Test
    void stringConțineToateCâmpurile() {
        medic.setId(42);
        medic.setNume("X");
        medic.setPrenume("Y");
        medic.setSpecializare("Z");
        String s = medic.toString();
        assertThat(s)
                .contains("id=42")
                .contains("nume='X'")
                .contains("prenume='Y'")
                .contains("specializare='Z'");
    }

    // [Mutanți: nume null și gol]
    @Test
    void numeGolȘiNull() {
        medic.setNume("");
        assertThat(medic.getNume()).isEmpty();
        medic.setNume(null);
        assertThat(medic.getNume()).isNull();
    }

    // [Mutanți: prenume null și gol]
    @Test
    void prenumeGolȘiNull() {
        medic.setPrenume("");
        assertThat(medic.getPrenume()).isEmpty();
        medic.setPrenume(null);
        assertThat(medic.getPrenume()).isNull();
    }
    @Test
        void equalsCuNull() {
        assertThat(medic.equals(null)).isFalse();
        }
    @Test
        void equalsAceeasiReferinta() {
                assertThat(medic.equals(medic)).isTrue();
            }
}
