
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_containsTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;
    private Transformer<String, Integer> keyTransformer;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>();
        keyTransformer = String::length;
        indexedCollection = new IndexedCollection<>(originalCollection, keyTransformer,
                MultiValueMap.<Integer, String>multiValueMap(new HashMap<>()), true);
    }

    @Test
    public void testContainsReturnsTrueForExistingElement() {
        originalCollection.add("test");
        indexedCollection.reindex();
        assertTrue(indexedCollection.contains("test"));
    }

    @Test
    public void testContainsReturnsFalseForNonExistingElement() {
        originalCollection.add("test");
        indexedCollection.reindex();
        assertFalse(indexedCollection.contains("nonexistent"));
    }
}
