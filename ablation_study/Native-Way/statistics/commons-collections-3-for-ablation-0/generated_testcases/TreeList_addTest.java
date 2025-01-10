
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeList_addTest {
    private TreeList<String> treeList;

    @BeforeEach
    public void setUp() {
        treeList = new TreeList<>();
    }

    @Test
    public void testAddAtIndex() {
        treeList.add(0, "hugo");
        assertEquals(1, treeList.size());
        assertEquals("hugo", treeList.get(0));

        treeList.add(0, "erna");
        assertEquals(2, treeList.size());
        assertEquals("erna", treeList.get(0));
        assertEquals("hugo", treeList.get(1));

        treeList.add(1, "daniel");
        assertEquals(3, treeList.size());
        assertEquals("erna", treeList.get(0));
        assertEquals("daniel", treeList.get(1));
        assertEquals("hugo", treeList.get(2));

        treeList.add(3, "andres");
        assertEquals(4, treeList.size());
        assertEquals("erna", treeList.get(0));
        assertEquals("daniel", treeList.get(1));
        assertEquals("hugo", treeList.get(2));
        assertEquals("andres", treeList.get(3));

        treeList.add(0, "harald");
        assertEquals(5, treeList.size());
        assertEquals("harald", treeList.get(0));
        assertEquals("erna", treeList.get(1));
        assertEquals("daniel", treeList.get(2));
        assertEquals("hugo", treeList.get(3));
        assertEquals("andres", treeList.get(4));
    }

    @Test
    public void testAddAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> treeList.add(-1, "hugo"));
        assertThrows(IndexOutOfBoundsException.class, () -> treeList.add(1, "hugo"));
    }

    @Test
    public void testAddAtIndexWithEmptyList() {
        treeList.add(0, "hugo");
        assertEquals(1, treeList.size());
        assertEquals("hugo", treeList.get(0));
    }

    @Test
    public void testAddAtIndexWithNull() {
        treeList.add(0, null);
        assertEquals(1, treeList.size());
        assertNull(treeList.get(0));
    }
}
