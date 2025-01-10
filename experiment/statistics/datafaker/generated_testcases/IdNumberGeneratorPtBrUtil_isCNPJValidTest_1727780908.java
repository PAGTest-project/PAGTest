
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdNumberGeneratorPtBrUtil_isCNPJValidTest {

    @Test
    void testIsCNPJValid_ValidCNPJ() {
        String validCNPJ = "12345678000195"; // Example of a valid CNPJ
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(validCNPJ));
    }

    @Test
    void testIsCNPJValid_InvalidCNPJ() {
        String invalidCNPJ = "12345678000196"; // Example of an invalid CNPJ
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(invalidCNPJ));
    }

    @Test
    void testIsCNPJValid_InvalidFormat() {
        String invalidFormatCNPJ = "123456780001"; // CNPJ with incorrect length
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(invalidFormatCNPJ));
    }
}
