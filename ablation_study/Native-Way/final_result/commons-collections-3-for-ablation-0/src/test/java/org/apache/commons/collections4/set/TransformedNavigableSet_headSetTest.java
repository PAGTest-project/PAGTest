
package org.apache.commons.collections4.set;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.NavigableSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransformedNavigableSet_headSetTest {

    @Test
    public void testHeadSet() {
        // Given
        NavigableSet<Integer> originalSet = new TreeSet<>();
        originalSet.add(1);
        originalSet.add(2);
        originalSet.add(3);

        Transformer<Integer, Integer> transformer = mock(Transformer.class);
        when(transformer.transform(1)).thenReturn(10);
        when(transformer.transform(2)).thenReturn(20);
        when(transformer.transform(3)).thenReturn(30);

        TransformedNavigableSet<Integer> transformedSet = new TransformedNavigableSet<>(originalSet, transformer);

        // When
        NavigableSet<Integer> resultSet = transformedSet.headSet(3, false);

        // Then
        assertEquals(2, resultSet.size());
        assertEquals(10, transformer.transform(resultSet.first()));
        assertEquals(20, transformer.transform(resultSet.last()));
    }
}
