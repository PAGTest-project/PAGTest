
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FixedOrderComparator_addTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    public void setUp() {
        comparator = new FixedOrderComparator<>();
    }

    @Test
    public void testAddNewObject() {
        assertTrue(comparator.add("New York"));
        assertFalse(comparator.add("New York"));
    }

    @Test
    public void testAddObjectAfterLock() {
        comparator.add("New York");
        comparator.compare("New York", "New York"); // Lock the comparator
        assertThrows(UnsupportedOperationException.class, () -> comparator.add("Los Angeles"));
    }

    @Test
    public void testAddNullObject() {
        assertThrows(NullPointerException.class, () -> comparator.add(null));
    }

    @Test
    public void testAddMultipleObjects() {
        assertTrue(comparator.add("New York"));
        assertTrue(comparator.add("Los Angeles"));
        assertTrue(comparator.add("Chicago"));
        assertFalse(comparator.add("New York"));
        assertFalse(comparator.add("Los Angeles"));
        assertFalse(comparator.add("Chicago"));
    }

    @Test
    public void testAddObjectWithUnknownBehavior() {
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        assertTrue(comparator.add("New York"));
        comparator.compare("New York", "New York"); // Lock the comparator
        assertThrows(UnsupportedOperationException.class, () -> comparator.add("Los Angeles"));
    }
}
