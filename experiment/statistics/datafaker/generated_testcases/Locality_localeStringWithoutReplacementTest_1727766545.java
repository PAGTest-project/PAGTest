
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import net.datafaker.Faker;

public class Locality_localeStringWithoutReplacementTest {
    private Locality locality;

    @BeforeEach
    public void setUp() {
        locality = new Locality(new Faker());
    }

    @Test
    void testLocaleStringWithoutReplacement() {
        // Given
        Random random = new Random(5); // Fixed seed for deterministic results

        // When
        String locale1 = locality.localeStringWithoutReplacement(random);
        String locale2 = locality.localeStringWithoutReplacement(random);

        // Then
        assertEquals(locale1, locale2); // Since the seed is fixed, the first two locales should be the same

        // When
        String locale3 = locality.localeStringWithoutReplacement(random);

        // Then
        assertTrue(!locale1.equals(locale3)); // The third locale should be different from the first one
    }
}
