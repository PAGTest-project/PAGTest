
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdNumberGeneratorPtBrUtil_isCPFValidTest {

    @Test
    void testIsCPFValid_ValidCPF() {
        String validCPF = "52998224725"; // A valid CPF number
        assertTrue(IdNumberGeneratorPtBrUtil.isCPFValid(validCPF));
    }

    @Test
    void testIsCPFValid_InvalidCPF() {
        String invalidCPF = "12345678901"; // An invalid CPF number
        assertFalse(IdNumberGeneratorPtBrUtil.isCPFValid(invalidCPF));
    }

    @Test
    void testIsCPFValid_FormattedCPF() {
        String formattedCPF = "529.982.247-25"; // A valid formatted CPF number
        assertTrue(IdNumberGeneratorPtBrUtil.isCPFValid(formattedCPF));
    }

    @Test
    void testIsCPFValid_InvalidFormattedCPF() {
        String invalidFormattedCPF = "123.456.789-01"; // An invalid formatted CPF number
        assertFalse(IdNumberGeneratorPtBrUtil.isCPFValid(invalidFormattedCPF));
    }
}
