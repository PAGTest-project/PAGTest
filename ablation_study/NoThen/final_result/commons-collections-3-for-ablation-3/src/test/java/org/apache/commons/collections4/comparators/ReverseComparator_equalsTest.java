
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ReverseComparator_equalsTest {

    @Test
    void testEquals() {
        Comparator<Integer> naturalOrder = Comparator.naturalOrder();
        ReverseComparator<Integer> rc1 = new ReverseComparator<>(naturalOrder);
        ReverseComparator<Integer> rc2 = new ReverseComparator<>(naturalOrder);
        ReverseComparator<Integer> rc3 = new ReverseComparator<>(Comparator.reverseOrder());

        // Test same instance
        assertTrue(rc1.equals(rc1));

        // Test null
        assertFalse(rc1.equals(null));

        // Test different class
        assertFalse(rc1.equals("Not a ReverseComparator"));

        // Test same class, same comparator
        assertTrue(rc1.equals(rc2));

        // Test same class, different comparator
        assertFalse(rc1.equals(rc3));
    }
}
