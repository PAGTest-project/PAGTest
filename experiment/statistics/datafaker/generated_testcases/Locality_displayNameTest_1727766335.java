
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Locality_displayNameTest {
    private Locality locality;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        locality = new Locality(faker);
    }

    @Test
    public void testDisplayNameWithCountry() {
        // Mocking a random selection to a known locale with a country
        String localeWithCountry = "en-US";
        Random mockRandom = new Random(1); // Fixed seed for deterministic result
        String result = locality.localeStringWithRandom(mockRandom);
        assertEquals(localeWithCountry, result);

        // Now test displayName with the known locale
        Locale locale = Locale.forLanguageTag(localeWithCountry);
        String expectedDisplayName = locale.getDisplayLanguage(Locale.ROOT) + " (" + locale.getDisplayCountry(Locale.ROOT) + ")";
        assertEquals(expectedDisplayName, locality.displayName());
    }

    @Test
    public void testDisplayNameWithoutCountry() {
        // Mocking a random selection to a known locale without a country
        String localeWithoutCountry = "en";
        Random mockRandom = new Random(2); // Fixed seed for deterministic result
        String result = locality.localeStringWithRandom(mockRandom);
        assertEquals(localeWithoutCountry, result);

        // Now test displayName with the known locale
        Locale locale = Locale.forLanguageTag(localeWithoutCountry);
        String expectedDisplayName = locale.getDisplayLanguage(Locale.ROOT);
        assertEquals(expectedDisplayName, locality.displayName());
    }

    @Test
    public void testDisplayNameFallback() {
        // Mocking a random selection to a locale with an empty display name
        String localeWithEmptyDisplayName = "xx";
        Random mockRandom = new Random(3); // Fixed seed for deterministic result
        String result = locality.localeStringWithRandom(mockRandom);
        assertEquals(localeWithEmptyDisplayName, result);

        // Now test displayName with the known locale
        String expectedDisplayName = Locale.ENGLISH.getDisplayLanguage(Locale.ROOT);
        assertEquals(expectedDisplayName, locality.displayName());
    }
}
