
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeList_removeTest {
    private TreeList<String> treeList;

    @BeforeEach
    public void setUp() {
        treeList = new TreeList<>();
        treeList.add("A");
        treeList.add("B");
        treeList.add("C");
    }

    @Test
    public void testRemoveMiddleElement() {
        String removed = treeList.remove(1);
        assertEquals("B", removed);
        assertEquals(2, treeList.size());
        assertEquals("A", treeList.get(0));
        assertEquals("C", treeList.get(1));
    }

    @Test
    public void testRemoveFirstElement() {
        String removed = treeList.remove(0);
        assertEquals("A", removed);
        assertEquals(2, treeList.size());
        assertEquals("B", treeList.get(0));
        assertEquals("C", treeList.get(1));
    }

    @Test
    public void testRemoveLastElement() {
        String removed = treeList.remove(2);
        assertEquals("C", removed);
        assertEquals(2, treeList.size());
        assertEquals("A", treeList.get(0));
        assertEquals("B", treeList.get(1));
    }

    @Test
    public void testRemoveOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> treeList.remove(3));
        assertThrows(IndexOutOfBoundsException.class, () -> treeList.remove(-1));
    }

    @Test
    public void testRemoveFromEmptyList() {
        TreeList<String> emptyList = new TreeList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.remove(0));
    }
}
