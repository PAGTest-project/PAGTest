
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RandomService_hexTest {
    private RandomService randomService;
    private Random mockRandom;

    @BeforeEach
    public void setUp() {
        mockRandom = mock(Random.class);
        randomService = new RandomService(mockRandom);
    }

    @Test
    public void testHexWithZeroLength() {
        assertEquals("", randomService.hex(0, true));
        assertEquals("", randomService.hex(0, false));
    }

    @Test
    public void testHexWithPositiveLengthUppercase() {
        when(mockRandom.nextBytes(any(byte[].class))).thenAnswer(invocation -> {
            byte[] bytes = invocation.getArgument(0);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (i % 16); // Ensure each byte is within the range of HEX_UP
            }
            return null;
        });

        String result = randomService.hex(5, true);
        assertEquals(5, result.length());
        assertEquals("01234", result); // Since each byte is (i % 16), the result should be "01234"
    }

    @Test
    public void testHexWithPositiveLengthLowercase() {
        when(mockRandom.nextBytes(any(byte[].class))).thenAnswer(invocation -> {
            byte[] bytes = invocation.getArgument(0);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (i % 16); // Ensure each byte is within the range of HEX_LOWER
            }
            return null;
        });

        String result = randomService.hex(5, false);
        assertEquals(5, result.length());
        assertEquals("01234", result); // Since each byte is (i % 16), the result should be "01234"
    }
}
