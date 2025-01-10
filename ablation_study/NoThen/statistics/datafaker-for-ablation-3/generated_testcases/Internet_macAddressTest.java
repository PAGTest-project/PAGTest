
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Internet_macAddressTest {

    private final BaseFaker faker = new BaseFaker();
    private final Internet internet = new Internet(faker);

    @Test
    public void testMacAddressWithNullPrefix() {
        String result = internet.macAddress(null);
        assertEquals(17, result.length());
        assertTrue(result.matches("^([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$"));
    }

    @Test
    public void testMacAddressWithEmptyPrefix() {
        String result = internet.macAddress("");
        assertEquals(17, result.length());
        assertTrue(result.matches("^([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$"));
    }

    @Test
    public void testMacAddressWithValidPrefix() {
        String result = internet.macAddress("00:1A:2B");
        assertEquals(17, result.length());
        assertTrue(result.startsWith("00:1A:2B"));
        assertTrue(result.matches("^00:1A:2B:([0-9A-Fa-f]{2}:){2}[0-9A-Fa-f]{2}$"));
    }
}
