
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedOrderComparator_compareTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    public void setUp() {
        comparator = new FixedOrderComparator<>();
    }

    @Test
    public void testCompareKnownObjects() {
        comparator.add("apple");
        comparator.add("banana");
        comparator.add("cherry");

        assertEquals(-1, comparator.compare("apple", "banana"));
        assertEquals(1, comparator.compare("banana", "apple"));
        assertEquals(0, comparator.compare("apple", "apple"));
    }

    @Test
    public void testCompareUnknownObjectsBefore() {
        comparator.add("apple");
        comparator.add("banana");
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);

        assertEquals(-1, comparator.compare("unknown", "apple"));
        assertEquals(1, comparator.compare("apple", "unknown"));
        assertEquals(0, comparator.compare("unknown1", "unknown2"));
    }

    @Test
    public void testCompareUnknownObjectsAfter() {
        comparator.add("apple");
        comparator.add("banana");
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.AFTER);

        assertEquals(1, comparator.compare("unknown", "apple"));
        assertEquals(-1, comparator.compare("apple", "unknown"));
        assertEquals(0, comparator.compare("unknown1", "unknown2"));
    }

    @Test
    public void testCompareUnknownObjectsException() {
        comparator.add("apple");
        comparator.add("banana");

        assertThrows(IllegalArgumentException.class, () -> comparator.compare("unknown", "apple"));
        assertThrows(IllegalArgumentException.class, () -> comparator.compare("apple", "unknown"));
    }

    @Test
    public void testCompareAfterLock() {
        comparator.add("apple");
        comparator.add("banana");
        comparator.compare("apple", "banana"); // This will lock the comparator

        assertThrows(UnsupportedOperationException.class, () -> comparator.add("cherry"));
    }

    @Test
    public void testAddAsEqual() {
        comparator.add("apple");
        comparator.add("banana");
        comparator.addAsEqual("apple", "apricot");

        assertEquals(0, comparator.compare("apple", "apricot"));
        assertEquals(-1, comparator.compare("apple", "banana"));
    }

    @Test
    public void testAddAsEqualUnknownObject() {
        comparator.add("apple");

        assertThrows(IllegalArgumentException.class, () -> comparator.addAsEqual("unknown", "apricot"));
    }

    @Test
    public void testEquals() {
        FixedOrderComparator<String> comparator1 = new FixedOrderComparator<>();
        FixedOrderComparator<String> comparator2 = new FixedOrderComparator<>();

        comparator1.add("apple");
        comparator1.add("banana");
        comparator2.add("apple");
        comparator2.add("banana");

        assertTrue(comparator1.equals(comparator2));
        comparator2.add("cherry");
        assertFalse(comparator1.equals(comparator2));
    }

    @Test
    public void testHashCode() {
        FixedOrderComparator<String> comparator1 = new FixedOrderComparator<>();
        FixedOrderComparator<String> comparator2 = new FixedOrderComparator<>();

        comparator1.add("apple");
        comparator1.add("banana");
        comparator2.add("apple");
        comparator2.add("banana");

        assertEquals(comparator1.hashCode(), comparator2.hashCode());
        comparator2.add("cherry");
        assertNotEquals(comparator1.hashCode(), comparator2.hashCode());
    }
}
