
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
    public void testEqualsDifferentState() {
        FixedOrderComparator<String> otherComparator = new FixedOrderComparator<>(Arrays.asList("New York", "Los Angeles", "Chicago"));
        otherComparator.add("Houston");
        assertFalse(comparator.equals(otherComparator));
    }

    @Test
    public void testEqualsSameState() {
        FixedOrderComparator<String> otherComparator = new FixedOrderComparator<>(Arrays.asList("New York", "Los Angeles", "Chicago"));
        assertTrue(comparator.equals(otherComparator));
    }

    @Test
    public void testEqualsDifferentLockState() {
        FixedOrderComparator<String> otherComparator = new FixedOrderComparator<>(Arrays.asList("New York", "Los Angeles", "Chicago"));
        otherComparator.compare("New York", "Los Angeles"); // Lock the comparator
        assertFalse(comparator.equals(otherComparator));
    }

    @Test
    public void testEqualsDifferentUnknownObjectBehavior() {
        FixedOrderComparator<String> otherComparator = new FixedOrderComparator<>(Arrays.asList("New York", "Los Angeles", "Chicago"));
        otherComparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        assertFalse(comparator.equals(otherComparator));
    }
}
