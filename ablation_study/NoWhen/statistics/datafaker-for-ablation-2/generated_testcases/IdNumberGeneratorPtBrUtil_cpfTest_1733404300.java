
package net.datafaker.idnumbers.pt.br;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static net.datafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil.isCPFValid;

public class IdNumberGeneratorPtBrUtil_cpfTest {

    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders() {
            @Override
            public Object random() {
                return null; // Mock implementation
            }
        };
    }

    @Test
    public void testCpfValidFormatted() {
        String cpf = IdNumberGeneratorPtBrUtil.cpf(faker, true, true);
        assertTrue(isCPFValid(cpf));
        // Assuming DocumentFormatterUtil.cpf adds formatting like "###.###.###-##"
        assertTrue(cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"));
    }

    @Test
    public void testCpfValidUnformatted() {
        String cpf = IdNumberGeneratorPtBrUtil.cpf(faker, false, true);
        assertTrue(isCPFValid(cpf));
        // Assuming unformatted CPF is just a 11-digit number
        assertTrue(cpf.matches("\\d{11}"));
    }

    @Test
    public void testCpfInvalidFormatted() {
        String cpf = IdNumberGeneratorPtBrUtil.cpf(faker, true, false);
        assertFalse(isCPFValid(cpf));
        // Assuming DocumentFormatterUtil.cpf adds formatting like "###.###.###-##"
        assertTrue(cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"));
    }

    @Test
    public void testCpfInvalidUnformatted() {
        String cpf = IdNumberGeneratorPtBrUtil.cpf(faker, false, false);
        assertFalse(isCPFValid(cpf));
        // Assuming unformatted CPF is just a 11-digit number
        assertTrue(cpf.matches("\\d{11}"));
    }
}
