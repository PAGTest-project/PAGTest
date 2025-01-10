
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollectionSortedBag_addTest {

    private SortedBag<String> mockBag;
    private CollectionSortedBag<String> collectionSortedBag;

    @BeforeEach
    void setUp() {
        mockBag = mock(SortedBag.class);
        collectionSortedBag = new CollectionSortedBag<>(mockBag);
    }

    @Test
    void testAdd() {
        String object = "testObject";
        int count = 1;

        boolean result = collectionSortedBag.add(object, count);

        verify(mockBag).add(object, count);
        assertTrue(result);
    }
}
