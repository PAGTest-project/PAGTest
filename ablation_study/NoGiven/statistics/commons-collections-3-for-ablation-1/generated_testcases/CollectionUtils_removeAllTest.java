
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
        Equator<String> equator = new Equator<String>() {
            @Override
            public boolean equate(String a, String b) {
                return a.equals(b);
            }

            @Override
            public int hash(String o) {
                return o.hashCode();
            }
        };

        Collection<String> result = CollectionUtils.removeAll(collection, remove, equator);

        assertEquals(3, result.size());
        assertTrue(result.containsAll(collection));
    }

    @Test
    public void testRemoveAll_WithRemoval() {
        List<String> collection = Arrays.asList("a", "b", "c");
        List<String> remove = Arrays.asList("b");
        Equator<String> equator = new Equator<String>() {
            @Override
            public boolean equate(String a, String b) {
                return a.equals(b);
            }

            @Override
            public int hash(String o) {
                return o.hashCode();
            }
        };

        Collection<String> result = CollectionUtils.removeAll(collection, remove, equator);

        assertEquals(2, result.size());
        assertTrue(result.contains("a"));
        assertTrue(result.contains("c"));
        assertFalse(result.contains("b"));
    }

    @Test
    public void testRemoveAll_NullInputs() {
        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.removeAll(null, Collections.emptyList(), new Equator<String>() {
                @Override
                public boolean equate(String a, String b) {
                    return a.equals(b);
                }

                @Override
                public int hash(String o) {
                    return o.hashCode();
                }
            });
        });

        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.removeAll(Collections.emptyList(), null, new Equator<String>() {
                @Override
                public boolean equate(String a, String b) {
                    return a.equals(b);
                }

                @Override
                public int hash(String o) {
                    return o.hashCode();
                }
            });
        });

        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.removeAll(Collections.emptyList(), Collections.emptyList(), null);
        });
    }
}
