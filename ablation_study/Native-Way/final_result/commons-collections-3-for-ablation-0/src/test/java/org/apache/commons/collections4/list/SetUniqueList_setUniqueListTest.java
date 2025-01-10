
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUniqueList_setUniqueListTest {

    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    public void testSetUniqueListWithNonEmptyList() {
        list.add("A");
        list.add("B");
        list.add("C");

        SetUniqueList<String> uniqueList = SetUniqueList.setUniqueList(list);

        assertEquals(3, uniqueList.size());
        assertTrue(uniqueList.contains("A"));
        assertTrue(uniqueList.contains("B"));
        assertTrue(uniqueList.contains("C"));
    }

    @Test
    public void testSetUniqueListWithEmptyList() {
        SetUniqueList<String> uniqueList = SetUniqueList.setUniqueList(list);

        assertEquals(0, uniqueList.size());
    }

    @Test
    public void testSetUniqueListWithDuplicates() {
        list.add("A");
        list.add("B");
        list.add("A");

        SetUniqueList<String> uniqueList = SetUniqueList.setUniqueList(list);

        assertEquals(2, uniqueList.size());
        assertTrue(uniqueList.contains("A"));
        assertTrue(uniqueList.contains("B"));
    }

    @Test
    public void testSetUniqueListWithNullList() {
        assertThrows(NullPointerException.class, () -> {
            SetUniqueList.setUniqueList(null);
        });
    }
}
