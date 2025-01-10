
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NavigableSet;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

public class PredicatedNavigableSet_headSetTest {

    @Test
    public void testHeadSet() {
        // Given
        NavigableSet<Integer> mockSet = mock(NavigableSet.class);
        Predicate<Integer> mockPredicate = mock(Predicate.class);
        NavigableSet<Integer> expectedHeadSet = mock(NavigableSet.class);

        when(mockSet.headSet(10, true)).thenReturn(expectedHeadSet);

        PredicatedNavigableSet<Integer> predicatedSet = new PredicatedNavigableSet<>(mockSet, mockPredicate);

        // When
        NavigableSet<Integer> result = predicatedSet.headSet(10, true);

        // Then
        assertEquals(expectedHeadSet, result);
    }
}
