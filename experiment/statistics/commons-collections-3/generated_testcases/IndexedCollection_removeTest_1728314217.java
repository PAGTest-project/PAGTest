
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_removeTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;
    private Transformer<String, Integer> keyTransformer;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>();
        keyTransformer = s -> Integer.valueOf(s);
        indexedCollection = new IndexedCollection<>(originalCollection, keyTransformer,
                MultiValueMap.<Integer, String>multiValueMap(new HashMap<>()), true);
    }

    @Test
    public void testRemoveElementSuccessfully() {
        originalCollection.add("1");
        originalCollection.add("2");
        indexedCollection.reindex();

        assertTrue(indexedCollection.remove("1"));
        assertFalse(indexedCollection.contains("1"));
        assertTrue(indexedCollection.contains("2"));
    }

    @Test
    public void testRemoveElementNotInCollection() {
        originalCollection.add("1");
        indexedCollection.reindex();

        assertFalse(indexedCollection.remove("2"));
        assertTrue(indexedCollection.contains("1"));
    }
}
