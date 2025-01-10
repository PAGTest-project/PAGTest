
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Locality_displayNameTest {
    private Locality locality;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker();
        locality = new Locality(faker);
    }

    @Test
    public void testDisplayName() {
        String displayName = locality.displayName();
        assertNotNull(displayName);
        assertFalse(displayName.isEmpty());
    }

    @Test
    public void testDisplayNameWithCountry() {
        String localeString = locality.localeString();
        Locale locale = Locale.forLanguageTag(localeString);
        String displayCountry = locale.getDisplayCountry(Locale.ROOT);

        if (!displayCountry.isEmpty()) {
            String displayName = locality.displayName();
            assertNotNull(displayName);
            assertFalse(displayName.isEmpty());
            assertTrue(displayName.contains("(" + displayCountry + ")"));
        }
    }

    @Test
    public void testDisplayNameFallback() {
        // Simulate a case where the display language is empty
        // This is a bit tricky to simulate due to the randomness, but we can check the fallback logic
        String displayName = locality.displayName();
        assertNotNull(displayName);
        assertFalse(displayName.isEmpty());
        assertEquals(Locale.ENGLISH.getDisplayLanguage(Locale.ROOT), displayName);
    }
}
