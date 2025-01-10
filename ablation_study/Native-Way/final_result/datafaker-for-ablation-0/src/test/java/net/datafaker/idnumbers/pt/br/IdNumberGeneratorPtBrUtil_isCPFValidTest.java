
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdNumberGeneratorPtBrUtil_isCPFValidTest {

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testIsCPFValid_ValidCPF() {
        String validCPF = "52998224725"; // Example of a valid CPF
        assertTrue(IdNumberGeneratorPtBrUtil.isCPFValid(validCPF));
    }

    @Test
    public void testIsCPFValid_InvalidCPF() {
        String invalidCPF = "12345678901"; // Example of an invalid CPF
        assertFalse(IdNumberGeneratorPtBrUtil.isCPFValid(invalidCPF));
    }

    @Test
    public void testIsCPFValid_FormattedCPF() {
        String formattedCPF = "529.982.247-25"; // Example of a formatted valid CPF
        assertTrue(IdNumberGeneratorPtBrUtil.isCPFValid(formattedCPF));
    }

    @Test
    public void testIsCPFValid_InvalidFormattedCPF() {
        String invalidFormattedCPF = "123.456.789-01"; // Example of a formatted invalid CPF
        assertFalse(IdNumberGeneratorPtBrUtil.isCPFValid(invalidFormattedCPF));
    }

    @Test
    public void testIsCPFValid_ShortCPF() {
        String shortCPF = "123456789"; // Example of a short CPF
        assertFalse(IdNumberGeneratorPtBrUtil.isCPFValid(shortCPF));
    }

    @Test
    public void testIsCPFValid_LongCPF() {
        String longCPF = "123456789012"; // Example of a long CPF
        assertFalse(IdNumberGeneratorPtBrUtil.isCPFValid(longCPF));
    }
}
