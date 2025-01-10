
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeList_getTest {
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
    public void testGetValidIndex() {
        assertEquals("hugo", treeList.get(0));
        assertEquals("erna", treeList.get(1));
        assertEquals("daniel", treeList.get(2));
        assertEquals("andres", treeList.get(3));
        assertEquals("harald", treeList.get(4));
    }

    @Test
    public void testGetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> treeList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> treeList.get(5));
    }

    @Test
    public void testGetAfterRemove() {
        treeList.remove(1);
        assertEquals("hugo", treeList.get(0));
        assertEquals("daniel", treeList.get(1));
        assertEquals("andres", treeList.get(2));
        assertEquals("harald", treeList.get(3));
    }

    @Test
    public void testGetAfterAdd() {
        treeList.add(2, "inserted");
        assertEquals("hugo", treeList.get(0));
        assertEquals("erna", treeList.get(1));
        assertEquals("inserted", treeList.get(2));
        assertEquals("daniel", treeList.get(3));
        assertEquals("andres", treeList.get(4));
        assertEquals("harald", treeList.get(5));
    }

    @Test
    public void testGetAfterSet() {
        treeList.set(2, "updated");
        assertEquals("hugo", treeList.get(0));
        assertEquals("erna", treeList.get(1));
        assertEquals("updated", treeList.get(2));
        assertEquals("andres", treeList.get(3));
        assertEquals("harald", treeList.get(4));
    }
}
