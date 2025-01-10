
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Locality_localeStringWithRandomTest {
    private Locality locality;
    private Random random;

    @BeforeEach
    public void setUp() {
        locality = new Locality(new Faker());
        random = new Random();
    }

    @Test
    public void testLocaleStringWithRandom() {
        String locale = locality.localeStringWithRandom(random);
        assertTrue(Locality.LOCALES.contains(locale));
    }
}
