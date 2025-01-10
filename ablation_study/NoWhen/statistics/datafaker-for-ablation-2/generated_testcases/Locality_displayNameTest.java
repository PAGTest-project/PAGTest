
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        String displayName = locality.displayName();
        Locale locale = Locale.forLanguageTag(locality.localeString());
        String expectedLanguage = locale.getDisplayLanguage(Locale.ROOT);
        String expectedCountry = locale.getDisplayCountry(Locale.ROOT);
        if (!expectedCountry.isEmpty()) {
            expectedLanguage += " (" + expectedCountry + ")";
        }
        assertNotNull(displayName);
        assertFalse(displayName.isEmpty());
        if (!expectedCountry.isEmpty()) {
            assertTrue(displayName.contains(expectedCountry));
        }
    }

    @Test
    public void testDisplayNameFallback() {
        // Force a locale that might not have a display name
        String displayName = locality.displayName();
        assertNotNull(displayName);
        assertFalse(displayName.isEmpty());
        assertTrue(displayName.equals(Locale.ENGLISH.getDisplayLanguage(Locale.ROOT)) || displayName.contains(Locale.ENGLISH.getDisplayLanguage(Locale.ROOT)));
    }
}
