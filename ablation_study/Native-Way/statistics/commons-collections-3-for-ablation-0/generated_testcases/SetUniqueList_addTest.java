
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUniqueList_addTest {

    private SetUniqueList<Integer> uniqueList;

    @BeforeEach
    public void setUp() {
        List<Integer> list = new ArrayList<>();
        uniqueList = SetUniqueList.setUniqueList(list);
    }

    @Test
    public void testAddUniqueElement() {
        assertTrue(uniqueList.add(1));
        assertEquals(1, uniqueList.size());
        assertTrue(uniqueList.contains(1));
    }

    @Test
    public void testAddDuplicateElement() {
        uniqueList.add(1);
        assertFalse(uniqueList.add(1));
        assertEquals(1, uniqueList.size());
    }

    @Test
    public void testAddNullElement() {
        assertTrue(uniqueList.add(null));
        assertEquals(1, uniqueList.size());
        assertTrue(uniqueList.contains(null));
    }

    @Test
    public void testAddMultipleElements() {
        assertTrue(uniqueList.add(1));
        assertTrue(uniqueList.add(2));
        assertTrue(uniqueList.add(3));
        assertEquals(3, uniqueList.size());
        assertTrue(uniqueList.contains(1));
        assertTrue(uniqueList.contains(2));
        assertTrue(uniqueList.contains(3));
    }

    @Test
    public void testAddMixedElements() {
        assertTrue(uniqueList.add(1));
        assertFalse(uniqueList.add(1));
        assertTrue(uniqueList.add(2));
        assertTrue(uniqueList.add(null));
        assertFalse(uniqueList.add(null));
        assertEquals(3, uniqueList.size());
        assertTrue(uniqueList.contains(1));
        assertTrue(uniqueList.contains(2));
        assertTrue(uniqueList.contains(null));
    }
}
