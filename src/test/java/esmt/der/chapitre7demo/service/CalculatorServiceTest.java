package esmt.der.chapitre7demo.service;

import org.junit.jupiter.api.Test;
// Import pour JUnit standard
import static org.junit.jupiter.api.Assertions.assertEquals;
// Import pour AssertJ
import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void testAdditionStandard() {
        // Approche classique du cours
        assertEquals(5, calculatorService.add(2, 3));
    }

    @Test
    void testAdditionFluent() {
        // Approche AssertJ (plus lisible)
        assertThat(calculatorService.add(2, 3)).isEqualTo(5);
    }
}
