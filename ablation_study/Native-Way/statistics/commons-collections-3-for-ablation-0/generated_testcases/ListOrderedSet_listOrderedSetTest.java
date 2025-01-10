
package org.apache.commons.collections4.set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOrderedSet_listOrderedSetTest {

    private List<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    public void testListOrderedSetWithUniqueElements() {
        list.add(1);
        list.add(2);
        list.add(3);

        ListOrderedSet<Integer> orderedSet = ListOrderedSet.listOrderedSet(list);

        assertEquals(3, orderedSet.size());
        assertEquals(Integer.valueOf(1), orderedSet.get(0));
        assertEquals(Integer.valueOf(2), orderedSet.get(1));
        assertEquals(Integer.valueOf(3), orderedSet.get(2));
    }

    @Test
    public void testListOrderedSetWithDuplicateElements() {
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);

        ListOrderedSet<Integer> orderedSet = ListOrderedSet.listOrderedSet(list);

        assertEquals(3, orderedSet.size());
        assertEquals(Integer.valueOf(1), orderedSet.get(0));
        assertEquals(Integer.valueOf(2), orderedSet.get(1));
        assertEquals(Integer.valueOf(3), orderedSet.get(2));
    }

    @Test
    public void testListOrderedSetWithNullList() {
        list = null;

        assertThrows(NullPointerException.class, () -> {
            ListOrderedSet.listOrderedSet(list);
        });
    }

    @Test
    public void testListOrderedSetWithEmptyList() {
        ListOrderedSet<Integer> orderedSet = ListOrderedSet.listOrderedSet(list);

        assertEquals(0, orderedSet.size());
    }
}
