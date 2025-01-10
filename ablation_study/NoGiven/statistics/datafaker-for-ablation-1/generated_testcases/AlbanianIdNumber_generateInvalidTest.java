
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

        // Assert that the invalid PIN is different from the valid PIN
        assertNotEquals(validPin, invalidPin);

        // Assert that the invalid PIN has an invalid month part
        int invalidMonth = Integer.parseInt(invalidPin.substring(2, 4));
        assertTrue(invalidMonth >= 93 && invalidMonth <= 99);
    }
}
