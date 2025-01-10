
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.set.TransformedSet;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransformedBag_uniqueSetTest {

    @Test
    public void testUniqueSet() {
        // Given
        Bag<String> mockBag = Mockito.mock(Bag.class);
        Transformer<String, String> mockTransformer = Mockito.mock(Transformer.class);
        Set<String> mockSet = new HashSet<>();
        mockSet.add("element");

        when(mockBag.uniqueSet()).thenReturn(mockSet);
        when(mockTransformer.transform("element")).thenReturn("transformed");

        TransformedBag<String> transformedBag = new TransformedBag<>(mockBag, mockTransformer);

        // When
        Set<String> resultSet = transformedBag.uniqueSet();

        // Then
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("transformed");
        assertEquals(expectedSet, resultSet);
    }
}
