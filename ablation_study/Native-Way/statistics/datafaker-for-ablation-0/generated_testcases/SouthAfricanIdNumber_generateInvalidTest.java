
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
    void testGenerateInvalidSsn() {
        String invalidSsn = southAfricanIdNumber.generateInvalid(faker);
        assertFalse(SouthAfricanIdNumber.isValidEnZASsn(invalidSsn));
    }

    @Test
    void testGenerateInvalidSsnMultipleTimes() {
        for (int i = 0; i < 10; i++) {
            String invalidSsn = southAfricanIdNumber.generateInvalid(faker);
            assertFalse(SouthAfricanIdNumber.isValidEnZASsn(invalidSsn));
        }
    }

    @Test
    void testGenerateInvalidSsnWithInvalidPattern() {
        String invalidPattern = "foo2204720082";
        String invalidSsn = southAfricanIdNumber.generateInvalid(faker);
        assertFalse(invalidSsn.contains(invalidPattern));
    }

    @Test
    void testGenerateInvalidSsnWithValidPattern() {
        String validPattern = "9202204720083";
        String invalidSsn = southAfricanIdNumber.generateInvalid(faker);
        assertFalse(invalidSsn.equals(validPattern));
    }
}
