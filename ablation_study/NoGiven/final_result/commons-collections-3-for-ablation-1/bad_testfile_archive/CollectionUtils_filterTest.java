
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectionUtils_filterTest {

    @Test
    public void testFilter_WithMatchingPredicate() {
        List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i % 2 == 0;

        boolean result = CollectionUtils.filter(collection, new org.apache.commons.collections4.Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer i) {
                return predicate.test(i);
            }
        });

        assertTrue(result);
        assertFalse(collection.contains(1));
        assertFalse(collection.contains(3));
        assertFalse(collection.contains(5));
    }

    @Test
    public void testFilter_WithNonMatchingPredicate() {
        List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i > 10;

        boolean result = CollectionUtils.filter(collection, new org.apache.commons.collections4.Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer i) {
                return predicate.test(i);
            }
        });

        assertFalse(result);
    }

    @Test
    public void testFilter_WithNullCollection() {
        Predicate<Integer> predicate = i -> i % 2 == 0;

        boolean result = CollectionUtils.filter(null, new org.apache.commons.collections4.Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer i) {
                return predicate.test(i);
            }
        });

        assertFalse(result);
    }

    @Test
    public void testFilter_WithNullPredicate() {
        List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);

        boolean result = CollectionUtils.filter(collection, null);

        assertFalse(result);
    }
}
