
package net.datafaker.idnumbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SouthAfricanIdNumber_isValidEnZASsnTest {

    @Test
    void testIsValidEnZASsn_ValidSsn() {
        // Given
        String validSsn = "9201015009087"; // A valid SSN

        // When
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(validSsn);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsValidEnZASsn_InvalidLength() {
        // Given
        String invalidLengthSsn = "920101500908"; // Invalid length

        // When
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(invalidLengthSsn);

        // Then
        assertFalse(result);
    }

    @Test
    void testIsValidEnZASsn_InvalidDate() {
        // Given
        String invalidDateSsn = "9202305009087"; // Invalid date (February 30th)

        // When
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(invalidDateSsn);

        // Then
        assertFalse(result);
    }

    @Test
    void testIsValidEnZASsn_InvalidChecksum() {
        // Given
        String invalidChecksumSsn = "9201015009088"; // Invalid checksum

        // When
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(invalidChecksumSsn);

        // Then
        assertFalse(result);
    }
}
