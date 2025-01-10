
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
    public void testHexWithPositiveLengthAndUppercase() {
        String result = randomService.hex(8, true);
        assertEquals(8, result.length());
        assertTrue(result.matches("^[0-9A-F]{8}$"));
    }

    @Test
    public void testHexWithPositiveLengthAndLowercase() {
        String result = randomService.hex(8, false);
        assertEquals(8, result.length());
        assertTrue(result.matches("^[0-9a-f]{8}$"));
    }

    @Test
    public void testHexWithZeroLength() {
        String result = randomService.hex(0, true);
        assertEquals("", result);
    }

    @Test
    public void testHexWithNegativeLength() {
        String result = randomService.hex(-5, true);
        assertEquals("", result);
    }
}
