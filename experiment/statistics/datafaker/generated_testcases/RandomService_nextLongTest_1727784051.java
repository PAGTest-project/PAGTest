
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class RandomService_nextLongTest {

    private RandomService randomService;
    private Random mockRandom;

    @BeforeEach
    public void setUp() {
        mockRandom = Mockito.mock(Random.class);
        randomService = new RandomService(mockRandom);
    }

    @Test
    public void testNextLongWithPositiveBound() {
        when(mockRandom.nextLong()).thenReturn(1234567890L);
        long result = randomService.nextLong(100L);
        assertEquals(90L, result);
    }

    @Test
    public void testNextLongWithNonPositiveBound() {
        assertThrows(IllegalArgumentException.class, () -> randomService.nextLong(0L));
    }
}
