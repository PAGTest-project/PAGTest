
package org.apache.commons.collections4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_getTest {

    private List<String> list;
    private Iterator<String> iterator;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        iterator = list.iterator();
    }

    @Test
    public void testGetElementAtIndex() {
        assertEquals("A", IteratorUtils.get(iterator, 0));
        iterator = list.iterator(); // Reset iterator
        assertEquals("B", IteratorUtils.get(iterator, 1));
        iterator = list.iterator(); // Reset iterator
        assertEquals("C", IteratorUtils.get(iterator, 2));
    }

    @Test
    public void testGetElementWithNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> IteratorUtils.get(iterator, -1));
    }

    @Test
    public void testGetElementWithIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> IteratorUtils.get(iterator, 3));
    }

    @Test
    public void testGetElementFromEmptyIterator() {
        Iterator<String> emptyIterator = new ArrayList<String>().iterator();
        assertThrows(IndexOutOfBoundsException.class, () -> IteratorUtils.get(emptyIterator, 0));
    }
}
