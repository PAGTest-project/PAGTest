
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdNumberGeneratorPtBrUtil_isCNPJValidTest {

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testIsCNPJValid_ValidCNPJ() {
        String validCNPJ = "12345678000195";
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(validCNPJ));
    }

    @Test
    public void testIsCNPJValid_InvalidCNPJ() {
        String invalidCNPJ = "12345678000196";
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(invalidCNPJ));
    }

    @Test
    public void testIsCNPJValid_ShortCNPJ() {
        String shortCNPJ = "123456780001";
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(shortCNPJ));
    }

    @Test
    public void testIsCNPJValid_LongCNPJ() {
        String longCNPJ = "1234567800019500";
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(longCNPJ));
    }

    @Test
    public void testIsCNPJValid_AllZerosCNPJ() {
        String allZerosCNPJ = "00000000000000";
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(allZerosCNPJ));
    }
}
