
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedOrderComparator_addTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    public void setUp() {
        comparator = new FixedOrderComparator<>();
    }

    @Test
    public void testAddNewObject() {
        assertTrue(comparator.add("Object1"));
        assertFalse(comparator.add("Object1")); // Adding the same object again
    }

    @Test
    public void testAddAfterLock() {
        comparator.add("Object1");
        comparator.compare("Object1", "Object1"); // Lock the comparator
        assertThrows(UnsupportedOperationException.class, () -> comparator.add("Object2"));
    }

    @Test
    public void testAddAsEqual() {
        comparator.add("Object1");
        assertTrue(comparator.addAsEqual("Object1", "Object2"));
        assertEquals(0, comparator.compare("Object1", "Object2"));
    }

    @Test
    public void testCompareAfterAdd() {
        comparator.add("Object1");
        comparator.add("Object2");
        assertTrue(comparator.compare("Object1", "Object2") < 0);
        assertTrue(comparator.compare("Object2", "Object1") > 0);
    }

    @Test
    public void testEqualsAfterAdd() {
        FixedOrderComparator<String> comparator2 = new FixedOrderComparator<>();
        comparator.add("Object1");
        comparator2.add("Object1");
        assertEquals(comparator, comparator2);
    }
}
