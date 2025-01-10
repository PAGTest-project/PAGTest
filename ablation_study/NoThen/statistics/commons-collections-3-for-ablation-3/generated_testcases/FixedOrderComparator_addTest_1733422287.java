
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedOrderComparator_addTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    void setUp() {
        comparator = new FixedOrderComparator<>();
    }

    @Test
    void testAddNewObject() {
        assertFalse(comparator.isLocked());
        assertTrue(comparator.add("newObject"));
        assertFalse(comparator.add("newObject"));
    }

    @Test
    void testAddWhenLocked() {
        comparator.compare("obj1", "obj2"); // Lock the comparator
        assertThrows(UnsupportedOperationException.class, () -> comparator.add("newObject"));
    }
}
