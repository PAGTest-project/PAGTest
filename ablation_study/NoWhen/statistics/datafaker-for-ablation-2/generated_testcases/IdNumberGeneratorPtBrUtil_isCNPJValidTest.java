
package net.datafaker.idnumbers.pt.br;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;
import java.net.URL;
import java.nio.file.Path;

public class IdNumberGeneratorPtBrUtil_isCNPJValidTest {

    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders() {
            @Override
            public String expression(String expression) {
                return null;
            }

            @Override
            public void addUrl(Locale locale, URL url) {
                // Implementation not needed for this test
            }

            @Override
            public void addPath(Locale locale, Path path) {
                // Implementation not needed for this test
            }
        };
    }

    @Test
    public void testIsCNPJValid_ValidCNPJ() {
        String validCNPJ = IdNumberGeneratorPtBrUtil.cnpj(faker, false, true, true);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(validCNPJ));
    }

    @Test
    public void testIsCNPJValid_InvalidCNPJ() {
        String invalidCNPJ = IdNumberGeneratorPtBrUtil.cnpj(faker, false, false, true);
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(invalidCNPJ));
    }

    @Test
    public void testIsCNPJValid_UnmaskedCNPJ() {
        String cnpj = "12.345.678/0001-95";
        String unmaskedCNPJ = DocumentFormatterUtil.unmask(cnpj);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(unmaskedCNPJ));
    }

    @Test
    public void testIsCNPJValid_InvalidFormat() {
        String invalidFormatCNPJ = "12345678901234"; // Missing one digit
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(invalidFormatCNPJ));
    }
}
