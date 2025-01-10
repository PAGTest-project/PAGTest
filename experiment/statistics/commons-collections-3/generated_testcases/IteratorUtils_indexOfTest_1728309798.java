
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
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        Predicate<String> predicate = s -> s.equals("b");

        int result = IteratorUtils.indexOf(iterator, predicate);

        assertEquals(1, result);
    }

    @Test
    public void testIndexOf_ElementNotFound() {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        Predicate<String> predicate = s -> s.equals("d");

        int result = IteratorUtils.indexOf(iterator, predicate);

        assertEquals(CollectionUtils.INDEX_NOT_FOUND, result);
    }

    @Test
    public void testIndexOf_NullPredicate() {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.indexOf(iterator, null);
        });
    }

    @Test
    public void testIndexOf_NullIterator() {
        Predicate<String> predicate = s -> s.equals("b");

        int result = IteratorUtils.indexOf(null, predicate);

        assertEquals(CollectionUtils.INDEX_NOT_FOUND, result);
    }
}
