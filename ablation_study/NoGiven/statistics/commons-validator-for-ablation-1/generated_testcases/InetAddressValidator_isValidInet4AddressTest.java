
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InetAddressValidator_isValidInet4AddressTest {
    private InetAddressValidator validator;

    @BeforeEach
    protected void setUp() {
        validator = new InetAddressValidator();
    }

    @Test
    public void testValidInet4Address() {
        final String[] valid = { "192.168.0.1", "10.0.0.1", "172.16.254.1", "255.255.255.255" };
        for (final String item : valid) {
            assertTrue(validator.isValidInet4Address(item), () -> String.format("%s should be valid", item));
        }
    }

    @Test
    public void testInvalidInet4Address() {
        final String[] invalid = { "256.256.256.256", "192.168.0", "192.168.0.1.1", "192.168.0.1.", "192.168.0.01", "192.168.0.1/24" };
        for (final String item : invalid) {
            assertFalse(validator.isValidInet4Address(item), () -> String.format("%s should be invalid", item));
        }
    }

    @Test
    public void testInvalidFormatInet4Address() {
        final String[] invalidFormat = { "192.168.0.a", "192.168.0.-1", "192.168.0.256", "192.168.0.1 ", " 192.168.0.1" };
        for (final String item : invalidFormat) {
            assertFalse(validator.isValidInet4Address(item), () -> String.format("%s should be invalid", item));
        }
    }

    @Test
    public void testLeadingZeroInet4Address() {
        final String[] invalidLeadingZero = { "192.168.00.1", "192.168.0.01", "0192.168.0.1" };
        for (final String item : invalidLeadingZero) {
            assertFalse(validator.isValidInet4Address(item), () -> String.format("%s should be invalid", item));
        }
    }
}
