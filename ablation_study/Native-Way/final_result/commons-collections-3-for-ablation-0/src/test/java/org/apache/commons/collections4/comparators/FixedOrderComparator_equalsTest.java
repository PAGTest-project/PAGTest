
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class FixedOrderComparator_equalsTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    public void setUp() {
        List<String> items = Arrays.asList("New York", "Los Angeles", "Chicago");
        comparator = new FixedOrderComparator<>(items);
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
    public void testEqualsDifferentMap() {
        FixedOrderComparator<String> other = new FixedOrderComparator<>(Arrays.asList("New York", "Chicago", "Los Angeles"));
        assertFalse(comparator.equals(other));
    }

    @Test
    public void testEqualsDifferentCounter() {
        FixedOrderComparator<String> other = new FixedOrderComparator<>(Arrays.asList("New York", "Los Angeles", "Chicago"));
        other.add("Houston");
        assertFalse(comparator.equals(other));
    }

    @Test
    public void testEqualsDifferentIsLocked() {
        FixedOrderComparator<String> other = new FixedOrderComparator<>(Arrays.asList("New York", "Los Angeles", "Chicago"));
        other.compare("New York", "Los Angeles");
        assertFalse(comparator.equals(other));
    }

    @Test
    public void testEqualsDifferentUnknownObjectBehavior() {
        FixedOrderComparator<String> other = new FixedOrderComparator<>(Arrays.asList("New York", "Los Angeles", "Chicago"));
        other.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        assertFalse(comparator.equals(other));
    }

    @Test
    public void testEqualsIdentical() {
        FixedOrderComparator<String> other = new FixedOrderComparator<>(Arrays.asList("New York", "Los Angeles", "Chicago"));
        assertTrue(comparator.equals(other));
    }
}
