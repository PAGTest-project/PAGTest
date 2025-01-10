
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Finance_ibanTest {
    private Finance finance;

    @BeforeEach
    public void setUp() {
        finance = new Finance(new BaseProviders());
    }

    @Test
    public void testIban() {
        String iban = finance.iban();
        assertNotNull(iban);
        assertTrue(iban.length() > 0);
    }

    @Test
    public void testIbanWithCountryCode() {
        String countryCode = "GB";
        String iban = finance.iban(countryCode);
        assertNotNull(iban);
        assertTrue(iban.startsWith(countryCode));
        assertTrue(iban.length() > countryCode.length());
    }
}
