
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

public class Finance_ibanTest {

    private Finance finance;

    @BeforeEach
    public void setUp() {
        finance = new Finance(new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation to satisfy the abstract method
            }
        });
    }

    @Test
    void testIbanSupportedCountries() {
        Set<String> supportedCountries = Finance.ibanSupportedCountries();
        assertNotNull(supportedCountries);
        assertTrue(supportedCountries.size() > 70);
    }

    @Test
    void testIbanGeneration() {
        String iban = finance.iban();
        assertNotNull(iban);
        assertTrue(iban.length() > 10); // Assuming a reasonable minimum length for an IBAN
    }

    @Test
    void testIbanGenerationWithCountryCode() {
        String countryCode = "DE"; // Example country code
        String iban = finance.iban(countryCode);
        assertNotNull(iban);
        assertTrue(iban.startsWith(countryCode));
    }
}
