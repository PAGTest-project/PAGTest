
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BooleanComparator_equalsTest {

    @Test
    public void testEqualsWithSameInstance() {
        BooleanComparator comparator = BooleanComparator.getTrueFirstComparator();
        assertTrue(comparator.equals(comparator));
    }

    @Test
    public void testEqualsWithDifferentInstanceSameTrueFirst() {
        BooleanComparator comparator1 = BooleanComparator.getTrueFirstComparator();
        BooleanComparator comparator2 = BooleanComparator.booleanComparator(true);
        assertTrue(comparator1.equals(comparator2));
    }

    @Test
    public void testEqualsWithDifferentInstanceDifferentTrueFirst() {
        BooleanComparator comparator1 = BooleanComparator.getTrueFirstComparator();
        BooleanComparator comparator2 = BooleanComparator.getFalseFirstComparator();
        assertFalse(comparator1.equals(comparator2));
    }

    @Test
    public void testEqualsWithNonBooleanComparatorInstance() {
        BooleanComparator comparator = BooleanComparator.getTrueFirstComparator();
        assertFalse(comparator.equals("Not a BooleanComparator"));
    }

    @Test
    public void testEqualsWithNull() {
        BooleanComparator comparator = BooleanComparator.getTrueFirstComparator();
        assertFalse(comparator.equals(null));
    }
}
