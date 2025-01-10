
package net.datafaker.idnumbers.pt.br;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import net.datafaker.service.RandomService;

public class IdNumberGeneratorPtBrUtil_cpfTest {

    private BaseProviders faker;
    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
        randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
    }

    @Test
    public void testCpfValidFormatted() {
        when(randomService.nextInt(9)).thenReturn(1);
        String cpf = IdNumberGeneratorPtBrUtil.cpf(faker, true, true);
        assertTrue(IdNumberGeneratorPtBrUtil.isCPFValid(cpf));
    }

    @Test
    public void testCpfValidUnformatted() {
        when(randomService.nextInt(9)).thenReturn(1);
        String cpf = IdNumberGeneratorPtBrUtil.cpf(faker, false, true);
        assertTrue(IdNumberGeneratorPtBrUtil.isCPFValid(cpf));
    }

    @Test
    public void testCpfInvalidFormatted() {
        when(randomService.nextInt(1000000000)).thenReturn(123456789);
        when(randomService.nextInt(90)).thenReturn(10);
        String cpf = IdNumberGeneratorPtBrUtil.cpf(faker, true, false);
        assertFalse(IdNumberGeneratorPtBrUtil.isCPFValid(cpf));
    }

    @Test
    public void testCpfInvalidUnformatted() {
        when(randomService.nextInt(1000000000)).thenReturn(123456789);
        when(randomService.nextInt(90)).thenReturn(10);
        String cpf = IdNumberGeneratorPtBrUtil.cpf(faker, false, false);
        assertFalse(IdNumberGeneratorPtBrUtil.isCPFValid(cpf));
    }
}
