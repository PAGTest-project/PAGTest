
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class IdNumber_invalidTest {

    private Faker faker;
    private IdNumber idNumber;

    @BeforeEach
    public void setUp() {
        faker = new Faker(new Locale("es", "MX"));
        idNumber = new IdNumber(faker);
    }

    @Test
    public void testInvalid() {
        String invalidId = idNumber.invalid();
        assertThat(invalidId).isNotEmpty();
        // Additional assertions can be added based on the expected format of the invalid ID
    }

    @Test
    public void testInvalidWithNoCountryProvider() {
        faker = new Faker(new Locale("unknown", "XX"));
        idNumber = new IdNumber(faker);
        String invalidId = idNumber.invalid();
        assertThat(invalidId).isNotEmpty();
        // Additional assertions can be added based on the expected fallback behavior
    }
}
