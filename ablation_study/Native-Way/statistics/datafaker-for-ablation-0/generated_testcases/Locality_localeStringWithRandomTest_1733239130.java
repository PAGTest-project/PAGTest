
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Locality_localeStringWithRandomTest {
    private Locality locality;
    private Random random;

    @BeforeEach
    public void setUp() {
        locality = new Locality(new net.datafaker.Faker());
        random = new Random();
    }

    @Test
    void testLocaleStringWithRandom() {
        String result = locality.localeStringWithRandom(random);
        assertNotNull(result);
        assertTrue(Locality.allSupportedLocales().contains(result));
    }
}
