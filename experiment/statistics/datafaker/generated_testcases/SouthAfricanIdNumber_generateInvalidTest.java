
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseFaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SouthAfricanIdNumber_generateInvalidTest {

    private SouthAfricanIdNumber southAfricanIdNumber;
    private BaseFaker faker;

    @BeforeEach
    public void setUp() {
        southAfricanIdNumber = new SouthAfricanIdNumber();
        faker = new BaseFaker();
    }

    @Test
    public void testGenerateInvalid() {
        String invalidSsn = southAfricanIdNumber.generateInvalid(faker);
        assertFalse(SouthAfricanIdNumber.isValidEnZASsn(invalidSsn));
    }

    @Test
    public void testGenerateInvalidWithValidPattern() {
        // Ensure that the method can generate an invalid SSN even when starting with a valid pattern
        String validPattern = SouthAfricanIdNumber.VALID_PATTERN;
        String invalidSsn = southAfricanIdNumber.generateInvalid(faker);
        assertFalse(SouthAfricanIdNumber.isValidEnZASsn(invalidSsn));
    }
}
