
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedOrderComparator_compareTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    public void setUp() {
        comparator = new FixedOrderComparator<>();
        comparator.add("apple");
        comparator.add("banana");
        comparator.add("cherry");
    }

    @Test
    public void testCompareKnownObjects() {
        assertEquals(-1, comparator.compare("apple", "banana"));
        assertEquals(0, comparator.compare("apple", "apple"));
        assertEquals(1, comparator.compare("banana", "apple"));
    }

    @Test
    public void testCompareUnknownObjectBefore() {
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        assertEquals(-1, comparator.compare("unknown", "apple"));
        assertEquals(1, comparator.compare("apple", "unknown"));
        assertEquals(0, comparator.compare("unknown", "unknown"));
    }

    @Test
    public void testCompareUnknownObjectAfter() {
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.AFTER);
        assertEquals(1, comparator.compare("unknown", "apple"));
        assertEquals(-1, comparator.compare("apple", "unknown"));
        assertEquals(0, comparator.compare("unknown", "unknown"));
    }

    @Test
    public void testCompareUnknownObjectException() {
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.EXCEPTION);
        assertThrows(IllegalArgumentException.class, () -> comparator.compare("apple", "unknown"));
        assertThrows(IllegalArgumentException.class, () -> comparator.compare("unknown", "apple"));
    }

    @Test
    public void testCompareUnknownObjectBehaviorNull() {
        comparator.setUnknownObjectBehavior(null);
        assertThrows(UnsupportedOperationException.class, () -> comparator.compare("apple", "unknown"));
    }

    @Test
    public void testCompareAfterLock() {
        comparator.compare("apple", "banana");
        assertThrows(UnsupportedOperationException.class, () -> comparator.add("date"));
    }
}
