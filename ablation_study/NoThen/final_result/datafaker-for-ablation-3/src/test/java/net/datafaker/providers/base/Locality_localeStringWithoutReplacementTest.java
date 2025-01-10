
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertTrue;
import net.datafaker.Faker;

public class Locality_localeStringWithoutReplacementTest {
    private Locality locality;
    private Random random;

    @BeforeEach
    public void setUp() {
        locality = new Locality(new Faker());
        random = new Random();
    }

    @Test
    void testLocaleStringWithoutReplacementInitial() {
        String locale = locality.localeStringWithoutReplacement(random);
        assertTrue(locality.allSupportedLocales().contains(locale));
    }

    @Test
    void testLocaleStringWithoutReplacementAllLocales() {
        for (int i = 0; i < locality.allSupportedLocales().size(); i++) {
            String locale = locality.localeStringWithoutReplacement(random);
            assertTrue(locality.allSupportedLocales().contains(locale));
        }
    }

    @Test
    void testLocaleStringWithoutReplacementReset() {
        for (int i = 0; i < locality.allSupportedLocales().size(); i++) {
            locality.localeStringWithoutReplacement(random);
        }
        String locale = locality.localeStringWithoutReplacement(random);
        assertTrue(locality.allSupportedLocales().contains(locale));
    }
}
