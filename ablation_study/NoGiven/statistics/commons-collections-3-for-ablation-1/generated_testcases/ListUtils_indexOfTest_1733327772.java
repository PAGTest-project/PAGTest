
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtils_indexOfTest {

    @Test
    public void testIndexOf_Found() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i == 3;
        int result = ListUtils.indexOf(list, predicate);
        assertEquals(2, result);
    }

    @Test
    public void testIndexOf_NotFound() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i == 6;
        int result = ListUtils.indexOf(list, predicate);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, result);
    }

    @Test
    public void testIndexOf_NullList() {
        Predicate<Integer> predicate = i -> i == 3;
        int result = ListUtils.indexOf(null, predicate);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, result);
    }

    @Test
    public void testIndexOf_NullPredicate() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int result = ListUtils.indexOf(list, null);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, result);
    }
}
