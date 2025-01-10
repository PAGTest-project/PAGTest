
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
    public void testValidIPv4Address() {
        assertTrue(validator.isValidInet4Address("192.168.1.1"), "192.168.1.1 should be valid");
    }

    @Test
    public void testInvalidIPv4Address() {
        assertFalse(validator.isValidInet4Address("256.256.256.256"), "256.256.256.256 should be invalid");
    }

    @Test
    public void testInvalidIPv4AddressWithLeadingZero() {
        assertFalse(validator.isValidInet4Address("192.168.01.1"), "192.168.01.1 should be invalid");
    }

    @Test
    public void testInvalidIPv4AddressWithEmptySegment() {
        assertFalse(validator.isValidInet4Address("192.168..1"), "192.168..1 should be invalid");
    }

    @Test
    public void testInvalidIPv4AddressWithNonNumericSegment() {
        assertFalse(validator.isValidInet4Address("192.168.a.1"), "192.168.a.1 should be invalid");
    }
}
