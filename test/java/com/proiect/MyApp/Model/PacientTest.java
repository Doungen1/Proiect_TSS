package com.proiect.MyApp.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PacientTest {
    private Pacient pacient;

    @BeforeEach
    void setUp() {
        pacient = new Pacient();
    }

    // 1. Partiționare în clase de echivalență pentru setter CNP
    @Nested
        class CnpEquivalenceClasses {
            @Test
            void whenValidCnpLength13_thenSetAndGet() {
                String validCnp = "1234567890123";
                pacient.setCnp(validCnp);
                assertThat(pacient.getCnp()).isEqualTo(validCnp);
            }

            @Test
            void whenEmptyCnp_thenThrowsException() {
                assertThrows(IllegalArgumentException.class, () -> pacient.setCnp(""));
            }

            @Test
            void whenNullCnp_thenThrowsException() {
                assertThrows(IllegalArgumentException.class, () -> pacient.setCnp(null));
            }
        }

        // 2. Analiza valorilor de frontieră pentru CNP (lungime 12, 13, 14)
        @Nested
        class CnpBoundaryAnalysis {
            @Test
            void cnpLength12_boundaryLow_throwsException() {
                String cnp12 = "123456789012";
                assertThrows(IllegalArgumentException.class, () -> pacient.setCnp(cnp12));
            }

            @Test
            void cnpLength13_optimal_isAccepted() {
                String cnp13 = "1234567890123";
                pacient.setCnp(cnp13);
                assertThat(pacient.getCnp()).isEqualTo(cnp13);
            }

            @Test
            void cnpLength14_boundaryHigh_throwsException() {
                String cnp14 = "12345678901234";
                assertThrows(IllegalArgumentException.class, () -> pacient.setCnp(cnp14));
            }
        }

    // 3. Acoperire instrucțiune/decizie/condiție pentru restul getter/setter
    @Test
    void gettersAndSetters_workCorrectly() {
        pacient.setNume("Popescu");
        pacient.setPrenume("Ion");
        pacient.setIstoricMedical("Istoric");
        assertThat(pacient.getNume()).isEqualTo("Popescu");
        assertThat(pacient.getPrenume()).isEqualTo("Ion");
        assertThat(pacient.getIstoricMedical()).isEqualTo("Istoric");
    }

    // 4. Circuit independent pentru constructorul implicit
    @Test
    void defaultConstructor_initializesEmptyStrings() {
        assertThat(pacient.getNume()).isEmpty();
        assertThat(pacient.getPrenume()).isEmpty();
        assertThat(pacient.getCnp()).isEmpty();
        assertThat(pacient.getIstoricMedical()).isEmpty();
    }

    // 5. Teste suplimentare pentru uciderea mutanților rămași
    
}
