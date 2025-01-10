
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedOrderComparator_compareTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    public void setUp() {
        comparator = new FixedOrderComparator<>();
        comparator.add("New York");
        comparator.add("Los Angeles");
        comparator.add("Chicago");
    }

    @Test
    public void testCompareKnownObjects() {
        assertEquals(-1, comparator.compare("New York", "Los Angeles"));
        assertEquals(1, comparator.compare("Los Angeles", "New York"));
        assertEquals(0, comparator.compare("New York", "New York"));
    }

    @Test
    public void testCompareUnknownObjectBehaviorException() {
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.EXCEPTION);
        assertThrows(IllegalArgumentException.class, () -> comparator.compare("Unknown", "New York"));
        assertThrows(IllegalArgumentException.class, () -> comparator.compare("New York", "Unknown"));
    }

    @Test
    public void testCompareUnknownObjectBehaviorBefore() {
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        assertEquals(-1, comparator.compare("Unknown", "New York"));
        assertEquals(1, comparator.compare("New York", "Unknown"));
        assertEquals(0, comparator.compare("Unknown", "Unknown"));
    }

    @Test
    public void testCompareUnknownObjectBehaviorAfter() {
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.AFTER);
        assertEquals(1, comparator.compare("Unknown", "New York"));
        assertEquals(-1, comparator.compare("New York", "Unknown"));
        assertEquals(0, comparator.compare("Unknown", "Unknown"));
    }
}
