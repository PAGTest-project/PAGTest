
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.set.TransformedSet;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransformedBag_uniqueSetTest {

    @Test
    public void testUniqueSet() {
        // Given
        Bag<String> mockBag = mock(Bag.class);
        Set<String> mockSet = mock(Set.class);
        Transformer<String, String> mockTransformer = mock(Transformer.class);

        when(mockBag.uniqueSet()).thenReturn(mockSet);

        TransformedBag<String> transformedBag = new TransformedBag<>(mockBag, mockTransformer);

        // When
        Set<String> resultSet = transformedBag.uniqueSet();

        // Then
        assertEquals(TransformedSet.transformingSet(mockSet, mockTransformer), resultSet);
    }
}
