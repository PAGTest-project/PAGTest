
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class RandomService_equalsTest {

    @Test
    void testEquals() {
        RandomService rs1 = new RandomService(new Random(1));
        RandomService rs2 = new RandomService(new Random(1));
        RandomService rs3 = new RandomService(new Random(2));
        RandomService rs4 = new RandomService();

        // Test same instance
        assertTrue(rs1.equals(rs1));

        // Test different instances with same random state
        assertTrue(rs1.equals(rs2));

        // Test different instances with different random state
        assertFalse(rs1.equals(rs3));

        // Test different instances with default random
        assertFalse(rs1.equals(rs4));

        // Test null
        assertFalse(rs1.equals(null));

        // Test different class
        assertFalse(rs1.equals("Not a RandomService"));
    }
}
