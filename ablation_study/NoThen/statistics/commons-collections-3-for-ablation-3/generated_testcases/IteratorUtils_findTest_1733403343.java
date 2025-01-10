
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_findTest {

    @Test
    public void testFind_ElementFound() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4).iterator();
        Predicate<Integer> predicate = e -> e == 3;

        Integer result = IteratorUtils.find(iterator, predicate);

        assertEquals(3, result);
    }

    @Test
    public void testFind_ElementNotFound() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4).iterator();
        Predicate<Integer> predicate = e -> e == 5;

        Integer result = IteratorUtils.find(iterator, predicate);

        assertNull(result);
    }

    @Test
    public void testFind_NullIterator() {
        Predicate<Integer> predicate = e -> e == 3;

        Integer result = IteratorUtils.find(null, predicate);

        assertNull(result);
    }

    @Test
    public void testFind_NullPredicate() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4).iterator();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.find(iterator, null);
        });
    }
}
