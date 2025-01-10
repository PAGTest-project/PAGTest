
package net.datafaker.idnumbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwedenIdNumber_isValidSwedishSsnTest {

    @Test
    public void testIsValidSwedishSsn_ValidSsn() {
        // Given a valid Swedish SSN
        String ssn = "811228-9874";

        // When isValidSwedishSsn is called
        boolean result = SwedenIdNumber.isValidSwedishSsn(ssn);

        // Then it should return true
        assertTrue(result);
    }

    @Test
    public void testIsValidSwedishSsn_InvalidLength() {
        // Given an SSN with invalid length
        String ssn = "811228-987";

        // When isValidSwedishSsn is called
        boolean result = SwedenIdNumber.isValidSwedishSsn(ssn);

        // Then it should return false
        assertFalse(result);
    }

    @Test
    public void testIsValidSwedishSsn_InvalidDate() {
        // Given an SSN with an invalid date
        String ssn = "990230-1234";

        // When isValidSwedishSsn is called
        boolean result = SwedenIdNumber.isValidSwedishSsn(ssn);

        // Then it should return false
        assertFalse(result);
    }

    @Test
    public void testIsValidSwedishSsn_InvalidChecksum() {
        // Given an SSN with an invalid checksum
        String ssn = "811228-9873";

        // When isValidSwedishSsn is called
        boolean result = SwedenIdNumber.isValidSwedishSsn(ssn);

        // Then it should return false
        assertFalse(result);
    }
}
