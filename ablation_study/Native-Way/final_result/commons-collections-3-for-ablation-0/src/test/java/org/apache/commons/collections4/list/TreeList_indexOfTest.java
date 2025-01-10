
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeList_indexOfTest {
    private TreeList<String> treeList;

    @BeforeEach
    public void setUp() {
        treeList = new TreeList<>();
    }

    @Test
    public void testIndexOfWithEmptyList() {
        assertEquals(-1, treeList.indexOf("element"));
    }

    @Test
    public void testIndexOfWithElementPresent() {
        treeList.add("element1");
        treeList.add("element2");
        treeList.add("element3");
        assertEquals(1, treeList.indexOf("element2"));
    }

    @Test
    public void testIndexOfWithElementNotPresent() {
        treeList.add("element1");
        treeList.add("element2");
        treeList.add("element3");
        assertEquals(-1, treeList.indexOf("element4"));
    }

    @Test
    public void testIndexOfWithNullElement() {
        treeList.add(null);
        treeList.add("element2");
        treeList.add("element3");
        assertNull(treeList.get(0));
        assertEquals(0, treeList.indexOf(null));
    }

    @Test
    public void testIndexOfWithMultipleNullElements() {
        treeList.add(null);
        treeList.add("element2");
        treeList.add(null);
        assertEquals(0, treeList.indexOf(null));
    }
}
