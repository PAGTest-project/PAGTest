
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_matchesAllTest {

    @Test
    public void testMatchesAll_AllElementsMatch() {
        Iterator<Integer> iterator = Arrays.asList(2, 4, 6).iterator();
        Predicate<Integer> predicate = n -> n % 2 == 0;

        boolean result = IteratorUtils.matchesAll(iterator, predicate::test);

        assertTrue(result);
    }

    @Test
    public void testMatchesAll_SomeElementsDoNotMatch() {
        Iterator<Integer> iterator = Arrays.asList(2, 3, 6).iterator();
        Predicate<Integer> predicate = n -> n % 2 == 0;

        boolean result = IteratorUtils.matchesAll(iterator, predicate::test);

        assertFalse(result);
    }

    @Test
    public void testMatchesAll_NullIterator() {
        Predicate<Integer> predicate = n -> n % 2 == 0;

        boolean result = IteratorUtils.matchesAll(null, predicate::test);

        assertTrue(result);
    }

    @Test
    public void testMatchesAll_NullPredicate() {
        Iterator<Integer> iterator = Arrays.asList(2, 4, 6).iterator();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.matchesAll(iterator, null);
        });
    }
}
