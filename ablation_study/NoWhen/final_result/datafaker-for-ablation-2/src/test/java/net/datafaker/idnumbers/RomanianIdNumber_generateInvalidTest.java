
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RomanianIdNumber_generateInvalidTest {
    private RomanianIdNumber impl;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        impl = new RomanianIdNumber();
        faker = new Faker();
    }

    @Test
    public void testGenerateInvalid() {
        String validId = impl.generateValid(faker);
        String invalidId = impl.generateInvalid(faker);

        // Ensure the generated invalid ID is not equal to a valid ID
        assertNotEquals(validId, invalidId);

        // Ensure the checksum of the invalid ID is incorrect
        String basePart = invalidId.substring(0, invalidId.length() - 1);
        int expectedChecksum = (impl.checksum(basePart) + 1) % 10;
        int actualChecksum = Character.getNumericValue(invalidId.charAt(invalidId.length() - 1));
        assertEquals(expectedChecksum, actualChecksum);
    }
}
