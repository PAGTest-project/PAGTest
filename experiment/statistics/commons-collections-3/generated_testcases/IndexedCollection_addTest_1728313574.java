
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.collections4.MultiMapUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_addTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;
    private Transformer<String, Integer> keyTransformer;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>();
        keyTransformer = s -> Integer.valueOf(s);
        indexedCollection = new IndexedCollection<>(originalCollection, keyTransformer,
                MultiMapUtils.multiValueMap(new HashMap<>()), true);
    }

    @Test
    public void testAddSuccessfullyAddsElementAndUpdatesIndex() {
        String element = "1";
        assertTrue(indexedCollection.add(element));
        assertEquals(element, indexedCollection.get(1));
    }

    @Test
    public void testAddFailsWhenUniqueIndexConstraintViolated() {
        String element1 = "1";
        String element2 = "1";
        indexedCollection.add(element1);
        assertThrows(IllegalArgumentException.class, () -> indexedCollection.add(element2));
    }
}
