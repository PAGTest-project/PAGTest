
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorUtils_filteredIteratorTest {

    @Test
    public void testFilteredIterator_NormalCase() {
        // Given
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        Predicate<Integer> predicate = i -> i % 2 == 0;

        // When
        Iterator<Integer> filteredIterator = IteratorUtils.filteredIterator(iterator, new org.apache.commons.collections4.Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer integer) {
                return predicate.test(integer);
            }
        });

        // Then
        assertNotNull(filteredIterator);
    }

    @Test
    public void testFilteredIterator_NullIterator() {
        // Given
        Iterator<Integer> iterator = null;
        Predicate<Integer> predicate = i -> i % 2 == 0;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.filteredIterator(iterator, new org.apache.commons.collections4.Predicate<Integer>() {
                @Override
                public boolean evaluate(Integer integer) {
                    return predicate.test(integer);
                }
            });
        });
    }

    @Test
    public void testFilteredIterator_NullPredicate() {
        // Given
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        Predicate<Integer> predicate = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.filteredIterator(iterator, new org.apache.commons.collections4.Predicate<Integer>() {
                @Override
                public boolean evaluate(Integer integer) {
                    return predicate != null && predicate.test(integer);
                }
            });
        });
    }
}
