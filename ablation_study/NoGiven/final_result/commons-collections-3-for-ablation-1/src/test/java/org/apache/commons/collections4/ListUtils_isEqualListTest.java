
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListUtils_isEqualListTest {

    @Test
    void testIsEqualList() {
        // Given
        List<String> list1 = Arrays.asList("a", "b", "c");
        List<String> list2 = Arrays.asList("a", "b", "c");
        List<String> list3 = Arrays.asList("a", "b");
        List<String> list4 = Arrays.asList("a", "b", "d");
        List<String> list5 = null;
        List<String> list6 = Collections.emptyList();

        // When and Then
        assertTrue(ListUtils.isEqualList(list1, list2)); // Same content
        assertFalse(ListUtils.isEqualList(list1, list3)); // Different size
        assertFalse(ListUtils.isEqualList(list1, list4)); // Same size, different content
        assertFalse(ListUtils.isEqualList(list1, list5)); // One null
        assertFalse(ListUtils.isEqualList(list5, list6)); // Both null
        assertTrue(ListUtils.isEqualList(list6, Collections.emptyList())); // Both empty
    }
}
