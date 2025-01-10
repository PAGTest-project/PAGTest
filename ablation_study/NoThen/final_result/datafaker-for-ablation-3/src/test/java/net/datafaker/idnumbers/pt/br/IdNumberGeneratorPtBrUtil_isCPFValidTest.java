
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IdNumberGeneratorPtBrUtil_isCPFValidTest {

    @Test
    void testIsCPFValid_ValidCPF() {
        // Given
        String cpf = "12345678909";

        // When
        Boolean result = IdNumberGeneratorPtBrUtil.isCPFValid(cpf);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsCPFValid_InvalidCPF() {
        // Given
        String cpf = "12345678900";

        // When
        Boolean result = IdNumberGeneratorPtBrUtil.isCPFValid(cpf);

        // Then
        assertFalse(result);
    }
}
