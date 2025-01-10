
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
        String hexString = randomService.hex(8, true);
        assertEquals(8, hexString.length());
        assertTrue(hexString.matches("^[0-9A-F]{8}$"));
    }

    @Test
    public void testHexWithPositiveLengthLowercase() {
        String hexString = randomService.hex(8, false);
        assertEquals(8, hexString.length());
        assertTrue(hexString.matches("^[0-9a-f]{8}$"));
    }

    @Test
    public void testHexWithCustomRandom() {
        Random customRandom = new Random(12345L);
        RandomService customRandomService = new RandomService(customRandom);
        String hexString = customRandomService.hex(8, true);
        assertEquals(8, hexString.length());
        assertTrue(hexString.matches("^[0-9A-F]{8}$"));
    }
}
