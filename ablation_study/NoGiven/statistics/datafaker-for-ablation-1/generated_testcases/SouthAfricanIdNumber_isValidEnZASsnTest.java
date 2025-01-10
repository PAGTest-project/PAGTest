
package net.datafaker.idnumbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SouthAfricanIdNumber_isValidEnZASsnTest {

    @Test
    void testIsValidEnZASsn_ValidSsn() {
        // Given
        String validSsn = "8001015000089"; // A valid SSN

        // When
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(validSsn);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsValidEnZASsn_InvalidLength() {
        // Given
        String invalidLengthSsn = "800101500008"; // Invalid length (12 characters)

        // When
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(invalidLengthSsn);

        // Then
        assertFalse(result);
    }

    @Test
    void testIsValidEnZASsn_InvalidDate() {
        // Given
        String invalidDateSsn = "8013015000089"; // Invalid date (month 13)

        // When
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(invalidDateSsn);

        // Then
        assertFalse(result);
    }

    @Test
    void testIsValidEnZASsn_InvalidChecksum() {
        // Given
        String invalidChecksumSsn = "8001015000088"; // Invalid checksum (last digit should be 9)

        // When
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(invalidChecksumSsn);

        // Then
        assertFalse(result);
    }
}
