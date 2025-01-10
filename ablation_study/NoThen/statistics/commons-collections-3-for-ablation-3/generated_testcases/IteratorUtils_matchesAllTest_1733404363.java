
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

        boolean result = IteratorUtils.matchesAll(iterator, new org.apache.commons.collections4.functors.PredicateTransformer<>(predicate));

        assertTrue(result);
    }

    @Test
    public void testMatchesAll_SomeElementsDoNotMatch() {
        Iterator<Integer> iterator = Arrays.asList(2, 3, 6).iterator();
        Predicate<Integer> predicate = n -> n % 2 == 0;

        boolean result = IteratorUtils.matchesAll(iterator, new org.apache.commons.collections4.functors.PredicateTransformer<>(predicate));

        assertFalse(result);
    }

    @Test
    public void testMatchesAll_NullIterator() {
        Predicate<Integer> predicate = n -> n % 2 == 0;

        boolean result = IteratorUtils.matchesAll(null, new org.apache.commons.collections4.functors.PredicateTransformer<>(predicate));

        assertTrue(result);
    }

    @Test
    public void testMatchesAll_EmptyIterator() {
        Iterator<Integer> iterator = Arrays.asList().iterator();
        Predicate<Integer> predicate = n -> n % 2 == 0;

        boolean result = IteratorUtils.matchesAll(iterator, new org.apache.commons.collections4.functors.PredicateTransformer<>(predicate));

        assertTrue(result);
    }
}
