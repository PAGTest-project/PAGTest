
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdNumber_validTest {

    private Faker faker;
    private IdNumber idNumber;

    @BeforeEach
    public void setUp() {
        faker = new Faker(new Locale("en", "ZA"));
        idNumber = new IdNumber(faker);
    }

    @Test
    public void testValidWithCountryProvider() {
        String result = idNumber.valid();
        assertTrue(result.matches("\\d{10}[01]8\\d"));
    }

    @Test
    public void testValidWithoutCountryProvider() {
        faker = new Faker(new Locale("unknown"));
        idNumber = new IdNumber(faker);
        String result = idNumber.valid();
        assertTrue(result.matches("\\d+"));
    }
}
