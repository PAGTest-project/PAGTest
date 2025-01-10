
package org.apache.commons.collections4.collection;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.*;

public class IndexedCollection_removeIfTest {

    @Test
    public void testRemoveIf_NullFilter() {
        IndexedCollection<String, String> indexedCollection = new IndexedCollection<>(Arrays.asList("a", "b"), obj -> obj, null, false);
        assertFalse(indexedCollection.removeIf(null));
    }

    @Test
    public void testRemoveIf_NoElementsRemoved() {
        IndexedCollection<String, String> indexedCollection = new IndexedCollection<>(Arrays.asList("a", "b"), obj -> obj, null, false);
        Predicate<String> filter = s -> s.equals("c");
        assertFalse(indexedCollection.removeIf(filter));
    }

    @Test
    public void testRemoveIf_ElementsRemoved() {
        IndexedCollection<String, String> indexedCollection = new IndexedCollection<>(Arrays.asList("a", "b"), obj -> obj, null, false);
        Predicate<String> filter = s -> s.equals("a");
        assertTrue(indexedCollection.removeIf(filter));
    }
}
