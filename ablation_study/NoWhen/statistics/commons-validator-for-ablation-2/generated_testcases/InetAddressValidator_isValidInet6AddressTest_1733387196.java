
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InetAddressValidator_isValidInet6AddressTest {
    private InetAddressValidator validator;

    @BeforeEach
    protected void setUp() {
        validator = new InetAddressValidator();
    }

    @Test
    public void testValidInet6Address() {
        assertTrue(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334"), "2001:0db8:85a3:0000:0000:8a2e:0370:7334 should be valid");
        assertTrue(validator.isValidInet6Address("2001:db8::1"), "2001:db8::1 should be valid");
        assertTrue(validator.isValidInet6Address("::1"), "::1 should be valid");
        assertTrue(validator.isValidInet6Address("::ffff:192.0.2.128"), "::ffff:192.0.2.128 should be valid");
    }

    @Test
    public void testInvalidInet6Address() {
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334:1"), "2001:0db8:85a3:0000:0000:8a2e:0370:7334:1 should be invalid");
        assertFalse(validator.isValidInet6Address("2001:db8::1::1"), "2001:db8::1::1 should be invalid");
        assertFalse(validator.isValidInet6Address(":::"), "::: should be invalid");
        assertFalse(validator.isValidInet6Address("::ffff:192.0.2.300"), "::ffff:192.0.2.300 should be invalid");
    }

    @Test
    public void testValidInet6AddressWithPrefix() {
        assertTrue(validator.isValidInet6Address("2001:db8::1/64"), "2001:db8::1/64 should be valid");
        assertTrue(validator.isValidInet6Address("::1/128"), "::1/128 should be valid");
    }

    @Test
    public void testInvalidInet6AddressWithPrefix() {
        assertFalse(validator.isValidInet6Address("2001:db8::1/129"), "2001:db8::1/129 should be invalid");
        assertFalse(validator.isValidInet6Address("::1/abc"), "::1/abc should be invalid");
    }

    @Test
    public void testValidInet6AddressWithZoneId() {
        assertTrue(validator.isValidInet6Address("fe80::1%eth0"), "fe80::1%eth0 should be valid");
        assertTrue(validator.isValidInet6Address("fe80::1%1"), "fe80::1%1 should be valid");
    }

    @Test
    public void testInvalidInet6AddressWithZoneId() {
        assertFalse(validator.isValidInet6Address("fe80::1%"), "fe80::1% should be invalid");
        assertFalse(validator.isValidInet6Address("fe80::1%eth0/64"), "fe80::1%eth0/64 should be invalid");
    }
}
