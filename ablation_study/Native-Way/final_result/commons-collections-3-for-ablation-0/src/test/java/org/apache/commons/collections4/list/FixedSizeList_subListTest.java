
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedSizeList_subListTest {

    private FixedSizeList<String> fixedSizeList;

    @BeforeEach
    public void setUp() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        fixedSizeList = new FixedSizeList<>(list);
    }

    @Test
    public void testSubListValidRange() {
        List<String> subList = fixedSizeList.subList(1, 3);
        assertEquals(2, subList.size());
        assertEquals("B", subList.get(0));
        assertEquals("C", subList.get(1));
    }

    @Test
    public void testSubListInvalidRange() {
        assertThrows(IndexOutOfBoundsException.class, () -> fixedSizeList.subList(1, 5));
    }

    @Test
    public void testSubListEmptyRange() {
        List<String> subList = fixedSizeList.subList(2, 2);
        assertEquals(0, subList.size());
    }

    @Test
    public void testSubListFullRange() {
        List<String> subList = fixedSizeList.subList(0, 4);
        assertEquals(4, subList.size());
        assertEquals("A", subList.get(0));
        assertEquals("B", subList.get(1));
        assertEquals("C", subList.get(2));
        assertEquals("D", subList.get(3));
    }
}
