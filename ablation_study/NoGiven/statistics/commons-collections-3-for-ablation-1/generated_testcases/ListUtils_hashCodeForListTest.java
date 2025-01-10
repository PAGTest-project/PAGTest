
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtils_hashCodeForListTest {

    @Test
    public void testHashCodeForList_NullList() {
        assertEquals(0, ListUtils.hashCodeForList(null));
    }

    @Test
    public void testHashCodeForList_EmptyList() {
        assertEquals(1, ListUtils.hashCodeForList(Collections.emptyList()));
    }

    @Test
    public void testHashCodeForList_NonEmptyList() {
        List<String> list = Arrays.asList("a", "b", "c");
        int expectedHashCode = 1;
        for (String s : list) {
            expectedHashCode = 31 * expectedHashCode + (s == null ? 0 : s.hashCode());
        }
        assertEquals(expectedHashCode, ListUtils.hashCodeForList(list));
    }

    @Test
    public void testHashCodeForList_ListWithNullElements() {
        List<String> list = Arrays.asList("a", null, "c");
        int expectedHashCode = 1;
        for (String s : list) {
            expectedHashCode = 31 * expectedHashCode + (s == null ? 0 : s.hashCode());
        }
        assertEquals(expectedHashCode, ListUtils.hashCodeForList(list));
    }
}
