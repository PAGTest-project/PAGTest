
package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_getTest {

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
        indexedCollection = new IndexedCollection<>(new ArrayList<>(), keyTransformer,
                MultiValueMap.<Integer, String>multiValueMap(new HashMap<>()), false);
    }

    @Test
    public void testGetWithExistingKey() {
        indexedCollection.add("1");
        indexedCollection.add("2");
        indexedCollection.add("3");

        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testGetWithNonExistingKey() {
        indexedCollection.add("1");
        indexedCollection.add("2");
        indexedCollection.add("3");

        assertNull(indexedCollection.get(4));
    }

    @Test
    public void testGetAfterReindex() {
        Collection<String> original = new ArrayList<>();
        original.add("1");
        original.add("2");
        original.add("3");

        indexedCollection = new IndexedCollection<>(original, keyTransformer,
                MultiValueMap.<Integer, String>multiValueMap(new HashMap<>()), false);

        assertNull(indexedCollection.get(1));
        assertNull(indexedCollection.get(2));
        assertNull(indexedCollection.get(3));

        indexedCollection.reindex();

        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testGetAfterAddToIndex() {
        indexedCollection.add("1");
        indexedCollection.add("2");
        indexedCollection.add("3");

        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));

        indexedCollection.add("4");

        assertEquals("4", indexedCollection.get(4));
    }

    @Test
    public void testGetAfterRemoveFromIndex() {
        indexedCollection.add("1");
        indexedCollection.add("2");
        indexedCollection.add("3");

        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));

        indexedCollection.remove("2");

        assertNull(indexedCollection.get(2));
    }
}
