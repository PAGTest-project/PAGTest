
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeList_clearTest {
    private TreeList<Integer> treeList;

    @BeforeEach
    public void setUp() {
        treeList = new TreeList<>();
    }

    @Test
    public void testClearEmptyList() {
        treeList.clear();
        assertEquals(0, treeList.size());
        assertNull(treeList.getRoot());
    }

    @Test
    public void testClearNonEmptyList() {
        treeList.add(1);
        treeList.add(2);
        treeList.add(3);

        treeList.clear();
        assertEquals(0, treeList.size());
        assertNull(treeList.getRoot());
    }

    @Test
    public void testClearAfterAddAll() {
        List<Integer> elements = new ArrayList<>();
        elements.add(1);
        elements.add(2);
        elements.add(3);

        treeList.addAll(elements);
        treeList.clear();
        assertEquals(0, treeList.size());
        assertNull(treeList.getRoot());
    }

    @Test
    public void testClearAfterRemove() {
        treeList.add(1);
        treeList.add(2);
        treeList.add(3);

        treeList.remove(1);
        treeList.clear();
        assertEquals(0, treeList.size());
        assertNull(treeList.getRoot());
    }

    @Test
    public void testClearAfterSet() {
        treeList.add(1);
        treeList.add(2);
        treeList.add(3);

        treeList.set(1, 4);
        treeList.clear();
        assertEquals(0, treeList.size());
        assertNull(treeList.getRoot());
    }
}
