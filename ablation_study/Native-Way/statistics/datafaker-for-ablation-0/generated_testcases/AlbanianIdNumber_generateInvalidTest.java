
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

        // Ensure the invalid PIN is different from the valid one
        assertNotEquals(validPin, invalidPin);
    }
}
