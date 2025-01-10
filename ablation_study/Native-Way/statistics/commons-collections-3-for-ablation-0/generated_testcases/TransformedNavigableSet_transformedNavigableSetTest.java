
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.NavigableSet;
import java.util.TreeSet;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

public class TransformedNavigableSet_transformedNavigableSetTest {

    @Test
    public void testTransformedNavigableSetWithNonEmptySet() {
        // Given
        NavigableSet<String> originalSet = new TreeSet<>();
        originalSet.add("one");
        originalSet.add("two");
        Transformer<String, String> transformer = mock(Transformer.class);
        when(transformer.apply("one")).thenReturn("ONE");
        when(transformer.apply("two")).thenReturn("TWO");

        // When
        TransformedNavigableSet<String> transformedSet = TransformedNavigableSet.transformedNavigableSet(originalSet, transformer);

        // Then
        assertEquals(2, transformedSet.size());
        assertEquals("ONE", transformedSet.first());
        assertEquals("TWO", transformedSet.last());
        verify(transformer, times(1)).apply("one");
        verify(transformer, times(1)).apply("two");
    }

    @Test
    public void testTransformedNavigableSetWithEmptySet() {
        // Given
        NavigableSet<String> originalSet = new TreeSet<>();
        Transformer<String, String> transformer = mock(Transformer.class);

        // When
        TransformedNavigableSet<String> transformedSet = TransformedNavigableSet.transformedNavigableSet(originalSet, transformer);

        // Then
        assertEquals(0, transformedSet.size());
        verify(transformer, never()).apply(any());
    }
}
