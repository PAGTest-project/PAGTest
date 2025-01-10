
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BooleanComparator_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        BooleanComparator comparator = new BooleanComparator(true);
        assertTrue(comparator.equals(comparator));
    }

    @Test
    public void testEquals_DifferentInstanceSameState() {
        BooleanComparator comparator1 = new BooleanComparator(true);
        BooleanComparator comparator2 = new BooleanComparator(true);
        assertTrue(comparator1.equals(comparator2));
    }

    @Test
    public void testEquals_DifferentInstanceDifferentState() {
        BooleanComparator comparator1 = new BooleanComparator(true);
        BooleanComparator comparator2 = new BooleanComparator(false);
        assertFalse(comparator1.equals(comparator2));
    }

    @Test
    public void testEquals_DifferentClass() {
        BooleanComparator comparator = new BooleanComparator(true);
        Object obj = new Object();
        assertFalse(comparator.equals(obj));
    }
}
