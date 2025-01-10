
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class Locality_localeStringWithoutReplacementTest {
    private Locality locality;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        locality = new Locality(faker.base());
    }

    @RepeatedTest(100)
    void localeStringWithoutReplacement() {
        Random random = new Random();
        List<String> allLocales = locality.allSupportedLocales();
        String firstLocale = locality.localeStringWithoutReplacement(random);
        assertThat(allLocales).contains(firstLocale);

        for (int i = 1; i < allLocales.size(); i++) {
            String nextLocale = locality.localeStringWithoutReplacement(random);
            assertThat(allLocales).contains(nextLocale);
            assertThat(nextLocale).isNotEqualTo(firstLocale);
            firstLocale = nextLocale;
        }

        // After all locales have been used, the list should reset and start again
        String resetLocale = locality.localeStringWithoutReplacement(random);
        assertThat(allLocales).contains(resetLocale);
    }

    @Test
    void localeStringWithoutReplacement_reset() {
        Random random = new Random();
        List<String> allLocales = locality.allSupportedLocales();
        for (int i = 0; i < allLocales.size(); i++) {
            locality.localeStringWithoutReplacement(random);
        }

        // After all locales have been used, the list should reset and start again
        String resetLocale = locality.localeStringWithoutReplacement(random);
        assertThat(allLocales).contains(resetLocale);
    }
}
