
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransformedBag_transformedBagTest {

    @Test
    public void testTransformedBagWithNonEmptyBag() {
        // Given
        Bag<String> mockBag = mock(Bag.class);
        Transformer<String, String> mockTransformer = mock(Transformer.class);
        when(mockBag.isEmpty()).thenReturn(false);
        when(mockBag.toArray()).thenReturn(new String[]{"a", "b"});
        when(mockTransformer.apply("a")).thenReturn("A");
        when(mockTransformer.apply("b")).thenReturn("B");

        // When
        Bag<String> resultBag = TransformedBag.transformedBag(mockBag, mockTransformer);

        // Then
        verify(mockBag).clear();
        verify(mockBag).add("A");
        verify(mockBag).add("B");
        assertNotNull(resultBag);
    }

    @Test
    public void testTransformedBagWithEmptyBag() {
        // Given
        Bag<String> mockBag = mock(Bag.class);
        Transformer<String, String> mockTransformer = mock(Transformer.class);
        when(mockBag.isEmpty()).thenReturn(true);

        // When
        Bag<String> resultBag = TransformedBag.transformedBag(mockBag, mockTransformer);

        // Then
        verify(mockBag, never()).clear();
        verify(mockBag, never()).toArray();
        assertNotNull(resultBag);
    }
}
