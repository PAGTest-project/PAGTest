
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUniqueList_removeIfTest {

    private SetUniqueList<Integer> uniqueList;

    @BeforeEach
    public void setUp() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        uniqueList = SetUniqueList.setUniqueList(list);
    }

    @Test
    public void testRemoveIf_AllElementsRemoved() {
        Predicate<Integer> filter = e -> e < 4;
        assertTrue(uniqueList.removeIf(filter));
        assertTrue(uniqueList.isEmpty());
    }

    @Test
    public void testRemoveIf_NoElementsRemoved() {
        Predicate<Integer> filter = e -> e > 3;
        assertFalse(uniqueList.removeIf(filter));
        assertEquals(3, uniqueList.size());
    }

    @Test
    public void testRemoveIf_SomeElementsRemoved() {
        Predicate<Integer> filter = e -> e % 2 == 0;
        assertTrue(uniqueList.removeIf(filter));
        assertEquals(2, uniqueList.size());
        assertTrue(uniqueList.contains(1));
        assertTrue(uniqueList.contains(3));
    }

    @Test
    public void testRemoveIf_EmptyList() {
        uniqueList.clear();
        Predicate<Integer> filter = e -> true;
        assertFalse(uniqueList.removeIf(filter));
        assertTrue(uniqueList.isEmpty());
    }
}
