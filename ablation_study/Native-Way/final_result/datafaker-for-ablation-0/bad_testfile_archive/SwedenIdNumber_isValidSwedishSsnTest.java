
package net.datafaker.idnumbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SwedenIdNumber_isValidSwedishSsnTest {

    @Test
    void testIsValidSwedishSsn_InvalidLength() {
        assertFalse(SwedenIdNumber.isValidSwedishSsn("1234567890"));
    }

    @Test
    void testIsValidSwedishSsn_InvalidDate() {
        assertFalse(SwedenIdNumber.isValidSwedishSsn("990230-1234"));
    }

    @Test
    void testIsValidSwedishSsn_ValidChecksum() {
        assertTrue(SwedenIdNumber.isValidSwedishSsn("900101-1232"));
    }

    @Test
    void testIsValidSwedishSsn_InvalidChecksum() {
        assertFalse(SwedenIdNumber.isValidSwedishSsn("900101-1233"));
    }
}
