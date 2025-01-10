
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testNonUniqueIndexedCollection() {
        Collection<String> coll = Arrays.asList("1", "2", "3");
        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.nonUniqueIndexedCollection(coll, keyTransformer);

        assertNotNull(indexedCollection);
        assertEquals(3, indexedCollection.size());
        assertTrue(indexedCollection.contains("1"));
        assertTrue(indexedCollection.contains("2"));
        assertTrue(indexedCollection.contains("3"));
    }

    @Test
    public void testNonUniqueIndexedCollectionWithEmptyCollection() {
        Collection<String> coll = Arrays.asList();
        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.nonUniqueIndexedCollection(coll, keyTransformer);

        assertNotNull(indexedCollection);
        assertEquals(0, indexedCollection.size());
    }

    @Test
    public void testNonUniqueIndexedCollectionWithDuplicateKeys() {
        Collection<String> coll = Arrays.asList("1", "2", "2", "3");
        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.nonUniqueIndexedCollection(coll, keyTransformer);

        assertNotNull(indexedCollection);
        assertEquals(4, indexedCollection.size());
        assertTrue(indexedCollection.contains("1"));
        assertTrue(indexedCollection.contains("2"));
        assertTrue(indexedCollection.contains("3"));
    }
}
