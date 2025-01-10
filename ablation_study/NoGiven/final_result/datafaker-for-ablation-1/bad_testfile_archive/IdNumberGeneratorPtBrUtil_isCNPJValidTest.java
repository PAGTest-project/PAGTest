
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
            public String toString() {
                return "MockBaseProviders";
            }

            @Override
            public void addUrl(Locale locale, URL url) {
                // Dummy implementation to satisfy the abstract method
            }

            @Override
            public void addPath(Locale locale, Path path) {
                // Dummy implementation to satisfy the abstract method
            }
        };
    }

    @Test
    public void testIsCNPJValid_ValidCNPJ() {
        String validCNPJ = IdNumberGeneratorPtBrUtil.cnpj(faker, false, true, false);
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(validCNPJ));
    }

    @Test
    public void testIsCNPJValid_InvalidCNPJ() {
        String invalidCNPJ = "12345678000195"; // Example of an invalid CNPJ
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(invalidCNPJ));
    }

    @Test
    public void testIsCNPJValid_UnmaskedCNPJ() {
        String unmaskedCNPJ = "12.345.678/0001-95"; // Example of a masked CNPJ
        assertTrue(IdNumberGeneratorPtBrUtil.isCNPJValid(unmaskedCNPJ));
    }

    @Test
    public void testIsCNPJValid_PartialCNPJ() {
        String partialCNPJ = "123456780001"; // Partial CNPJ without verification digits
        assertFalse(IdNumberGeneratorPtBrUtil.isCNPJValid(partialCNPJ));
    }
}
