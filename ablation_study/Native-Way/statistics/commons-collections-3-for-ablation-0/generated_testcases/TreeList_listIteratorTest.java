
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ListIterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeList_listIteratorTest {
    private TreeList<String> treeList;

    @BeforeEach
    public void setUp() {
        treeList = new TreeList<>();
        treeList.add("hugo");
        treeList.add("erna");
        treeList.add("daniel");
        treeList.add("andres");
        treeList.add("harald");
    }

    @Test
    public void testListIterator() {
        ListIterator<String> iterator = treeList.listIterator();
        assertTrue(iterator.hasNext());
        assertEquals("hugo", iterator.next());
        assertEquals("erna", iterator.next());
        assertEquals("daniel", iterator.next());
        assertEquals("andres", iterator.next());
        assertEquals("harald", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testListIteratorWithIndex() {
        ListIterator<String> iterator = treeList.listIterator(2);
        assertTrue(iterator.hasNext());
        assertEquals("daniel", iterator.next());
        assertEquals("andres", iterator.next());
        assertEquals("harald", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testListIteratorPrevious() {
        ListIterator<String> iterator = treeList.listIterator(5);
        assertTrue(iterator.hasPrevious());
        assertEquals("harald", iterator.previous());
        assertEquals("andres", iterator.previous());
        assertEquals("daniel", iterator.previous());
        assertEquals("erna", iterator.previous());
        assertEquals("hugo", iterator.previous());
        assertFalse(iterator.hasPrevious());
    }

    @Test
    public void testListIteratorAdd() {
        ListIterator<String> iterator = treeList.listIterator();
        iterator.next();
        iterator.add("inserted");
        assertEquals(6, treeList.size());
        assertEquals("inserted", treeList.get(1));
    }

    @Test
    public void testListIteratorRemove() {
        ListIterator<String> iterator = treeList.listIterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals(4, treeList.size());
        assertEquals("daniel", treeList.get(1));
    }

    @Test
    public void testListIteratorSet() {
        ListIterator<String> iterator = treeList.listIterator();
        iterator.next();
        iterator.set("updated");
        assertEquals("updated", treeList.get(0));
    }
}
