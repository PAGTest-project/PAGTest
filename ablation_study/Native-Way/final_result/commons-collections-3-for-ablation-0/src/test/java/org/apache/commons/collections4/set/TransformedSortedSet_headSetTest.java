
package org.apache.commons.collections4.set;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransformedSortedSet_headSetTest {

    @Test
    public void testHeadSet() {
        // Given
        SortedSet<Integer> originalSet = new TreeSet<>();
        originalSet.add(1);
        originalSet.add(2);
        originalSet.add(3);

        Transformer<Integer, Integer> transformer = mock(Transformer.class);
        when(transformer.apply(anyInt())).thenAnswer(i -> i.getArgument(0));

        TransformedSortedSet<Integer> transformedSet = new TransformedSortedSet<>(originalSet, transformer);

        // When
        SortedSet<Integer> resultSet = transformedSet.headSet(3);

        // Then
        assertEquals(2, resultSet.size());
        assertEquals(1, resultSet.first());
        assertEquals(2, resultSet.last());
    }
}
