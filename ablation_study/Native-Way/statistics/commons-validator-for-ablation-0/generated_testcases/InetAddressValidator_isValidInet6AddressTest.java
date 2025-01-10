
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
        assertTrue(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334"), "Valid IPv6 address should be valid");
        assertTrue(validator.isValidInet6Address("2001:db8:85a3::8a2e:370:7334"), "Valid IPv6 address with compressed zeroes should be valid");
        assertTrue(validator.isValidInet6Address("::1"), "Valid IPv6 loopback address should be valid");
        assertTrue(validator.isValidInet6Address("::ffff:192.0.2.128"), "Valid IPv6 address with embedded IPv4 should be valid");
    }

    @Test
    public void testInvalidIPv6Address() {
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334:extra"), "IPv6 address with extra segment should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3::0000::8a2e:0370:7334"), "IPv6 address with multiple compressed zeroes should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/129"), "IPv6 address with out-of-range prefix should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%invalid"), "IPv6 address with invalid zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/invalid"), "IPv6 address with invalid prefix should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%invalid/64"), "IPv6 address with invalid zone ID and valid prefix should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/64%invalid"), "IPv6 address with valid prefix and invalid zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/64%"), "IPv6 address with valid prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%/64"), "IPv6 address with empty prefix and valid zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%/invalid"), "IPv6 address with empty prefix and invalid zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%invalid/invalid"), "IPv6 address with invalid prefix and invalid zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/invalid%invalid"), "IPv6 address with invalid prefix and invalid zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/invalid%"), "IPv6 address with invalid prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%/invalid"), "IPv6 address with empty prefix and invalid zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334%"), "IPv6 address with empty prefix and empty zone ID should be invalid");
        assertFalse(validator.isValidInet6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334/"), "IPv6 address with empty prefix and empty zone ID should be invalid");