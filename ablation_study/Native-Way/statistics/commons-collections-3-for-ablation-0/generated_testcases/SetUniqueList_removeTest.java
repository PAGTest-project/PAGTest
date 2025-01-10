
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUniqueList_removeTest {

    private SetUniqueList<String> setUniqueList;

    @BeforeEach
    public void setUp() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        setUniqueList = SetUniqueList.setUniqueList(list);
    }

    @Test
    public void testRemoveValidIndex() {
        String removed = setUniqueList.remove(1);
        assertEquals("B", removed);
        assertEquals(2, setUniqueList.size());
        assertEquals("A", setUniqueList.get(0));
        assertEquals("C", setUniqueList.get(1));
    }

    @Test
    public void testRemoveInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> setUniqueList.remove(3));
    }

    @Test
    public void testRemoveFirstIndex() {
        String removed = setUniqueList.remove(0);
        assertEquals("A", removed);
        assertEquals(2, setUniqueList.size());
        assertEquals("B", setUniqueList.get(0));
        assertEquals("C", setUniqueList.get(1));
    }

    @Test
    public void testRemoveLastIndex() {
        String removed = setUniqueList.remove(2);
        assertEquals("C", removed);
        assertEquals(2, setUniqueList.size());
        assertEquals("A", setUniqueList.get(0));
        assertEquals("B", setUniqueList.get(1));
    }
}
