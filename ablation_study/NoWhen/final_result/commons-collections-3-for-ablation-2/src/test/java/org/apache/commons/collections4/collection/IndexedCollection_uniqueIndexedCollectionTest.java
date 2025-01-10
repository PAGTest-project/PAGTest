
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IndexedCollection_uniqueIndexedCollectionTest {

    @Test
    public void testUniqueIndexedCollection() {
        // Given
        Collection<String> coll = Arrays.asList("apple", "banana");
        Transformer<String, String> keyTransformer = String::toUpperCase;

        // When
        IndexedCollection<String, String> indexedCollection = IndexedCollection.uniqueIndexedCollection(coll, keyTransformer);

        // Then
        assertNotNull(indexedCollection);
    }
}
