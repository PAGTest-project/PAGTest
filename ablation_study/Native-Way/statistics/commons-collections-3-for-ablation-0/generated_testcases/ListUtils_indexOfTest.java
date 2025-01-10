
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtils_indexOfTest {

    @Test
    public void testIndexOf_Found() {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        Predicate<String> predicate = s -> s.startsWith("b");
        int result = ListUtils.indexOf(list, predicate::test);
        assertEquals(1, result);
    }

    @Test
    public void testIndexOf_NotFound() {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        Predicate<String> predicate = s -> s.startsWith("d");
        int result = ListUtils.indexOf(list, predicate::test);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, result);
    }

    @Test
    public void testIndexOf_NullList() {
        Predicate<String> predicate = s -> s.startsWith("b");
        int result = ListUtils.indexOf(null, predicate::test);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, result);
    }

    @Test
    public void testIndexOf_NullPredicate() {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        int result = ListUtils.indexOf(list, null);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, result);
    }
}
