
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RandomService_nextLongTest {
    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        randomService = new RandomService(new Random());
    }

    @Test
    public void testNextLongWithPositiveBound() {
        long bound = 100L;
        long result = randomService.nextLong(bound);
        assertTrue(result >= 0 && result < bound);
    }

    @Test
    public void testNextLongWithBoundOne() {
        long bound = 1L;
        long result = randomService.nextLong(bound);
        assertEquals(0L, result);
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, -1L, -100L})
    public void testNextLongWithNonPositiveBound(long bound) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            randomService.nextLong(bound);
        });
        assertEquals("bound must be positive: " + bound, exception.getMessage());
    }
}
