
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IdNumberGeneratorPtBrUtil_isCNPJValidTest {

    @Test
    void testIsCNPJValid_ValidCNPJ() {
        // Given
        String validCNPJ = "12345678000195";

        // When
        boolean result = IdNumberGeneratorPtBrUtil.isCNPJValid(validCNPJ);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsCNPJValid_InvalidCNPJ() {
        // Given
        String invalidCNPJ = "12345678000190";

        // When
        boolean result = IdNumberGeneratorPtBrUtil.isCNPJValid(invalidCNPJ);

        // Then
        assertFalse(result);
    }
}
