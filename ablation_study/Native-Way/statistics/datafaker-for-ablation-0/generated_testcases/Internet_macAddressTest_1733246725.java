
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Internet_macAddressTest {
    private Internet internet;

    @BeforeEach
    public void setUp() {
        BaseFaker faker = new BaseFaker();
        internet = new Internet(faker);
    }

    @Test
    public void testMacAddressWithPrefix() {
        String prefix = "00:1A:2B";
        String macAddress = internet.macAddress(prefix);
        assertEquals(prefix.length() + 15, macAddress.length());
        assertTrue(macAddress.startsWith(prefix));
    }

    @Test
    public void testMacAddressWithoutPrefix() {
        String macAddress = internet.macAddress(null);
        assertEquals(17, macAddress.length());
        assertTrue(macAddress.matches("([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}"));
    }

    @Test
    public void testMacAddressWithEmptyPrefix() {
        String macAddress = internet.macAddress("");
        assertEquals(17, macAddress.length());
        assertTrue(macAddress.matches("([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}"));
    }

    @Test
    public void testMacAddressWithInvalidPrefix() {
        String prefix = "00:1A:2B:3C:4D:5E:6F";
        String macAddress = internet.macAddress(prefix);
        assertEquals(prefix.length() + 5, macAddress.length());
        assertTrue(macAddress.startsWith(prefix));
    }
}
