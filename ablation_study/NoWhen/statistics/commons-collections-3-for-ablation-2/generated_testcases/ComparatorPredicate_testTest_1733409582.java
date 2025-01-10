
package org.apache.commons.collections4.functors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Comparator;

public class ComparatorPredicate_testTest {

    @Test
    public void testComparatorPredicate() {
        // Given
        Comparator<Integer> comparator = mock(Comparator.class);
        when(comparator.compare(anyInt(), anyInt())).thenReturn(0);

        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(1, comparator, ComparatorPredicate.Criterion.EQUAL);

        // When
        boolean result = predicate.test(1);

        // Then
        assertTrue(result);
    }

    @Test
    public void testComparatorPredicateGreater() {
        // Given
        Comparator<Integer> comparator = mock(Comparator.class);
        when(comparator.compare(anyInt(), anyInt())).thenReturn(1);

        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(1, comparator, ComparatorPredicate.Criterion.GREATER);

        // When
        boolean result = predicate.test(0);

        // Then
        assertTrue(result);
    }

    @Test
    public void testComparatorPredicateLess() {
        // Given
        Comparator<Integer> comparator = mock(Comparator.class);
        when(comparator.compare(anyInt(), anyInt())).thenReturn(-1);

        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(1, comparator, ComparatorPredicate.Criterion.LESS);

        // When
        boolean result = predicate.test(2);

        // Then
        assertTrue(result);
    }

    @Test
    public void testComparatorPredicateGreaterOrEqual() {
        // Given
        Comparator<Integer> comparator = mock(Comparator.class);
        when(comparator.compare(anyInt(), anyInt())).thenReturn(0);

        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(1, comparator, ComparatorPredicate.Criterion.GREATER_OR_EQUAL);

        // When
        boolean result = predicate.test(1);

        // Then
        assertTrue(result);
    }

    @Test
    public void testComparatorPredicateLessOrEqual() {
        // Given
        Comparator<Integer> comparator = mock(Comparator.class);
        when(comparator.compare(anyInt(), anyInt())).thenReturn(0);

        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(1, comparator, ComparatorPredicate.Criterion.LESS_OR_EQUAL);

        // When
        boolean result = predicate.test(1);

        // Then
        assertTrue(result);
    }

    @Test
    public void testComparatorPredicateInvalidCriterion() {
        // Given
        Comparator<Integer> comparator = mock(Comparator.class);
        when(comparator.compare(anyInt(), anyInt())).thenReturn(0);

        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(1, comparator, null);

        // When & Then
        assertThrows(IllegalStateException.class, () -> predicate.test(1));
    }
}
