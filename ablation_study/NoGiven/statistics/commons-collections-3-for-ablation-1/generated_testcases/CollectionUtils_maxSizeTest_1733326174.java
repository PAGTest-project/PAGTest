
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collection;

class CollectionUtils_maxSizeTest {

    @Test
    void testMaxSize_BoundedCollection() {
        BoundedCollection<Object> boundedCollection = mock(BoundedCollection.class);
        when(boundedCollection.maxSize()).thenReturn(10);

        int result = CollectionUtils.maxSize(boundedCollection);
        assertEquals(10, result);
    }

    @Test
    void testMaxSize_UnmodifiableBoundedCollection() {
        Collection<Object> collection = mock(Collection.class);
        BoundedCollection<Object> boundedCollection = mock(BoundedCollection.class);
        when(boundedCollection.maxSize()).thenReturn(20);

        when(UnmodifiableBoundedCollection.unmodifiableBoundedCollection(collection)).thenReturn(boundedCollection);

        int result = CollectionUtils.maxSize(collection);
        assertEquals(20, result);
    }

    @Test
    void testMaxSize_IllegalArgumentException() {
        Collection<Object> collection = mock(Collection.class);
        when(UnmodifiableBoundedCollection.unmodifiableBoundedCollection(collection)).thenThrow(new IllegalArgumentException());

        int result = CollectionUtils.maxSize(collection);
        assertEquals(-1, result);
    }

    @Test
    void testMaxSize_NullCollection() {
        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.maxSize(null);
        });
    }
}
