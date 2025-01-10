
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionUtils_selectRejectedTest {

    @Test
    public void testSelectRejected_WithNonEmptyCollection() {
        Collection<Integer> inputCollection = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i % 2 == 0;

        Collection<Integer> result = CollectionUtils.selectRejected(inputCollection, PredicateUtils.asPredicate(predicate));

        assertEquals(3, result.size());
        assertEquals(Arrays.asList(1, 3, 5), new ArrayList<>(result));
    }

    @Test
    public void testSelectRejected_WithEmptyCollection() {
        Collection<Integer> inputCollection = new ArrayList<>();
        Predicate<Integer> predicate = i -> i % 2 == 0;

        Collection<Integer> result = CollectionUtils.selectRejected(inputCollection, PredicateUtils.asPredicate(predicate));

        assertEquals(0, result.size());
        assertEquals(new ArrayList<>(), new ArrayList<>(result));
    }

    @Test
    public void testSelectRejected_WithNullCollection() {
        Collection<Integer> result = CollectionUtils.selectRejected(null, PredicateUtils.asPredicate(i -> i % 2 == 0));

        assertEquals(0, result.size());
        assertEquals(new ArrayList<>(), new ArrayList<>(result));
    }
}
