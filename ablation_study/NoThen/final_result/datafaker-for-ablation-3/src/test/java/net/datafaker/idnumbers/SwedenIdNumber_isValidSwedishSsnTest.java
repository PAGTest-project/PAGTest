
package net.datafaker.idnumbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SwedenIdNumber_isValidSwedishSsnTest {

    @Test
    void testValidSwedishSsn() {
        // Given a valid SSN
        String validSsn = "811228-9874";

        // When isValidSwedishSsn is called
        boolean result = SwedenIdNumber.isValidSwedishSsn(validSsn);

        // Then it should return true
        assertTrue(result);
    }

    @Test
    void testInvalidSwedishSsnLength() {
        // Given an SSN with invalid length
        String invalidLengthSsn = "811228-987";

        // When isValidSwedishSsn is called
        boolean result = SwedenIdNumber.isValidSwedishSsn(invalidLengthSsn);

        // Then it should return false
        assertFalse(result);
    }

    @Test
    void testInvalidSwedishSsnDate() {
        // Given an SSN with invalid date
        String invalidDateSsn = "811328-9874";

        // When isValidSwedishSsn is called
        boolean result = SwedenIdNumber.isValidSwedishSsn(invalidDateSsn);

        // Then it should return false
        assertFalse(result);
    }

    @Test
    void testInvalidSwedishSsnChecksum() {
        // Given an SSN with invalid checksum
        String invalidChecksumSsn = "811228-9875";

        // When isValidSwedishSsn is called
        boolean result = SwedenIdNumber.isValidSwedishSsn(invalidChecksumSsn);

        // Then it should return false
        assertFalse(result);
    }
}
