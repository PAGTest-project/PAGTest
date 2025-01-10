
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collection;

class CollectionUtils_isFullTest {

    @Test
    void testIsFull_BoundedCollection() {
        BoundedCollection<Object> boundedCollection = mock(BoundedCollection.class);
        when(boundedCollection.isFull()).thenReturn(true);

        assertTrue(CollectionUtils.isFull(boundedCollection));
    }

    @Test
    void testIsFull_NonBoundedCollection() {
        Collection<Object> nonBoundedCollection = mock(Collection.class);
        when(nonBoundedCollection.size()).thenReturn(1);

        assertFalse(CollectionUtils.isFull(nonBoundedCollection));
    }

    @Test
    void testIsFull_IllegalArgumentException() {
        Collection<Object> collection = mock(Collection.class);
        when(collection.size()).thenReturn(1);

        assertFalse(CollectionUtils.isFull(collection));
    }

    @Test
    void testIsFull_NullCollection() {
        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.isFull(null);
        });
    }
}
