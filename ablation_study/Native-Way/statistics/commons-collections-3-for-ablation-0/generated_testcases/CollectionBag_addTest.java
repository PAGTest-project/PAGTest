
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CollectionBag_addTest {

    @Test
    public void testAdd() {
        // Given
        Bag<String> mockBag = mock(Bag.class);
        CollectionBag<String> collectionBag = new CollectionBag<>(mockBag);
        String object = "testObject";
        int count = 5;

        // When
        boolean result = collectionBag.add(object, count);

        // Then
        assertTrue(result);
        verify(mockBag).add(object, count);
    }
}
