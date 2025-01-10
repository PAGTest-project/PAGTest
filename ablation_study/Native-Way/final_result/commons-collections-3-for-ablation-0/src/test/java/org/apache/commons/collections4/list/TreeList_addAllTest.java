
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeList_addAllTest {
    private TreeList<String> treeList;

    @BeforeEach
    public void setUp() {
        treeList = new TreeList<>();
    }

    @Test
    public void testAddAllEmptyCollection() {
        Collection<String> emptyCollection = new ArrayList<>();
        assertFalse(treeList.addAll(emptyCollection));
        assertEquals(0, treeList.size());
    }

    @Test
    public void testAddAllNonEmptyCollection() {
        Collection<String> nonEmptyCollection = Arrays.asList("a", "b", "c");
        assertTrue(treeList.addAll(nonEmptyCollection));
        assertEquals(3, treeList.size());
        assertEquals("a", treeList.get(0));
        assertEquals("b", treeList.get(1));
        assertEquals("c", treeList.get(2));
    }

    @Test
    public void testAddAllToNonEmptyList() {
        treeList.add("x");
        Collection<String> collection = Arrays.asList("a", "b", "c");
        assertTrue(treeList.addAll(collection));
        assertEquals(4, treeList.size());
        assertEquals("x", treeList.get(0));
        assertEquals("a", treeList.get(1));
        assertEquals("b", treeList.get(2));
        assertEquals("c", treeList.get(3));
    }
}
