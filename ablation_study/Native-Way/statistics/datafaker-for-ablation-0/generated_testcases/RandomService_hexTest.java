
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomService_hexTest {
    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        randomService = new RandomService();
    }

    @Test
    public void testHexWithZeroLength() {
        assertEquals("", randomService.hex(0, true));
        assertEquals("", randomService.hex(0, false));
    }

    @Test
    public void testHexWithPositiveLengthUppercase() {
        String hexString = randomService.hex(10, true);
        assertEquals(10, hexString.length());
        for (char c : hexString.toCharArray()) {
            assert("0123456789ABCDEF".indexOf(c) != -1);
        }
    }

    @Test
    public void testHexWithPositiveLengthLowercase() {
        String hexString = randomService.hex(10, false);
        assertEquals(10, hexString.length());
        for (char c : hexString.toCharArray()) {
            assert("0123456789abcdef".indexOf(c) != -1);
        }
    }

    @Test
    public void testHexWithNegativeLength() {
        assertEquals("", randomService.hex(-5, true));
        assertEquals("", randomService.hex(-5, false));
    }
}
