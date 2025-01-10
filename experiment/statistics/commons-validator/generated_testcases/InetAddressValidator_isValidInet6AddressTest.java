
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
    public void testValidIPv6Address() {
        assertTrue(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334"), "2001:0db8:85a3:0000:0000:8a2e:0370:7334 should be valid");
    }

    @Test
    public void testInvalidIPv6Address() {
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3::8a2e:0370:7334::"), "2001:0db8:85a3::8a2e:0370:7334:: should be invalid");
    }

    @Test
    public void testIPv6AddressWithPrefix() {
        assertTrue(validator.isValidInet6Address("2001:0db8:85a3::8a2e:0370:7334/64"), "2001:0db8:85a3::8a2e:0370:7334/64 should be valid");
    }

    @Test
    public void testIPv6AddressWithInvalidPrefix() {
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3::8a2e:0370:7334/129"), "2001:0db8:85a3::8a2e:0370:7334/129 should be invalid");
    }

    @Test
    public void testIPv6AddressWithZoneId() {
        assertTrue(validator.isValidInet6Address("fe80::8a2e:0370:7334%eth0"), "fe80::8a2e:0370:7334%eth0 should be valid");
    }

    @Test
    public void testIPv6AddressWithInvalidZoneId() {
        assertFalse(validator.isValidInet6Address("fe80::8a2e:0370:7334%"), "fe80::8a2e:0370:7334% should be invalid");
    }

    @Test
    public void testIPv6AddressWithIPv4Ending() {
        assertTrue(validator.isValidInet6Address("::ffff:192.0.2.128"), "::ffff:192.0.2.128 should be valid");
    }

    @Test
    public void testIPv6AddressWithInvalidIPv4Ending() {
        assertFalse(validator.isValidInet6Address("::ffff:192.0.2.256"), "::ffff:192.0.2.256 should be invalid");
    }
}
