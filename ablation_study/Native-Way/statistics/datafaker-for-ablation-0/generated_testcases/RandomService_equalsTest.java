
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RandomService_equalsTest {
    private RandomService randomService1;
    private RandomService randomService2;
    private RandomService randomService3;

    @BeforeEach
    public void setUp() {
        randomService1 = new RandomService();
        randomService2 = new RandomService(new Random(12345L));
        randomService3 = new RandomService(null);
    }

    @Test
    public void testEqualsSameInstance() {
        assertTrue(randomService1.equals(randomService1));
    }

    @Test
    public void testEqualsDifferentInstancesSameRandom() {
        RandomService anotherRandomService = new RandomService(randomService1.getRandomInternal());
        assertTrue(randomService1.equals(anotherRandomService));
    }

    @Test
    public void testEqualsDifferentInstancesDifferentRandom() {
        assertFalse(randomService1.equals(randomService2));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(randomService1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(randomService1.equals("Not a RandomService"));
    }

    @Test
    public void testEqualsWithNullRandom() {
        assertTrue(randomService3.equals(new RandomService(null)));
    }
}
