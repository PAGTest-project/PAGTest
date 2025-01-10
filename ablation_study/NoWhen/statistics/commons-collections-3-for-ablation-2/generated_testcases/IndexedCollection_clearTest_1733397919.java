
package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_clearTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>(asList("1", "2", "3"));
        indexedCollection = IndexedCollection.uniqueIndexedCollection(originalCollection, new Transformer<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        });
    }

    @Test
    public void testClearRemovesAllElementsFromIndex() {
        indexedCollection.clear();
        assertTrue(indexedCollection.isEmpty());
        assertTrue(indexedCollection.index.isEmpty());
    }

    @Test
    public void testClearRemovesAllElementsFromDecoratedCollection() {
        indexedCollection.clear();
        assertTrue(originalCollection.isEmpty());
    }
}
