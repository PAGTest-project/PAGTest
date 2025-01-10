
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NavigableSet;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PredicatedNavigableSet_subSetTest {

    private NavigableSet<Integer> mockSet;
    private Predicate<Integer> mockPredicate;
    private PredicatedNavigableSet<Integer> predicatedSet;

    @BeforeEach
    public void setUp() {
        mockSet = mock(NavigableSet.class);
        mockPredicate = mock(Predicate.class);
        predicatedSet = new PredicatedNavigableSet<>(mockSet, mockPredicate);
    }

    @Test
    public void testSubSet() {
        NavigableSet<Integer> subSet = mock(NavigableSet.class);
        when(mockSet.subSet(1, true, 10, false)).thenReturn(subSet);

        NavigableSet<Integer> result = predicatedSet.subSet(1, true, 10, false);

        assertEquals(subSet, result);
    }
}
