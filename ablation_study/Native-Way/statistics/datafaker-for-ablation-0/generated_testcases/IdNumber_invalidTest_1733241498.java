
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdNumber_invalidTest {

    private Faker faker;
    private IdNumber idNumber;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        idNumber = new IdNumber(faker);
    }

    @Test
    public void testInvalidWithCountryProvider() {
        faker = new Faker(new Locale("sv", "SE"));
        idNumber = new IdNumber(faker);
        String result = idNumber.invalid();
        assertTrue(result.matches("\\d{10}[01]8\\d"));
    }

    @Test
    public void testInvalidWithoutCountryProvider() {
        faker = new Faker(new Locale("en", "US"));
        idNumber = new IdNumber(faker);
        String result = idNumber.invalid();
        assertTrue(result.matches("\\d+"));
    }
}
