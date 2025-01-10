
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
        final String[] valid = {
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876",
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876/123",
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876/0",
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876%0",
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876%abcdefgh"
        };
        for (final String item : valid) {
            assertTrue(validator.isValidInet6Address(item), () -> String.format("%s should be valid", item));
        }
    }

    @Test
    public void testInvalidInet6Address() {
        final String[] invalid = {
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876/129", // too big
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876/-0", // sign not allowed
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876/+0", // sign not allowed
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876/10O", // non-digit
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876/0%0", // /bits before %node-id
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876%abc defgh", // space in node id
            "2001:0000:1234:0000:0000:C1C0:ABCD:0876%abc%defgh" // '%' in node id
        };
        for (final String item : invalid) {
            assertFalse(validator.isValidInet6Address(item), () -> String.format("%s should be invalid", item));
        }
    }

    @Test
    public void testValidInet6AddressWithIPv4() {
        final String valid = "2001:0000:1234:0000:0000:C1C0:192.168.0.1";
        assertTrue(validator.isValidInet6Address(valid), () -> String.format("%s should be valid", valid));
    }

    @Test
    public void testInvalidInet6AddressWithIPv4() {
        final String invalid = "2001:0000:1234:0000:0000:C1C0:256.256.256.256";
        assertFalse(validator.isValidInet6Address(invalid), () -> String.format("%s should be invalid", invalid));
    }

    @Test
    public void testInvalidInet6AddressWithMultiplePrefixes() {
        final String invalid = "2001:0000:1234:0000:0000:C1C0:ABCD:0876/64/64";
        assertFalse(validator.isValidInet6Address(invalid), () -> String.format("%s should be invalid", invalid));
    }

    @Test
    public void testInvalidInet6AddressWithInvalidZoneId() {
        final String invalid = "2001:0000:1234:0000:0000:C1C0:ABCD:0876%abc/defgh";
        assertFalse(validator.isValidInet6Address(invalid), () -> String.format("%s should be invalid", invalid));
    }
}
