
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
        finance = new Finance(new BaseProviders());
    }

    @Test
    void testIban() {
        String iban = finance.iban();
        assertNotNull(iban);
        assertTrue(isValidIban(iban));
    }

    private boolean isValidIban(String iban) {
        Set<String> supportedCountries = Finance.ibanSupportedCountries();
        String countryCode = iban.substring(0, 2);
        return supportedCountries.contains(countryCode);
    }
}
