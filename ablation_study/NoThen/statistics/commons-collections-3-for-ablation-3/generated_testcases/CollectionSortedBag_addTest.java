
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollectionSortedBag_addTest {

    private SortedBag<String> mockSortedBag;
    private CollectionSortedBag<String> collectionSortedBag;

    @BeforeEach
    void setUp() {
        mockSortedBag = mock(SortedBag.class);
        collectionSortedBag = new CollectionSortedBag<>(mockSortedBag);
    }

    @Test
    void testAdd() {
        String element = "testElement";
        int count = 1;

        boolean result = collectionSortedBag.add(element, count);

        assertTrue(result);
        verify(mockSortedBag).add(element, count);
    }
}
