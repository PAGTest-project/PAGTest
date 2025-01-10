
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeList_setTest {
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
    public void testSetValidIndex() {
        String oldValue = treeList.set(2, "newDaniel");
        assertEquals("daniel", oldValue);
        assertEquals("newDaniel", treeList.get(2));
    }

    @Test
    public void testSetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            treeList.set(5, "invalid");
        });
    }

    @Test
    public void testSetFirstIndex() {
        String oldValue = treeList.set(0, "newHugo");
        assertEquals("hugo", oldValue);
        assertEquals("newHugo", treeList.get(0));
    }

    @Test
    public void testSetLastIndex() {
        String oldValue = treeList.set(4, "newHarald");
        assertEquals("harald", oldValue);
        assertEquals("newHarald", treeList.get(4));
    }

    @Test
    public void testSetEmptyList() {
        TreeList<String> emptyList = new TreeList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyList.set(0, "empty");
        });
    }
}
