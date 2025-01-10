
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedSet_removeIfTest {

    private ListOrderedSet<Integer> listOrderedSet;

    @BeforeEach
    public void setUp() {
        listOrderedSet = new ListOrderedSet<>();
        listOrderedSet.add(1);
        listOrderedSet.add(2);
        listOrderedSet.add(3);
        listOrderedSet.add(4);
        listOrderedSet.add(5);
    }

    @Test
    public void testRemoveIf_NullFilter() {
        assertFalse(listOrderedSet.removeIf(null));
        assertEquals(5, listOrderedSet.size());
    }

    @Test
    public void testRemoveIf_EvenNumbers() {
        Predicate<Integer> evenFilter = num -> num % 2 == 0;
        assertTrue(listOrderedSet.removeIf(evenFilter));
        assertEquals(3, listOrderedSet.size());
        assertFalse(listOrderedSet.contains(2));
        assertFalse(listOrderedSet.contains(4));
    }

    @Test
    public void testRemoveIf_NoMatches() {
        Predicate<Integer> oddFilter = num -> num % 2 != 0;
        assertFalse(listOrderedSet.removeIf(oddFilter));
        assertEquals(5, listOrderedSet.size());
    }

    @Test
    public void testRemoveIf_AllElements() {
        Predicate<Integer> allFilter = num -> true;
        assertTrue(listOrderedSet.removeIf(allFilter));
        assertTrue(listOrderedSet.isEmpty());
    }

    @Test
    public void testRemoveIf_OrderPreserved() {
        Predicate<Integer> greaterThanThree = num -> num > 3;
        assertTrue(listOrderedSet.removeIf(greaterThanThree));
        assertEquals(3, listOrderedSet.size());
        List<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(1);
        expectedOrder.add(2);
        expectedOrder.add(3);
        assertEquals(expectedOrder, listOrderedSet.asList());
    }
}
