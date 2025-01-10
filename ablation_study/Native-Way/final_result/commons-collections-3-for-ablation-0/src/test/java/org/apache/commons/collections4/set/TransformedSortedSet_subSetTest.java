
package org.apache.commons.collections4.set;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransformedSortedSet_subSetTest {

    @Test
    public void testSubSet() {
        // Given
        SortedSet<Integer> originalSet = new TreeSet<>();
        originalSet.add(1);
        originalSet.add(2);
        originalSet.add(3);
        originalSet.add(4);
        originalSet.add(5);

        Transformer<Integer, Integer> transformer = mock(Transformer.class);
        when(transformer.apply(anyInt())).thenAnswer(i -> i.getArgument(0));

        TransformedSortedSet<Integer> transformedSet = new TransformedSortedSet<>(originalSet, transformer);

        // When
        SortedSet<Integer> subset = transformedSet.subSet(2, 4);

        // Then
        assertEquals(2, subset.size());
        assertEquals(2, subset.first());
        assertEquals(3, subset.last());
    }
}
