
package net.datafaker.idnumbers.pt.br;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static net.datafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil.isCNPJValid;

public class IdNumberGeneratorPtBrUtil_cnpjTest {

    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders() {
            @Override
            public Object address() {
                return null;
            }

            @Override
            public Object ancient() {
                return null;
            }

            // Implement other required methods similarly

            @Override
            public Object pronouns() {
                return null;
            }
        };
    }

    @Test
    public void testCnpjValidFormattedMultiBranch() {
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, true, true, true);
        assertTrue(isCNPJValid(result));
        assertTrue(result.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}"));
    }

    @Test
    public void testCnpjValidUnformattedMultiBranch() {
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, false, true, true);
        assertTrue(isCNPJValid(result));
        assertTrue(result.matches("\\d{14}"));
    }

    @Test
    public void testCnpjValidFormattedSingleBranch() {
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, true, true, false);
        assertTrue(isCNPJValid(result));
        assertTrue(result.matches("\\d{2}\\.\\d{3}\\.\\d{3}/0001-\\d{2}"));
    }

    @Test
    public void testCnpjValidUnformattedSingleBranch() {
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, false, true, false);
        assertTrue(isCNPJValid(result));
        assertTrue(result.matches("\\d{14}"));
    }

    @Test
    public void testCnpjInvalidFormatted() {
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, true, false, false);
        assertFalse(isCNPJValid(result));
        assertTrue(result.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}"));
    }

    @Test
    public void testCnpjInvalidUnformatted() {
        String result = IdNumberGeneratorPtBrUtil.cnpj(faker, false, false, false);
        assertFalse(isCNPJValid(result));
        assertTrue(result.matches("\\d{14}"));
    }
}
