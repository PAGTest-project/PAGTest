
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomService_hexTest {
    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        randomService = new RandomService();
    }

    @Test
    public void testHexWithLengthZero() {
        assertEquals("", randomService.hex(0, true));
        assertEquals("", randomService.hex(0, false));
    }

    @Test
    public void testHexWithPositiveLengthUppercase() {
        String hexString = randomService.hex(10, true);
        assertEquals(10, hexString.length());
        assertTrue(hexString.matches("^[0-9A-F]{10}$"));
    }

    @Test
    public void testHexWithPositiveLengthLowercase() {
        String hexString = randomService.hex(10, false);
        assertEquals(10, hexString.length());
        assertTrue(hexString.matches("^[0-9a-f]{10}$"));
    }

    @Test
    public void testHexWithRandomLengthUppercase() {
        int length = randomService.nextInt(1, 100);
        String hexString = randomService.hex(length, true);
        assertEquals(length, hexString.length());
        assertTrue(hexString.matches("^[0-9A-F]{" + length + "}$"));
    }

    @Test
    public void testHexWithRandomLengthLowercase() {
        int length = randomService.nextInt(1, 100);
        String hexString = randomService.hex(length, false);
        assertEquals(length, hexString.length());
        assertTrue(hexString.matches("^[0-9a-f]{" + length + "}$"));
    }
}
