
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtils_containsAllTest {

    @Test
    void testContainsAll_Basic() {
        List<Integer> coll1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> coll2 = Arrays.asList(2, 4);
        assertTrue(CollectionUtils.containsAll(coll1, coll2));
    }

    @Test
    void testContainsAll_EmptyColl2() {
        List<Integer> coll1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> coll2 = Collections.emptyList();
        assertTrue(CollectionUtils.containsAll(coll1, coll2));
    }

    @Test
    void testContainsAll_Coll2NotContained() {
        List<Integer> coll1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> coll2 = Arrays.asList(2, 5);
        assertFalse(CollectionUtils.containsAll(coll1, coll2));
    }

    @Test
    void testContainsAll_NullColl1() {
        List<Integer> coll2 = Arrays.asList(2, 4);
        assertThrows(NullPointerException.class, () -> CollectionUtils.containsAll(null, coll2));
    }

    @Test
    void testContainsAll_NullColl2() {
        List<Integer> coll1 = Arrays.asList(1, 2, 3, 4);
        assertThrows(NullPointerException.class, () -> CollectionUtils.containsAll(coll1, null));
    }
}
