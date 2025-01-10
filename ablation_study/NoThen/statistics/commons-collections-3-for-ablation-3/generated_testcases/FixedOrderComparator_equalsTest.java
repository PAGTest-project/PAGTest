
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedOrderComparator_equalsTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    public void setUp() {
        comparator = new FixedOrderComparator<>();
    }

    @Test
    public void testEqualsSameInstance() {
        assertTrue(comparator.equals(comparator));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(comparator.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(comparator.equals(new Object()));
    }

    @Test
    public void testEqualsDifferentCounter() {
        FixedOrderComparator<String> other = new FixedOrderComparator<>();
        comparator.add("A");
        assertFalse(comparator.equals(other));
    }

    @Test
    public void testEqualsDifferentIsLocked() {
        FixedOrderComparator<String> other = new FixedOrderComparator<>();
        comparator.add("A");
        comparator.compare("A", "A");
        assertFalse(comparator.equals(other));
    }

    @Test
    public void testEqualsDifferentMap() {
        FixedOrderComparator<String> other = new FixedOrderComparator<>();
        comparator.add("A");
        other.add("B");
        assertFalse(comparator.equals(other));
    }

    @Test
    public void testEqualsDifferentUnknownObjectBehavior() {
        FixedOrderComparator<String> other = new FixedOrderComparator<>();
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        assertFalse(comparator.equals(other));
    }

    @Test
    public void testEqualsIdentical() {
        FixedOrderComparator<String> other = new FixedOrderComparator<>();
        comparator.add("A");
        other.add("A");
        assertTrue(comparator.equals(other));
    }
}
