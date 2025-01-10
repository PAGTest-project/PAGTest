
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class RandomService_equalsTest {

    @Test
    void testEquals() {
        Random random1 = new Random(12345L);
        Random random2 = new Random(12345L);
        RandomService service1 = new RandomService(random1);
        RandomService service2 = new RandomService(random2);
        RandomService service3 = new RandomService(new Random(54321L));

        // Test case 1: Same object reference
        assertTrue(service1.equals(service1));

        // Test case 2: Different objects with same random state
        assertTrue(service1.equals(service2));

        // Test case 3: Different objects with different random state
        assertFalse(service1.equals(service3));

        // Test case 4: Null comparison
        assertFalse(service1.equals(null));

        // Test case 5: Different class
        assertFalse(service1.equals("Not a RandomService"));

        // Consistency check with hashCode
        assertEquals(service1.hashCode(), service2.hashCode());
        assertNotEquals(service1.hashCode(), service3.hashCode());

        // State representation check with toString
        assertEquals(service1.toString(), service2.toString());
        assertNotEquals(service1.toString(), service3.toString());
    }
}
