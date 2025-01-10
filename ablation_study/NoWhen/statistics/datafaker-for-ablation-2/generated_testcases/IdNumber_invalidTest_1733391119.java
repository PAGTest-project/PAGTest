
package net.datafaker.providers.base;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IdNumber_invalidTest {

    private Faker faker;
    private IdNumber idNumber;

    @BeforeEach
    public void setUp() {
        faker = new Faker(new Locale("en", "ZA"));
        idNumber = new IdNumber(faker);
    }

    @Test
    public void testInvalidWithCountryProvider() {
        String invalidId = idNumber.invalid();
        assertFalse(isValidEnZASsn(invalidId));
    }

    @Test
    public void testInvalidWithoutCountryProvider() {
        faker = new Faker(new Locale("unknown"));
        idNumber = new IdNumber(faker);
        String invalidId = idNumber.invalid();
        assertTrue(invalidId.matches("\\d+"));
    }
}
