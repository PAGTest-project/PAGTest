
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUniqueList_addAllTest {

    private SetUniqueList<Integer> uniqueList;

    @BeforeEach
    public void setUp() {
        List<Integer> list = new ArrayList<>();
        uniqueList = SetUniqueList.setUniqueList(list);
    }

    @Test
    public void testAddAllWithIndex() {
        Collection<Integer> coll = Arrays.asList(1, 2, 3, 4, 5);
        assertTrue(uniqueList.addAll(0, coll));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), uniqueList);
    }

    @Test
    public void testAddAllWithIndexDuplicates() {
        Collection<Integer> coll = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        assertTrue(uniqueList.addAll(0, coll));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), uniqueList);
    }

    @Test
    public void testAddAllWithIndexEmptyCollection() {
        Collection<Integer> coll = new ArrayList<>();
        assertFalse(uniqueList.addAll(0, coll));
        assertTrue(uniqueList.isEmpty());
    }

    @Test
    public void testAddAllWithIndexMixedDuplicates() {
        uniqueList.addAll(Arrays.asList(1, 2, 3));
        Collection<Integer> coll = Arrays.asList(2, 3, 4, 5);
        assertTrue(uniqueList.addAll(1, coll));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), uniqueList);
    }

    @Test
    public void testAddAllWithIndexAtEnd() {
        uniqueList.addAll(Arrays.asList(1, 2, 3));
        Collection<Integer> coll = Arrays.asList(4, 5);
        assertTrue(uniqueList.addAll(uniqueList.size(), coll));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), uniqueList);
    }
}
