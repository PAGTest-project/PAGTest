
package net.datafaker.idnumbers.pt.br;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;

public class IdNumberGeneratorPtBrUtil_cnpjTest {

    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
        net.datafaker.service.RandomService randomService = new net.datafaker.service.RandomService();
        when(faker.random()).thenReturn(randomService);
    }

    @Test
    public void testCnpjValidFormattedMultiBranch() {
        net.datafaker.service.RandomService randomService = faker.random();
        doReturn(1, 2, 3, 4, 5, 6, 7, 8).when(randomService).nextInt(9);
        doReturn(1234).when(randomService).nextInt(1, 9999);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, true, true, true);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
        assertEquals("12.345.678/0001-95", result);
    }

    @Test
    public void testCnpjValidUnformattedMultiBranch() {
        net.datafaker.service.RandomService randomService = faker.random();
        doReturn(1, 2, 3, 4, 5, 6, 7, 8).when(randomService).nextInt(9);
        doReturn(1234).when(randomService).nextInt(1, 9999);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, false, true, true);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
        assertEquals("12345678000195", result);
    }

    @Test
    public void testCnpjValidFormattedSingleBranch() {
        net.datafaker.service.RandomService randomService = faker.random();
        doReturn(1, 2, 3, 4, 5, 6, 7, 8).when(randomService).nextInt(9);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, true, true, false);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
        assertEquals("12.345.678/0001-95", result);
    }

    @Test
    public void testCnpjValidUnformattedSingleBranch() {
        net.datafaker.service.RandomService randomService = faker.random();
        doReturn(1, 2, 3, 4, 5, 6, 7, 8).when(randomService).nextInt(9);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, false, true, false);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
        assertEquals("12345678000195", result);
    }

    @Test
    public void testCnpjInvalidFormatted() {
        net.datafaker.service.RandomService randomService = faker.random();
        doReturn(123456789).when(randomService).nextInt(1000000000);
        doReturn(50).when(randomService).nextInt(90);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, true, false, false);
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
    }

    @Test
    public void testCnpjInvalidUnformatted() {
        net.datafaker.service.RandomService randomService = faker.random();
        doReturn(123456789).when(randomService).nextInt(1000000000);
        doReturn(50).when(randomService).nextInt(90);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, false, false, false);
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
    }
}
