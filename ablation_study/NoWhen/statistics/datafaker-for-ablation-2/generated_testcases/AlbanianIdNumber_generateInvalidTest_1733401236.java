
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlbanianIdNumber_generateInvalidTest {
    private AlbanianIdNumber generator;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        generator = new AlbanianIdNumber();
        faker = new Faker();
    }

    @Test
    public void testGenerateInvalid() {
        String validPin = generator.generateValid(faker);
        String invalidPin = generator.generateInvalid(faker);

        // Ensure the invalid month is within the specified range
        int invalidMonth = Integer.parseInt(invalidPin.substring(2, 4));
        assertTrue(invalidMonth >= 93 && invalidMonth <= 99);

        // Ensure the rest of the PIN remains the same
        assertEquals(validPin.substring(0, 2), invalidPin.substring(0, 2));
        assertEquals(validPin.substring(4), invalidPin.substring(4));
    }
}
