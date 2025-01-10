
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_clearTest {

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
    public void testClear() {
        // Given
        originalCollection.add("1");
        originalCollection.add("2");
        indexedCollection.reindex();

        // When
        indexedCollection.clear();

        // Then
        assertTrue(originalCollection.isEmpty());
        assertTrue(indexedCollection.isEmpty());
    }
}
