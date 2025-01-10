
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

public class CollectionSortedBag_addTest {

    private SortedBag<String> mockBag;
    private CollectionSortedBag<String> collectionSortedBag;

    @BeforeEach
    public void setUp() {
        mockBag = Mockito.mock(SortedBag.class);
        collectionSortedBag = new CollectionSortedBag<>(mockBag);
    }

    @Test
    public void testAdd() {
        // Given
        String element = "testElement";
        int count = 1;

        // When
        boolean result = collectionSortedBag.add(element, count);

        // Then
        assertTrue(result);
        verify(mockBag).add(element, count);
    }
}
