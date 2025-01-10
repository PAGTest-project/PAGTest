
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
        assertTrue(validator.isValidInet4Address("192.168.1.1"), "Valid IPv4 address should be valid");
        assertTrue(validator.isValidInet4Address("255.255.255.255"), "Valid IPv4 address should be valid");
        assertTrue(validator.isValidInet4Address("0.0.0.0"), "Valid IPv4 address should be valid");
    }

    @Test
    public void testInvalidInet4Address() {
        assertFalse(validator.isValidInet4Address("256.256.256.256"), "Invalid IPv4 address should be invalid");
        assertFalse(validator.isValidInet4Address("192.168.1"), "Invalid IPv4 address should be invalid");
        assertFalse(validator.isValidInet4Address("192.168.1.1.1"), "Invalid IPv4 address should be invalid");
        assertFalse(validator.isValidInet4Address("192.168.1.a"), "Invalid IPv4 address should be invalid");
        assertFalse(validator.isValidInet4Address("192.168.01.1"), "Invalid IPv4 address should be invalid");
        assertFalse(validator.isValidInet4Address("192.168..1"), "Invalid IPv4 address should be invalid");
    }
}
