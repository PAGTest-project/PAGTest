
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
    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        locality = new Locality(faker.base());
    }

    @Test
    void testLocaleStringWithoutReplacement() {
        Random random = new Random();
        List<String> allLocales = locality.allSupportedLocales();
        int initialSize = allLocales.size();

        for (int i = 0; i < initialSize; i++) {
            String locale = locality.localeStringWithoutReplacement(random);
            assertTrue(allLocales.contains(locale));
        }

        // After all locales have been returned once, the list should be reset
        String localeAfterReset = locality.localeStringWithoutReplacement(random);
        assertTrue(allLocales.contains(localeAfterReset));
    }

    @Test
    void testLocaleStringWithoutReplacementWithSameSeed() {
        Random random1 = new Random(12345L);
        Random random2 = new Random(12345L);

        String locale1 = locality.localeStringWithoutReplacement(random1);
        String locale2 = locality.localeStringWithoutReplacement(random2);

        assertEquals(locale1, locale2);
    }
}
