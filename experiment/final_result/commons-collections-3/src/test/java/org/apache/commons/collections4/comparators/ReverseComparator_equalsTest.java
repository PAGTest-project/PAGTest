
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class ReverseComparator_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        ReverseComparator<Integer> rc = new ReverseComparator<>(Comparator.naturalOrder());
        assertTrue(rc.equals(rc));
    }

    @Test
    public void testEquals_NullObject() {
        ReverseComparator<Integer> rc = new ReverseComparator<>(Comparator.naturalOrder());
        assertFalse(rc.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ReverseComparator<Integer> rc = new ReverseComparator<>(Comparator.naturalOrder());
        Object obj = new Object();
        assertFalse(rc.equals(obj));
    }

    @Test
    public void testEquals_SameClassDifferentComparator() {
        ReverseComparator<Integer> rc1 = new ReverseComparator<>(Comparator.naturalOrder());
        ReverseComparator<Integer> rc2 = new ReverseComparator<>(Comparator.reverseOrder());
        assertFalse(rc1.equals(rc2));
    }

    @Test
    public void testEquals_SameClassSameComparator() {
        ReverseComparator<Integer> rc1 = new ReverseComparator<>(Comparator.naturalOrder());
        ReverseComparator<Integer> rc2 = new ReverseComparator<>(Comparator.naturalOrder());
        assertTrue(rc1.equals(rc2));
    }
}
