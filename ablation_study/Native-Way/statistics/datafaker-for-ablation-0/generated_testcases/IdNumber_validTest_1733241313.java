
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdNumber_validTest {

    private Faker faker;
    private IdNumber idNumber;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        idNumber = new IdNumber(faker);
    }

    @Test
    public void testValidWithCountryProvider() {
        faker.setLocale(new Locale("en", "ZA"));
        String result = idNumber.valid();
        assertNotNull(result);
        assertTrue(result.matches("\\d{10}[01]8\\d"));
    }

    @Test
    public void testValidWithoutCountryProvider() {
        faker.setLocale(new Locale("en", "US"));
        String result = idNumber.valid();
        assertNotNull(result);
        assertTrue(result.matches("\\d+"));
    }
}
