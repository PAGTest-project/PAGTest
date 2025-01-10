
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedSet_removeTest {

    private ListOrderedSet<String> listOrderedSet;

    @BeforeEach
    public void setUp() {
        listOrderedSet = new ListOrderedSet<>();
    }

    @Test
    public void testRemoveAtIndex() {
        listOrderedSet.add("A");
        listOrderedSet.add("B");
        listOrderedSet.add("C");

        String removed = listOrderedSet.remove(1);
        assertEquals("B", removed);
        assertEquals(2, listOrderedSet.size());
        assertEquals("A", listOrderedSet.get(0));
        assertEquals("C", listOrderedSet.get(1));
    }

    @Test
    public void testRemoveAtIndexEmptySet() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            listOrderedSet.remove(0);
        });
    }

    @Test
    public void testRemoveAtIndexOutOfBounds() {
        listOrderedSet.add("A");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            listOrderedSet.remove(1);
        });
    }

    @Test
    public void testRemoveAtIndexMultipleRemovals() {
        listOrderedSet.add("A");
        listOrderedSet.add("B");
        listOrderedSet.add("C");
        listOrderedSet.add("D");

        String removed1 = listOrderedSet.remove(1);
        assertEquals("B", removed1);
        assertEquals(3, listOrderedSet.size());
        assertEquals("A", listOrderedSet.get(0));
        assertEquals("C", listOrderedSet.get(1));
        assertEquals("D", listOrderedSet.get(2));

        String removed2 = listOrderedSet.remove(1);
        assertEquals("C", removed2);
        assertEquals(2, listOrderedSet.size());
        assertEquals("A", listOrderedSet.get(0));
        assertEquals("D", listOrderedSet.get(1));
    }

    @Test
    public void testRemoveAtIndexWithDuplicates() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("B");
        listOrderedSet = ListOrderedSet.listOrderedSet(list);

        String removed = listOrderedSet.remove(1);
        assertEquals("B", removed);
        assertEquals(3, listOrderedSet.size());
        assertEquals("A", listOrderedSet.get(0));
        assertEquals("C", listOrderedSet.get(1));
        assertEquals("B", listOrderedSet.get(2));
    }
}
