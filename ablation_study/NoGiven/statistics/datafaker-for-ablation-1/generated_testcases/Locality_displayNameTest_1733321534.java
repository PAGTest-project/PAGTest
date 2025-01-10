
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Locality_displayNameTest {
    private Locality locality;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        locality = new Locality(faker);
    }

    @Test
    void testDisplayNameWithLocaleString() {
        String localeString = locality.localeString();
        Locale locale = Locale.forLanguageTag(localeString);
        String expectedDisplayLanguage = locale.getDisplayLanguage(Locale.ROOT);
        String expectedDisplayCountry = locale.getDisplayCountry(Locale.ROOT);
        String expectedDisplayName = expectedDisplayLanguage;
        if (!expectedDisplayCountry.isEmpty()) {
            expectedDisplayName += " (" + expectedDisplayCountry + ")";
        }

        String actualDisplayName = locality.displayName();
        assertNotNull(actualDisplayName);
        assertFalse(actualDisplayName.isEmpty());
    }

    @Test
    void testDisplayNameWithLocaleStringWithoutReplacement() {
        String localeString = locality.localeStringWithoutReplacement();
        Locale locale = Locale.forLanguageTag(localeString);
        String expectedDisplayLanguage = locale.getDisplayLanguage(Locale.ROOT);
        String expectedDisplayCountry = locale.getDisplayCountry(Locale.ROOT);
        String expectedDisplayName = expectedDisplayLanguage;
        if (!expectedDisplayCountry.isEmpty()) {
            expectedDisplayName += " (" + expectedDisplayCountry + ")";
        }

        String actualDisplayName = locality.displayName();
        assertNotNull(actualDisplayName);
        assertFalse(actualDisplayName.isEmpty());
    }

    @RepeatedTest(10)
    void testDisplayNameRandomness() {
        String displayName1 = locality.displayName();
        String displayName2 = locality.displayName();
        assertNotNull(displayName1);
        assertNotNull(displayName2);
        assertFalse(displayName1.isEmpty());
        assertFalse(displayName2.isEmpty());
    }
}
