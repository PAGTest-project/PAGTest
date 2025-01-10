
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ReverseComparator_equalsTest {

    @Test
    void testEquals_SameInstance() {
        ReverseComparator<Integer> rc = new ReverseComparator<>(Comparator.naturalOrder());
        assertTrue(rc.equals(rc));
    }

    @Test
    void testEquals_NullObject() {
        ReverseComparator<Integer> rc = new ReverseComparator<>(Comparator.naturalOrder());
        assertFalse(rc.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        ReverseComparator<Integer> rc = new ReverseComparator<>(Comparator.naturalOrder());
        assertFalse(rc.equals("not a comparator"));
    }

    @Test
    void testEquals_SameComparator() {
        ReverseComparator<Integer> rc1 = new ReverseComparator<>(Comparator.naturalOrder());
        ReverseComparator<Integer> rc2 = new ReverseComparator<>(Comparator.naturalOrder());
        assertTrue(rc1.equals(rc2));
    }

    @Test
    void testEquals_DifferentComparator() {
        ReverseComparator<Integer> rc1 = new ReverseComparator<>(Comparator.naturalOrder());
        ReverseComparator<Integer> rc2 = new ReverseComparator<>(Comparator.reverseOrder());
        assertFalse(rc1.equals(rc2));
    }
}
