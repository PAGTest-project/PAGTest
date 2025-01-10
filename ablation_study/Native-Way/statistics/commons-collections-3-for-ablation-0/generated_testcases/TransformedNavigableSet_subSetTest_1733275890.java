
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NavigableSet;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

public class TransformedNavigableSet_subSetTest {

    @Test
    public void testSubSet() {
        // Given
        NavigableSet<String> mockSet = mock(NavigableSet.class);
        Transformer<String, String> mockTransformer = mock(Transformer.class);
        TransformedNavigableSet<String> transformedSet = new TransformedNavigableSet<>(mockSet, mockTransformer);

        NavigableSet<String> mockSubSet = mock(NavigableSet.class);
        when(mockSet.subSet("from", true, "to", false)).thenReturn(mockSubSet);

        // When
        NavigableSet<String> result = transformedSet.subSet("from", true, "to", false);

        // Then
        assertEquals(mockSubSet, result);
    }
}
