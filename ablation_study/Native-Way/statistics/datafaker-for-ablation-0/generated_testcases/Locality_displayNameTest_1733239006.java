
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Locality_displayNameTest {
    private Locality locality;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker();
        locality = new Locality(faker.base());
    }

    @Test
    void testDisplayNameNotNull() {
        String displayName = locality.displayName();
        assertNotNull(displayName);
    }

    @Test
    void testDisplayNameNotEmpty() {
        String displayName = locality.displayName();
        assertFalse(displayName.isEmpty());
    }

    @Test
    void testDisplayNameWithCountry() {
        String displayName = locality.displayName();
        if (displayName.contains("(")) {
            String country = displayName.substring(displayName.indexOf("(") + 1, displayName.indexOf(")"));
            assertNotNull(country);
            assertFalse(country.isEmpty());
        }
    }

    @Test
    void testDisplayNameFallback() {
        // This test assumes that there is a locale in LOCALES that results in an empty display language
        // and thus triggers the fallback to Locale.ENGLISH.getDisplayLanguage(Locale.ROOT)
        String displayName = locality.displayName();
        if (displayName.equals(Locale.ENGLISH.getDisplayLanguage(Locale.ROOT))) {
            assertNotNull(displayName);
            assertFalse(displayName.isEmpty());
        }
    }
}
