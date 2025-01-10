
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorUtils_indexOfTest {

    @Test
    public void testIndexOf_ElementFound() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4, 5).iterator();
        Predicate<Integer> predicate = i -> i == 3;
        int result = IteratorUtils.indexOf(iterator, predicate::test);
        assertEquals(2, result);
    }

    @Test
    public void testIndexOf_ElementNotFound() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4, 5).iterator();
        Predicate<Integer> predicate = i -> i == 6;
        int result = IteratorUtils.indexOf(iterator, predicate::test);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, result);
    }

    @Test
    public void testIndexOf_NullPredicate() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4, 5).iterator();
        assertThrows(NullPointerException.class, () -> IteratorUtils.indexOf(iterator, null));
    }

    @Test
    public void testIndexOf_NullIterator() {
        Predicate<Integer> predicate = i -> i == 3;
        int result = IteratorUtils.indexOf(null, predicate::test);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, result);
    }
}
