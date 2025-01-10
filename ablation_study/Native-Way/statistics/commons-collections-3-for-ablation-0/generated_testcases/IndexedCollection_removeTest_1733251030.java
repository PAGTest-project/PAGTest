
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexedCollection_removeTest {

    @Test
    void testRemove_ElementExists() {
        // Given
        Collection<String> mockCollection = mock(Collection.class);
        Transformer<String, String> mockTransformer = mock(Transformer.class);
        MultiValueMap<String, String> mockMap = MultiValueMap.multiValueMap(new HashMap<>());
        IndexedCollection<String, String> indexedCollection = new IndexedCollection<>(mockCollection, mockTransformer, mockMap, false);
        String element = "testElement";
        when(mockCollection.remove(element)).thenReturn(true);
        when(mockTransformer.apply(element)).thenReturn("testKey");

        // When
        boolean result = indexedCollection.remove(element);

        // Then
        assertTrue(result);
        verify(mockCollection).remove(element);
        verify(mockTransformer).apply(element);
        verify(mockMap).remove("testKey");
    }

    @Test
    void testRemove_ElementDoesNotExist() {
        // Given
        Collection<String> mockCollection = mock(Collection.class);
        Transformer<String, String> mockTransformer = mock(Transformer.class);
        MultiValueMap<String, String> mockMap = MultiValueMap.multiValueMap(new HashMap<>());
        IndexedCollection<String, String> indexedCollection = new IndexedCollection<>(mockCollection, mockTransformer, mockMap, false);
        String element = "testElement";
        when(mockCollection.remove(element)).thenReturn(false);

        // When
        boolean result = indexedCollection.remove(element);

        // Then
        assertFalse(result);
        verify(mockCollection).remove(element);
        verifyNoMoreInteractions(mockTransformer, mockMap);
    }
}
