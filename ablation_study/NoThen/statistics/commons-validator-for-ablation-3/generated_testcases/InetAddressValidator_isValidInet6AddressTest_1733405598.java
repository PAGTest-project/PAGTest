
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InetAddressValidator_isValidInet6AddressTest {

    @Test
    void testValidIPv6Address() {
        InetAddressValidator validator = new InetAddressValidator();

        // Valid IPv6 address with prefix and zone ID
        assertTrue(validator.isValidInet6Address("2001:0db8:85a3::8a2e:0370:7334/64%eth0"));

        // Valid IPv6 address with compressed zeroes
        assertTrue(validator.isValidInet6Address("2001:db8::1"));

        // Valid IPv6 address with embedded IPv4 address
        assertTrue(validator.isValidInet6Address("::ffff:192.0.2.128"));

        // Invalid IPv6 address with multiple "::"
        assertFalse(validator.isValidInet6Address("2001::db8::1"));

        // Invalid IPv6 address with invalid prefix
        assertFalse(validator.isValidInet6Address("2001:db8::1/129"));

        // Invalid IPv6 address with invalid zone ID
        assertFalse(validator.isValidInet6Address("2001:db8::1%invalid"));

        // Invalid IPv6 address with too many hex groups
        assertFalse(validator.isValidInet6Address("2001:db8:1:2:3:4:5:6:7"));

        // Invalid IPv6 address with invalid hex group
        assertFalse(validator.isValidInet6Address("2001:db8:1:2:3:4:5:XXXX"));
    }
}
