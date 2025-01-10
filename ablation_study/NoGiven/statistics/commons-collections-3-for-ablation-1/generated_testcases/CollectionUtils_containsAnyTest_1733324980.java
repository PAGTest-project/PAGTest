
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtils_containsAnyTest {

    @Test
    void testContainsAny_coll1SmallerAndContainsElement() {
        List<Integer> coll1 = Arrays.asList(1, 2, 3);
        List<Integer> coll2 = Arrays.asList(3, 4, 5);
        assertTrue(CollectionUtils.containsAny(coll1, coll2));
    }

    @Test
    void testContainsAny_coll2SmallerAndContainsElement() {
        List<Integer> coll1 = Arrays.asList(4, 5, 6);
        List<Integer> coll2 = Arrays.asList(5, 6, 7);
        assertTrue(CollectionUtils.containsAny(coll1, coll2));
    }

    @Test
    void testContainsAny_noCommonElements() {
        List<Integer> coll1 = Arrays.asList(1, 2, 3);
        List<Integer> coll2 = Arrays.asList(4, 5, 6);
        assertFalse(CollectionUtils.containsAny(coll1, coll2));
    }

    @Test
    void testContainsAny_nullColl1() {
        List<Integer> coll2 = Arrays.asList(1, 2, 3);
        assertThrows(NullPointerException.class, () -> CollectionUtils.containsAny((Collection<?>) null, coll2));
    }

    @Test
    void testContainsAny_nullColl2() {
        List<Integer> coll1 = Arrays.asList(1, 2, 3);
        assertThrows(NullPointerException.class, () -> CollectionUtils.containsAny(coll1, (Collection<?>) null));
    }
}
