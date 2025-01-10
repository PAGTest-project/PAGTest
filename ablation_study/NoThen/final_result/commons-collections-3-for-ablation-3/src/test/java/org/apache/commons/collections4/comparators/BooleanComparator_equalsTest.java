
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
    public void testEqualsWithDifferentInstanceSameOrder() {
        BooleanComparator trueFirst1 = BooleanComparator.getTrueFirstComparator();
        BooleanComparator trueFirst2 = BooleanComparator.booleanComparator(true);
        assertTrue(trueFirst1.equals(trueFirst2));
    }

    @Test
    public void testEqualsWithDifferentInstanceDifferentOrder() {
        BooleanComparator trueFirst = BooleanComparator.getTrueFirstComparator();
        BooleanComparator falseFirst = BooleanComparator.getFalseFirstComparator();
        assertFalse(trueFirst.equals(falseFirst));
    }

    @Test
    public void testEqualsWithNonBooleanComparatorInstance() {
        BooleanComparator trueFirst = BooleanComparator.getTrueFirstComparator();
        Object nonComparator = new Object();
        assertFalse(trueFirst.equals(nonComparator));
    }

    @Test
    public void testEqualsWithNull() {
        BooleanComparator trueFirst = BooleanComparator.getTrueFirstComparator();
        assertFalse(trueFirst.equals(null));
    }
}
