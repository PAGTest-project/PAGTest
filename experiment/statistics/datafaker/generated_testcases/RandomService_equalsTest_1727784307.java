
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class RandomService_equalsTest {

    @Test
    void testEquals_SameInstance() {
        RandomService randomService = new RandomService();
        assertTrue(randomService.equals(randomService));
    }

    @Test
    void testEquals_DifferentInstancesWithSameRandom() {
        Random random = new Random();
        RandomService randomService1 = new RandomService(random);
        RandomService randomService2 = new RandomService(random);
        assertTrue(randomService1.equals(randomService2));
    }

    @Test
    void testEquals_DifferentInstancesWithDifferentRandom() {
        RandomService randomService1 = new RandomService(new Random());
        RandomService randomService2 = new RandomService(new Random());
        assertFalse(randomService1.equals(randomService2));
    }

    @Test
    void testEquals_DifferentClass() {
        RandomService randomService = new RandomService();
        assertFalse(randomService.equals("Not a RandomService"));
    }

    @Test
    void testEquals_Null() {
        RandomService randomService = new RandomService();
        assertFalse(randomService.equals(null));
    }
}
