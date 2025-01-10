
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
        when(comparator.compare(anyInt(), anyInt())).thenReturn(0, 1, -1);

        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(0, comparator, ComparatorPredicate.Criterion.EQUAL);

        // When and Then
        assertTrue(predicate.test(0)); // EQUAL

        predicate = new ComparatorPredicate<>(0, comparator, ComparatorPredicate.Criterion.GREATER);
        assertTrue(predicate.test(1)); // GREATER

        predicate = new ComparatorPredicate<>(0, comparator, ComparatorPredicate.Criterion.LESS);
        assertTrue(predicate.test(-1)); // LESS

        predicate = new ComparatorPredicate<>(0, comparator, ComparatorPredicate.Criterion.GREATER_OR_EQUAL);
        assertTrue(predicate.test(0)); // GREATER_OR_EQUAL
        assertTrue(predicate.test(1)); // GREATER_OR_EQUAL

        predicate = new ComparatorPredicate<>(0, comparator, ComparatorPredicate.Criterion.LESS_OR_EQUAL);
        assertTrue(predicate.test(0)); // LESS_OR_EQUAL
        assertTrue(predicate.test(-1)); // LESS_OR_EQUAL
    }
}
