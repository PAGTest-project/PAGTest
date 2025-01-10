
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtils_addAllTest {

    @Test
    public void testAddAll_SuccessfulAddition() {
        Collection<String> collection = new ArrayList<>();
        String[] elements = {"a", "b", "c"};

        boolean result = CollectionUtils.addAll(collection, elements);

        assertTrue(result);
        assertArrayEquals(elements, collection.toArray());
    }

    @Test
    public void testAddAll_NoChange() {
        Collection<String> collection = new ArrayList<>();
        collection.add("a");
        collection.add("b");
        collection.add("c");
        String[] elements = {"a", "b", "c"};

        boolean result = CollectionUtils.addAll(collection, elements);

        assertFalse(result);
        assertArrayEquals(new String[]{"a", "b", "c"}, collection.toArray());
    }

    @Test
    public void testAddAll_NullCollection() {
        String[] elements = {"a", "b", "c"};

        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.addAll(null, elements);
        });
    }

    @Test
    public void testAddAll_NullElements() {
        Collection<String> collection = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.addAll(collection, (String[]) null);
        });
    }
}
