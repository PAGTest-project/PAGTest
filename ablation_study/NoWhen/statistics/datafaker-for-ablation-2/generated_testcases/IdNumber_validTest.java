
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
    public void testValidIdNumber() {
        String validIdNumber = idNumber.valid();
        assertTrue(validIdNumber.matches("\\d{10}[01]8\\d"));
    }

    @Test
    public void testInvalidIdNumber() {
        String invalidIdNumber = idNumber.invalid();
        assertTrue(invalidIdNumber.matches("\\d{10}[01]8\\d"));
    }
}
