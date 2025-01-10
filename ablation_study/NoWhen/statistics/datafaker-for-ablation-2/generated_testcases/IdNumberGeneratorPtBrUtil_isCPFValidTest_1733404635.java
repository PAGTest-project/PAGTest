
package net.datafaker.idnumbers.pt.br;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdNumberGeneratorPtBrUtil_isCPFValidTest {

    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders() {
            @Override
            public String expression(String expression) {
                return null;
            }
        };
    }

    @Test
    public void testIsCPFValid_ValidCPF() {
        String validCPF = IdNumberGeneratorPtBrUtil.cpf(faker, false, true);
        assertTrue(IdNumberGeneratorPtBrUtil.isCPFValid(validCPF));
    }

    @Test
    public void testIsCPFValid_InvalidCPF() {
        String invalidCPF = IdNumberGeneratorPtBrUtil.cpf(faker, false, false);
        assertFalse(IdNumberGeneratorPtBrUtil.isCPFValid(invalidCPF));
    }

    @Test
    public void testIsCPFValid_FormattedCPF() {
        String formattedCPF = IdNumberGeneratorPtBrUtil.cpf(faker, true, true);
        assertTrue(IdNumberGeneratorPtBrUtil.isCPFValid(formattedCPF));
    }

    @Test
    public void testIsCPFValid_InvalidFormattedCPF() {
        String invalidFormattedCPF = IdNumberGeneratorPtBrUtil.cpf(faker, true, false);
        assertFalse(IdNumberGeneratorPtBrUtil.isCPFValid(invalidFormattedCPF));
    }
}
