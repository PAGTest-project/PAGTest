
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListUtils_isEqualListTest {

    @Test
    public void testIsEqualList_SameReference() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        assertTrue(ListUtils.isEqualList(list1, list1));
    }

    @Test
    public void testIsEqualList_BothNull() {
        assertTrue(ListUtils.isEqualList(null, null));
    }

    @Test
    public void testIsEqualList_OneNull() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        assertFalse(ListUtils.isEqualList(list1, null));
        assertFalse(ListUtils.isEqualList(null, list1));
    }

    @Test
    public void testIsEqualList_DifferentSize() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2);
        assertFalse(ListUtils.isEqualList(list1, list2));
    }

    @Test
    public void testIsEqualList_SameElements() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        assertTrue(ListUtils.isEqualList(list1, list2));
    }

    @Test
    public void testIsEqualList_DifferentElements() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 4);
        assertFalse(ListUtils.isEqualList(list1, list2));
    }

    @Test
    public void testIsEqualList_EmptyLists() {
        List<Integer> list1 = Collections.emptyList();
        List<Integer> list2 = Collections.emptyList();
        assertTrue(ListUtils.isEqualList(list1, list2));
    }
}
