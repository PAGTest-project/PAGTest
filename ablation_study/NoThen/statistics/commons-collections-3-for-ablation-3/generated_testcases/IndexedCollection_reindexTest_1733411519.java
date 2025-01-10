
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndexedCollection_reindexTest {

    private IndexedCollection<String, String> indexedCollection;
    private MultiValueMap<String, String> index;

    @BeforeEach
    public void setUp() {
        index = MultiValueMap.multiValueMap(new HashMap<>());
        indexedCollection = new IndexedCollection<>(Arrays.asList("A", "B"), new Transformer<String, String>() {
            @Override
            public String transform(String input) {
                return input;
            }
        }, index, false);
    }

    @Test
    public void testReindex() {
        // Given
        indexedCollection.clear();
        indexedCollection.add("C");
        indexedCollection.add("D");

        // When
        indexedCollection.reindex();

        // Then
        assertEquals(2, index.size());
        assertTrue(index.containsKey("C"));
        assertTrue(index.containsKey("D"));
    }
}
