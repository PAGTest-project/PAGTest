
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FixedOrderComparator_addTest {

    @Test
    void testAddNewObject() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>();
        assertFalse(comparator.isLocked());
        assertTrue(comparator.add("newObject"));
    }

    @Test
    void testAddExistingObject() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>();
        comparator.add("existingObject");
        assertFalse(comparator.add("existingObject"));
    }

    @Test
    void testAddWhenLocked() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>();
        comparator.add("obj1"); // Add the object to avoid IllegalArgumentException
        comparator.add("obj2"); // Add the object to avoid IllegalArgumentException
        comparator.compare("obj1", "obj2"); // Lock the comparator
        assertThrows(UnsupportedOperationException.class, () -> comparator.add("newObject"));
    }
}
