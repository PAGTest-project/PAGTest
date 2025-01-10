
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Locality_localeStringWithoutReplacementTest {
    private Locality locality;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker();
        locality = new Locality(faker.base());
    }

    @Test
    void testLocaleStringWithoutReplacement() {
        // Test that the method returns a locale from the shuffled list
        Random random = new Random(12345L);
        String locale1 = locality.localeStringWithoutReplacement(random);
        String locale2 = locality.localeStringWithoutReplacement(random);

        // Ensure that the locales are different
        assertTrue(locality.allSupportedLocales().contains(locale1));
        assertTrue(locality.allSupportedLocales().contains(locale2));
        assertEquals(locality.allSupportedLocales().size(), 71); // Total number of locales
    }

    @Test
    void testLocaleStringWithoutReplacementReset() {
        // Test that the method resets the shuffled list when it is empty
        Random random = new Random(12345L);
        List<String> allLocales = locality.allSupportedLocales();
        for (int i = 0; i < allLocales.size(); i++) {
            locality.localeStringWithoutReplacement(random);
        }

        // After all locales are used, the list should reset
        String localeAfterReset = locality.localeStringWithoutReplacement(random);
        assertTrue(allLocales.contains(localeAfterReset));
    }
}
