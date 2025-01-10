
package org.apache.commons.collections4.set;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import java.util.NavigableSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransformedNavigableSet_tailSetTest {

    @Test
    public void testTailSet() {
        // Given
        NavigableSet<Integer> originalSet = new TreeSet<>();
        originalSet.add(1);
        originalSet.add(2);
        originalSet.add(3);
        originalSet.add(4);
        originalSet.add(5);

        Transformer<Integer, Integer> transformer = mock(Transformer.class);
        when(transformer.apply(any())).thenAnswer(i -> i.getArgument(0));

        TransformedNavigableSet<Integer> transformedSet = new TransformedNavigableSet<>(originalSet, transformer);

        // When
        NavigableSet<Integer> result = transformedSet.tailSet(3, true);

        // Then
        assertEquals(3, result.size());
        assertEquals(3, result.first());
        assertEquals(5, result.last());
    }
}
