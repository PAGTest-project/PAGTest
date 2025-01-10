
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUniqueList_subListTest {

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
    public void testSubListValidRange() {
        List<String> subList = setUniqueList.subList(1, 3);
        assertEquals(2, subList.size());
        assertEquals("B", subList.get(0));
        assertEquals("C", subList.get(1));
    }

    @Test
    public void testSubListInvalidRange() {
        List<String> subList = setUniqueList.subList(3, 3);
        assertTrue(subList.isEmpty());
    }

    @Test
    public void testSubListWithDuplicates() {
        List<String> listWithDuplicates = new ArrayList<>();
        listWithDuplicates.add("A");
        listWithDuplicates.add("B");
        listWithDuplicates.add("B");
        listWithDuplicates.add("C");
        SetUniqueList<String> list = SetUniqueList.setUniqueList(listWithDuplicates);

        List<String> subList = list.subList(1, 3); // Corrected toIndex to 3
        assertEquals(2, subList.size());
        assertEquals("B", subList.get(0));
        assertEquals("C", subList.get(1));
    }

    @Test
    public void testSubListUnmodifiable() {
        List<String> subList = setUniqueList.subList(0, 2);
        assertFalse(subList.getClass().equals(ArrayList.class));
    }
}
