
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IndexedCollection_nonUniqueIndexedCollectionTest {

    private Transformer<String, Integer> keyTransformer;

    @BeforeEach
    public void setUp() {
        keyTransformer = new Transformer<String, Integer>() {
            @Override
            public Integer transform(String input) {
                return Integer.parseInt(input);
            }
        };
    }

    @Test
    public void testNonUniqueIndexedCollectionWithEmptyCollection() {
        Collection<String> coll = Collections.emptyList();
        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.nonUniqueIndexedCollection(coll, keyTransformer);
        assertNotNull(indexedCollection);
        assertEquals(0, indexedCollection.size());
    }

    @Test
    public void testNonUniqueIndexedCollectionWithNonEmptyCollection() {
        Collection<String> coll = Arrays.asList("1", "2", "3");
        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.nonUniqueIndexedCollection(coll, keyTransformer);
        assertNotNull(indexedCollection);
        assertEquals(3, indexedCollection.size());
        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testNonUniqueIndexedCollectionWithDuplicateKeys() {
        Collection<String> coll = Arrays.asList("1", "2", "2", "3");
        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.nonUniqueIndexedCollection(coll, keyTransformer);
        assertNotNull(indexedCollection);
        assertEquals(4, indexedCollection.size());
        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }
}
