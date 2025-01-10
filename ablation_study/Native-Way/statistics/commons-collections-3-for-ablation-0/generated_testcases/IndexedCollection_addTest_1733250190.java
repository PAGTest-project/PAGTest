
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_addTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;
    private Transformer<String, Integer> keyTransformer;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>();
        keyTransformer = new Transformer<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        };
        indexedCollection = new IndexedCollection<>(originalCollection, keyTransformer,
                MultiValueMap.<Integer, String>multiValueMap(new HashMap<>()), true);
    }

    @Test
    public void testAddSuccess() {
        String element = "1";
        assertTrue(indexedCollection.add(element));
        assertEquals(element, indexedCollection.get(1));
    }

    @Test
    public void testAddFailureDueToUniqueConstraint() {
        String element1 = "1";
        String element2 = "1";
        assertTrue(indexedCollection.add(element1));
        assertThrows(IllegalArgumentException.class, () -> indexedCollection.add(element2));
    }

    @Test
    public void testAddFailureDueToNonUniqueIndex() {
        indexedCollection = new IndexedCollection<>(originalCollection, keyTransformer,
                MultiValueMap.<Integer, String>multiValueMap(new HashMap<>()), false);
        String element1 = "1";
        String element2 = "1";
        assertTrue(indexedCollection.add(element1));
        assertTrue(indexedCollection.add(element2));
        assertEquals(2, indexedCollection.values(1).size());
    }

    @Test
    public void testAddAllSuccess() {
        Collection<String> elements = new ArrayList<>();
        elements.add("1");
        elements.add("2");
        assertTrue(indexedCollection.addAll(elements));
        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
    }

    @Test
    public void testAddAllFailureDueToUniqueConstraint() {
        Collection<String> elements = new ArrayList<>();
        elements.add("1");
        elements.add("1");
        assertThrows(IllegalArgumentException.class, () -> indexedCollection.addAll(elements));
    }

    @Test
    public void testRemoveSuccess() {
        String element = "1";
        indexedCollection.add(element);
        assertTrue(indexedCollection.remove(element));
        assertNull(indexedCollection.get(1));
    }

    @Test
    public void testRemoveAllSuccess() {
        Collection<String> elements = new ArrayList<>();
        elements.add("1");
        elements.add("2");
        indexedCollection.addAll(elements);
        assertTrue(indexedCollection.removeAll(elements));
        assertNull(indexedCollection.get(1));
        assertNull(indexedCollection.get(2));
    }

    @Test
    public void testRemoveIfSuccess() {
        indexedCollection.addAll(Arrays.asList("1", "2", "3"));
        assertTrue(indexedCollection.removeIf(s -> s.equals("2")));
        assertNull(indexedCollection.get(2));
        assertEquals("1", indexedCollection.get(1));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testRetainAllSuccess() {
        indexedCollection.addAll(Arrays.asList("1", "2", "3"));
        assertTrue(indexedCollection.retainAll(Arrays.asList("1", "3")));
        assertNull(indexedCollection.get(2));
        assertEquals("1", indexedCollection.get(1));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testClear() {
        indexedCollection.addAll(Arrays.asList("1", "2", "3"));
        indexedCollection.clear();
        assertNull(indexedCollection.get(1));
        assertNull(indexedCollection.get(2));
        assertNull(indexedCollection.get(3));
    }

    @Test
    public void testReindex() {
        originalCollection.addAll(Arrays.asList("1", "2", "3"));
        indexedCollection.reindex();
        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }
}
