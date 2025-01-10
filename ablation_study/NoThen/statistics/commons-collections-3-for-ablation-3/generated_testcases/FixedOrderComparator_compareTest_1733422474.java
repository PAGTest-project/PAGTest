
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.LinkedList;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedOrderComparator_compareTest {

    private FixedOrderComparator<String> comparator;
    private String[] topCities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};

    @BeforeEach
    public void setUp() {
        comparator = new FixedOrderComparator<>(topCities);
    }

    @Test
    public void testCompareKnownObjects() {
        assertEquals(-1, comparator.compare("New York", "Los Angeles"));
        assertEquals(1, comparator.compare("Los Angeles", "New York"));
        assertEquals(0, comparator.compare("New York", "New York"));
    }

    @Test
    public void testCompareUnknownObjectBehaviorException() {
        assertThrows(IllegalArgumentException.class, () -> comparator.compare("Minneapolis", "New York"));
        assertThrows(IllegalArgumentException.class, () -> comparator.compare("New York", "Minneapolis"));
    }

    @Test
    public void testCompareUnknownObjectBehaviorBefore() {
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        assertEquals(-1, comparator.compare("Minneapolis", "New York"));
        assertEquals(1, comparator.compare("New York", "Minneapolis"));
        assertEquals(0, comparator.compare("Minneapolis", "St Paul"));
    }

    @Test
    public void testCompareUnknownObjectBehaviorAfter() {
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.AFTER);
        assertEquals(1, comparator.compare("Minneapolis", "New York"));
        assertEquals(-1, comparator.compare("New York", "Minneapolis"));
        assertEquals(0, comparator.compare("Minneapolis", "St Paul"));
    }

    @Test
    public void testAddAndCompare() {
        comparator.add("Minneapolis");
        assertEquals(1, comparator.compare("Minneapolis", "New York"));
        assertEquals(-1, comparator.compare("New York", "Minneapolis"));
    }

    @Test
    public void testAddAsEqualAndCompare() {
        comparator.addAsEqual("New York", "Minneapolis");
        assertEquals(0, comparator.compare("Minneapolis", "New York"));
        assertEquals(0, comparator.compare("New York", "Minneapolis"));
    }

    @Test
    public void testIsLocked() {
        assertFalse(comparator.isLocked());
        comparator.compare("New York", "Los Angeles");
        assertTrue(comparator.isLocked());
    }

    @Test
    public void testSetUnknownObjectBehaviorLocked() {
        comparator.compare("New York", "Los Angeles");
        assertThrows(UnsupportedOperationException.class, () -> comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE));
    }
}
