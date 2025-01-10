
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_uniqueIndexedCollectionTest {

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
    public void testUniqueIndexedCollectionWithUniqueKeys() {
        Collection<String> coll = Arrays.asList("1", "2", "3");
        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.uniqueIndexedCollection(coll, keyTransformer);

        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testUniqueIndexedCollectionWithDuplicateKeys() {
        Collection<String> coll = Arrays.asList("1", "2", "2");
        assertThrows(IllegalArgumentException.class, () -> {
            IndexedCollection.uniqueIndexedCollection(coll, keyTransformer);
        });
    }

    @Test
    public void testUniqueIndexedCollectionWithEmptyCollection() {
        Collection<String> coll = Arrays.asList();
        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.uniqueIndexedCollection(coll, keyTransformer);

        assertNull(indexedCollection.get(1));
    }

    @Test
    public void testUniqueIndexedCollectionWithNullKeyTransformer() {
        Collection<String> coll = Arrays.asList("1", "2", "3");
        assertThrows(NullPointerException.class, () -> {
            IndexedCollection.uniqueIndexedCollection(coll, null);
        });
    }
}
