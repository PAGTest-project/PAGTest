
package net.datafaker.idnumbers.pt.br;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static net.datafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil.*;

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
        String result = cpf(faker, true, true);
        assertTrue(isCPFValid(result));
        assertEquals(14, result.length()); // Formatted CPF should be 14 characters long
    }

    @Test
    public void testCpfValidUnformatted() {
        String result = cpf(faker, false, true);
        assertTrue(isCPFValid(result));
        assertEquals(11, result.length()); // Unformatted CPF should be 11 characters long
    }

    @Test
    public void testCpfInvalidFormatted() {
        String result = cpf(faker, true, false);
        assertFalse(isCPFValid(result));
        assertEquals(14, result.length()); // Formatted CPF should be 14 characters long
    }

    @Test
    public void testCpfInvalidUnformatted() {
        String result = cpf(faker, false, false);
        assertFalse(isCPFValid(result));
        assertEquals(11, result.length()); // Unformatted CPF should be 11 characters long
    }
}
