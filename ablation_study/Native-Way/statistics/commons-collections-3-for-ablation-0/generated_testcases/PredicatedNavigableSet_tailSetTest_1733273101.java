
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NavigableSet;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

public class PredicatedNavigableSet_tailSetTest {

    @Test
    public void testTailSet() {
        // Given
        NavigableSet<Integer> mockSet = mock(NavigableSet.class);
        Predicate<Integer> mockPredicate = mock(Predicate.class);
        PredicatedNavigableSet<Integer> predicatedSet = new PredicatedNavigableSet<>(mockSet, mockPredicate);

        NavigableSet<Integer> mockTailSet = mock(NavigableSet.class);
        when(mockSet.tailSet(10, true)).thenReturn(mockTailSet);

        // When
        NavigableSet<Integer> result = predicatedSet.tailSet(10, true);

        // Then
        assertEquals(mockTailSet, result);
    }
}
