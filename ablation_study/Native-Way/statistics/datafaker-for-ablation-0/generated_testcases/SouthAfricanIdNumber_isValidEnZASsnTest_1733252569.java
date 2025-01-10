
package net.datafaker.idnumbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SouthAfricanIdNumber_isValidEnZASsnTest {

    @Test
    void testIsValidEnZASsn_ValidSsn() {
        // Given a valid SSN
        String ssn = "8001015000089";

        // When isValidEnZASsn is called
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(ssn);

        // Then it should return true
        assertTrue(result);
    }

    @Test
    void testIsValidEnZASsn_InvalidLength() {
        // Given an SSN with invalid length
        String ssn = "800101500008";

        // When isValidEnZASsn is called
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(ssn);

        // Then it should return false
        assertFalse(result);
    }

    @Test
    void testIsValidEnZASsn_InvalidDate() {
        // Given an SSN with invalid date
        String ssn = "8013015000089";

        // When isValidEnZASsn is called
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(ssn);

        // Then it should return false
        assertFalse(result);
    }

    @Test
    void testIsValidEnZASsn_InvalidChecksum() {
        // Given an SSN with invalid checksum
        String ssn = "8001015000088";

        // When isValidEnZASsn is called
        boolean result = SouthAfricanIdNumber.isValidEnZASsn(ssn);

        // Then it should return false
        assertFalse(result);
    }
}
