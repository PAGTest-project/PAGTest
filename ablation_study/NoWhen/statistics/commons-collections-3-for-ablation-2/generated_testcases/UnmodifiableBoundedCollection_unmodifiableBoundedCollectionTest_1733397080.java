
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableBoundedCollection_unmodifiableBoundedCollectionTest {

    @Test
    void testUnmodifiableBoundedCollection_WithUnmodifiableCollection() {
        // Given
        BoundedCollection<String> mockColl = mock(BoundedCollection.class);
        when(mockColl.isUnmodifiable()).thenReturn(true);

        // When
        BoundedCollection<String> result = UnmodifiableBoundedCollection.unmodifiableBoundedCollection(mockColl);

        // Then
        assertSame(mockColl, result);
    }

    @Test
    void testUnmodifiableBoundedCollection_WithModifiableCollection() {
        // Given
        BoundedCollection<String> mockColl = mock(BoundedCollection.class);
        when(mockColl.isUnmodifiable()).thenReturn(false);

        // When
        BoundedCollection<String> result = UnmodifiableBoundedCollection.unmodifiableBoundedCollection(mockColl);

        // Then
        assertTrue(result instanceof UnmodifiableBoundedCollection);
        assertNotSame(mockColl, result);
    }
}
