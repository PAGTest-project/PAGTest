
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionUtils_removeAllTest {

    @Test
    public void testRemoveAll_NoRemoval() {
        List<String> collection = Arrays.asList("a", "b", "c");
        List<String> remove = Collections.emptyList();
        Equator<String> equator = (a, b) -> a.equals(b);

        Collection<String> result = CollectionUtils.removeAll(collection, remove, equator);

        assertEquals(3, result.size());
        assertTrue(result.containsAll(collection));
    }

    @Test
    public void testRemoveAll_WithRemoval() {
        List<String> collection = Arrays.asList("a", "b", "c");
        List<String> remove = Arrays.asList("b");
        Equator<String> equator = (a, b) -> a.equals(b);

        Collection<String> result = CollectionUtils.removeAll(collection, remove, equator);

        assertEquals(2, result.size());
        assertTrue(result.contains("a"));
        assertTrue(result.contains("c"));
        assertFalse(result.contains("b"));
    }

    @Test
    public void testRemoveAll_NullInputs() {
        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.removeAll(null, Collections.emptyList(), (a, b) -> a.equals(b));
        });

        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.removeAll(Collections.emptyList(), null, (a, b) -> a.equals(b));
        });

        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.removeAll(Collections.emptyList(), Collections.emptyList(), null);
        });
    }
}
