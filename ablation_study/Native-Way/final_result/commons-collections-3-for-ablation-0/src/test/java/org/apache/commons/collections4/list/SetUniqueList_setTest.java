
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUniqueList_setTest {

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
    public void testSetUniqueElement() {
        String result = setUniqueList.set(1, "D");
        assertEquals("B", result);
        assertEquals(3, setUniqueList.size());
        assertTrue(setUniqueList.contains("D"));
        assertFalse(setUniqueList.contains("B"));
    }

    @Test
    public void testSetDuplicateElement() {
        String result = setUniqueList.set(1, "A");
        assertEquals("B", result);
        assertEquals(2, setUniqueList.size());
        assertTrue(setUniqueList.contains("A"));
        assertFalse(setUniqueList.contains("B"));
    }

    @Test
    public void testSetSameElement() {
        String result = setUniqueList.set(1, "B");
        assertEquals("B", result);
        assertEquals(3, setUniqueList.size());
        assertTrue(setUniqueList.contains("B"));
    }

    @Test
    public void testSetAtEndOfList() {
        String result = setUniqueList.set(2, "D");
        assertEquals("C", result);
        assertEquals(3, setUniqueList.size());
        assertTrue(setUniqueList.contains("D"));
        assertFalse(setUniqueList.contains("C"));
    }

    @Test
    public void testSetAtBeginningOfList() {
        String result = setUniqueList.set(0, "D");
        assertEquals("A", result);
        assertEquals(3, setUniqueList.size());
        assertTrue(setUniqueList.contains("D"));
        assertFalse(setUniqueList.contains("A"));
    }
}
