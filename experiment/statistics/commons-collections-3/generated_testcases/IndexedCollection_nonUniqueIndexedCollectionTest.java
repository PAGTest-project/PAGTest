
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IndexedCollection_nonUniqueIndexedCollectionTest {

    private Collection<String> collection;
    private Transformer<String, Integer> keyTransformer;

    @BeforeEach
    public void setUp() {
        collection = new ArrayList<>();
        keyTransformer = new Transformer<String, Integer>() {
            @Override
            public Integer transform(String input) {
                return Integer.parseInt(input);
            }
        };
    }

    @Test
    public void testNonUniqueIndexedCollection() {
        collection.add("1");
        collection.add("2");
        collection.add("3");

        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.nonUniqueIndexedCollection(collection, keyTransformer);

        assertNotNull(indexedCollection);
        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }
}
