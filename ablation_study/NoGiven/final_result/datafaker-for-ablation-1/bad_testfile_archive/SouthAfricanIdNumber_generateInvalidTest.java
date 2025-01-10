
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseFaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.datafaker.idnumbers.SouthAfricanIdNumber.isValidEnZASsn;
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
    public void testGenerateInvalidSsn() {
        String invalidSsn = southAfricanIdNumber.generateInvalid(faker);
        assertFalse(isValidEnZASsn(invalidSsn));
    }

    @Test
    public void testGenerateInvalidSsnWithValidPattern() {
        String validPattern = SouthAfricanIdNumber.VALID_PATTERN;
        String invalidSsn = southAfricanIdNumber.generateInvalid(faker);
        assertFalse(invalidSsn.matches(validPattern));
    }

    @Test
    public void testGenerateInvalidSsnWithInvalidChecksum() {
        String invalidSsn = southAfricanIdNumber.generateInvalid(faker);
        int checksum = SouthAfricanIdNumber.calculateChecksum(invalidSsn.substring(0, 12), 12);
        assertFalse(invalidSsn.endsWith(String.valueOf(checksum)));
    }
}
