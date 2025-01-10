
package net.datafaker.idnumbers.pt.br;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static net.datafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil.cpf;
import static net.datafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil.isCPFValid;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IdNumberGeneratorPtBrUtil_cpfTest {

    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = Mockito.mock(BaseProviders.class);
        Mockito.when(faker.random().nextInt(9)).thenReturn(1); // Mocking random number generation
    }

    @Test
    public void testCpfValidFormatted() {
        String result = cpf(faker, true, true);
        assertTrue(isCPFValid(result));
    }

    @Test
    public void testCpfValidUnformatted() {
        String result = cpf(faker, false, true);
        assertTrue(isCPFValid(result));
    }

    @Test
    public void testCpfInvalidFormatted() {
        String result = cpf(faker, true, false);
        assertFalse(isCPFValid(result));
    }

    @Test
    public void testCpfInvalidUnformatted() {
        String result = cpf(faker, false, false);
        assertFalse(isCPFValid(result));
    }
}
