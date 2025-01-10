
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class Locality_localeStringWithRandomTest {
    private Locality locality;
    private Random random;

    @BeforeEach
    public void setUp() {
        locality = new Locality(new Faker());
        random = new Random();
    }

    @Test
    void allSupportedLocales() {
        List<String> allLocales = locality.allSupportedLocales();
        assertThat(allLocales).hasSize(87);
    }

    @RepeatedTest(10)
    void localeStringWithRandom() {
        String randomLocale = locality.localeStringWithRandom(random);
        List<String> allLocales = locality.allSupportedLocales();
        assertThat(allLocales).contains(randomLocale);
    }
}
