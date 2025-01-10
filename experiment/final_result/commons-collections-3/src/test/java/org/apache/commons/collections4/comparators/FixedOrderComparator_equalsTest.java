
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedOrderComparator_equalsTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    public void setUp() {
        comparator = new FixedOrderComparator<>(new String[]{"New York", "Los Angeles", "Chicago"});
    }

    @Test
    public void testEqualsReflexive() {
        assertTrue(comparator.equals(comparator));
    }

    @Test
    public void testEqualsSymmetric() {
        FixedOrderComparator<String> comparator2 = new FixedOrderComparator<>(new String[]{"New York", "Los Angeles", "Chicago"});
        assertTrue(comparator.equals(comparator2));
        assertTrue(comparator2.equals(comparator));
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
    public void testEqualsDifferentState() {
        FixedOrderComparator<String> comparator2 = new FixedOrderComparator<>(new String[]{"New York", "Los Angeles", "Chicago"});
        comparator2.add("Houston");
        assertFalse(comparator.equals(comparator2));
    }

    @Test
    public void testEqualsDifferentLockState() {
        FixedOrderComparator<String> comparator2 = new FixedOrderComparator<>(new String[]{"New York", "Los Angeles", "Chicago"});
        comparator.compare("New York", "Los Angeles");
        assertFalse(comparator.equals(comparator2));
    }

    @Test
    public void testEqualsDifferentUnknownObjectBehavior() {
        FixedOrderComparator<String> comparator2 = new FixedOrderComparator<>(new String[]{"New York", "Los Angeles", "Chicago"});
        comparator2.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        assertFalse(comparator.equals(comparator2));
    }
}
