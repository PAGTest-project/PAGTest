
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_addTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Transformer<String, Integer> keyTransformer;

    @BeforeEach
    public void setUp() {
        keyTransformer = new Transformer<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        };
        Collection<String> coll = new ArrayList<>();
        indexedCollection = IndexedCollection.nonUniqueIndexedCollection(coll, keyTransformer);
    }

    @Test
    public void testAddSuccess() {
        String object = "123";
        assertTrue(indexedCollection.add(object));
        assertEquals(object, indexedCollection.get(123));
    }

    @Test
    public void testAddFailure() {
        String object = "123";
        assertTrue(indexedCollection.add(object));
        assertFalse(indexedCollection.add(object)); // Adding the same object again should fail
    }

    @Test
    public void testAddUniqueIndexFailure() {
        Collection<String> coll = new ArrayList<>();
        indexedCollection = IndexedCollection.uniqueIndexedCollection(coll, keyTransformer);
        String object1 = "123";
        String object2 = "123";
        assertTrue(indexedCollection.add(object1));
        assertThrows(IllegalArgumentException.class, () -> indexedCollection.add(object2));
    }

    @Test
    public void testAddNonUniqueIndexSuccess() {
        String object1 = "123";
        String object2 = "123";
        assertTrue(indexedCollection.add(object1));
        assertTrue(indexedCollection.add(object2));
        assertEquals(object1, indexedCollection.get(123));
    }
}
