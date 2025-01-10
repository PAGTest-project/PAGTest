
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeList_iteratorTest {

    private TreeList<Integer> treeList;

    @BeforeEach
    public void setUp() {
        treeList = new TreeList<>();
        treeList.add(0);
        treeList.add(1);
        treeList.add(2);
        treeList.add(3);
        treeList.add(4);
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = treeList.iterator();
        assertNotNull(iterator);
        for (int i = 0; i < treeList.size(); i++) {
            assertEquals(Integer.valueOf(i), iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNoSuchElementException() {
        Iterator<Integer> iterator = treeList.iterator();
        for (int i = 0; i < treeList.size(); i++) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testIteratorAfterRemove() {
        treeList.remove(2);
        Iterator<Integer> iterator = treeList.iterator();
        assertNotNull(iterator);
        assertEquals(Integer.valueOf(0), iterator.next());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertEquals(Integer.valueOf(4), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorEmptyList() {
        treeList.clear();
        Iterator<Integer> iterator = treeList.iterator();
        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
    }
}
