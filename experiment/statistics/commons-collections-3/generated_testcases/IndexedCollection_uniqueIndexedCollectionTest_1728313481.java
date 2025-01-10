
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IndexedCollection_uniqueIndexedCollectionTest {

    private Transformer<String, Integer> keyTransformer;

    @BeforeEach
    public void setUp() {
        keyTransformer = new Transformer<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        };
    }

    @Test
    public void testUniqueIndexedCollection() {
        Collection<String> coll = Arrays.asList("1", "2", "3");
        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.uniqueIndexedCollection(coll, keyTransformer);

        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testUniqueIndexedCollectionWithDuplicateKey() {
        Collection<String> coll = Arrays.asList("1", "2", "2");
        assertThrows(IllegalArgumentException.class, () -> {
            IndexedCollection.uniqueIndexedCollection(coll, keyTransformer);
        });
    }
}
