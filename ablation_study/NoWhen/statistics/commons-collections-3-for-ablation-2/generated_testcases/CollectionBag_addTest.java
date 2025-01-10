
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CollectionBag_addTest {

    private Bag<String> mockBag;
    private CollectionBag<String> collectionBag;

    @BeforeEach
    public void setUp() {
        mockBag = mock(Bag.class);
        collectionBag = new CollectionBag<>(mockBag);
    }

    @Test
    public void testAdd() {
        String object = "testObject";
        int count = 3;

        boolean result = collectionBag.add(object, count);

        assertTrue(result);
        verify(mockBag).add(object, count);
    }
}
