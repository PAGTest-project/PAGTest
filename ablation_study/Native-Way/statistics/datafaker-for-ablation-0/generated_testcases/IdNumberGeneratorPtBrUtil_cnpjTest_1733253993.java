
package net.datafaker.idnumbers.pt.br;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IdNumberGeneratorPtBrUtil_cnpjTest {

    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
    }

    @Test
    public void testCnpjValidFormattedMultiBranch() {
        when(faker.random().nextInt(9)).thenReturn(1, 2, 3, 4, 5, 6, 7, 8);
        when(faker.random().nextInt(1, 9999)).thenReturn(1234);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, true, true, true);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
    }

    @Test
    public void testCnpjValidUnformattedMultiBranch() {
        when(faker.random().nextInt(9)).thenReturn(1, 2, 3, 4, 5, 6, 7, 8);
        when(faker.random().nextInt(1, 9999)).thenReturn(1234);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, false, true, true);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
    }

    @Test
    public void testCnpjValidFormattedSingleBranch() {
        when(faker.random().nextInt(9)).thenReturn(1, 2, 3, 4, 5, 6, 7, 8);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, true, true, false);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
    }

    @Test
    public void testCnpjValidUnformattedSingleBranch() {
        when(faker.random().nextInt(9)).thenReturn(1, 2, 3, 4, 5, 6, 7, 8);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, false, true, false);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
    }

    @Test
    public void testCnpjInvalidFormatted() {
        when(faker.random().nextInt(1000000000)).thenReturn(123456789);
        when(faker.random().nextInt(90)).thenReturn(50);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, true, false, false);
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
    }

    @Test
    public void testCnpjInvalidUnformatted() {
        when(faker.random().nextInt(1000000000)).thenReturn(123456789);
        when(faker.random().nextInt(90)).thenReturn(50);
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, false, false, false);
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(result));
    }
}
