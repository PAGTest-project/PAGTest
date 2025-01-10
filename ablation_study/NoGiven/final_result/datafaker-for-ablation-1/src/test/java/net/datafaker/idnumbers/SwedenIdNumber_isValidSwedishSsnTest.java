
package net.datafaker.idnumbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SwedenIdNumber_isValidSwedishSsnTest {

    @Test
    void testIsValidSwedishSsn_ValidSsn() {
        // Given
        String validSsn = "811228-9874"; // A valid Swedish SSN

        // When
        boolean result = SwedenIdNumber.isValidSwedishSsn(validSsn);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsValidSwedishSsn_InvalidLength() {
        // Given
        String invalidLengthSsn = "811228-987"; // Invalid length

        // When
        boolean result = SwedenIdNumber.isValidSwedishSsn(invalidLengthSsn);

        // Then
        assertFalse(result);
    }

    @Test
    void testIsValidSwedishSsn_InvalidDate() {
        // Given
        String invalidDateSsn = "811328-9874"; // Invalid date (month 13)

        // When
        boolean result = SwedenIdNumber.isValidSwedishSsn(invalidDateSsn);

        // Then
        assertFalse(result);
    }

    @Test
    void testIsValidSwedishSsn_InvalidChecksum() {
        // Given
        String invalidChecksumSsn = "811228-9875"; // Invalid checksum

        // When
        boolean result = SwedenIdNumber.isValidSwedishSsn(invalidChecksumSsn);

        // Then
        assertFalse(result);
    }
}
