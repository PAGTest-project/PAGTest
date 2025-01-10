
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Medical_diagnosisCodeTest {
    private Medical medical;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                return "A00"; // Mock implementation for testing purposes
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // No-op implementation for testing purposes
            }
        };
        medical = new Medical(faker);
    }

    @Test
    public void testDiagnosisCode() {
        String diagnosisCode = medical.diagnosisCode();
        assertTrue(diagnosisCode.matches("^[A-Z][0-9]{2}$"));
    }
}
