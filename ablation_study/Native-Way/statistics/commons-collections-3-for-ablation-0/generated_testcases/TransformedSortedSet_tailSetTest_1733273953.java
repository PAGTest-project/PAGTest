
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

public class TransformedSortedSet_tailSetTest {

    @Test
    public void testTailSet() {
        // Given
        SortedSet<Integer> originalSet = new TreeSet<>();
        originalSet.add(1);
        originalSet.add(2);
        originalSet.add(3);
        originalSet.add(4);
        originalSet.add(5);

        Transformer<Integer, Integer> transformer = mock(Transformer.class);
        when(transformer.transform(3)).thenReturn(30);
        when(transformer.transform(4)).thenReturn(40);
        when(transformer.transform(5)).thenReturn(50);

        TransformedSortedSet<Integer> transformedSet = new TransformedSortedSet<>(originalSet, transformer);

        // When
        SortedSet<Integer> resultSet = transformedSet.tailSet(3);

        // Then
        assertEquals(3, resultSet.size());
        assertEquals(30, resultSet.first());
        assertEquals(50, resultSet.last());
    }
}
