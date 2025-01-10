
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

public class Finance_ibanTest {
    private Finance finance;

    @BeforeEach
    public void setUp() {
        finance = new Finance(new BaseProviders() {});
    }

    @Test
    void testIbanSupportedCountries() {
        Set<String> supportedCountries = Finance.ibanSupportedCountries();
        assertNotNull(supportedCountries);
        assertTrue(supportedCountries.size() > 0);
    }

    @Test
    void testIbanGeneration() {
        String iban = finance.iban();
        assertNotNull(iban);
        assertTrue(iban.length() > 0);
    }

    @Test
    void testIbanWithCountryCode() {
        String iban = finance.iban("DE");
        assertNotNull(iban);
        assertTrue(iban.matches("DE\\d{20}"));
    }
}
