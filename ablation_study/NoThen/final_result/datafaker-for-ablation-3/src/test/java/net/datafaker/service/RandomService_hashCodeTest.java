
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomService_hashCodeTest {

    @Test
    public void testHashCodeWithSharedRandom() {
        RandomService randomService = new RandomService();
        assertEquals(1, randomService.hashCode());
    }

    @Test
    public void testHashCodeWithCustomRandom() {
        Random customRandom = new Random();
        RandomService randomService = new RandomService(customRandom);
        assertEquals(customRandom.hashCode(), randomService.hashCode());
    }

    @Test
    public void testHashCodeWithNullRandom() {
        RandomService randomService = new RandomService(null);
        assertEquals(1, randomService.hashCode());
    }
}
