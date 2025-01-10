
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String invalidIdNumber = impl.generateInvalid(faker);
        String basePart = invalidIdNumber.substring(0, invalidIdNumber.length() - 1);
        int expectedChecksum = (impl.checksum(basePart) + 1) % 10;
        int actualChecksum = Character.getNumericValue(invalidIdNumber.charAt(invalidIdNumber.length() - 1));
        assertEquals(expectedChecksum, actualChecksum);
    }
}
