
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CollectionUtils_unionTest {

    @Test
    public void testUnion_NormalCase() {
        Iterable<Integer> a = Arrays.asList(1, 2, 3);
        Iterable<Integer> b = Arrays.asList(3, 4, 5);
        Collection<Integer> result = CollectionUtils.union(a, b);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testUnion_NullInput() {
        Iterable<Integer> a = null;
        Iterable<Integer> b = Arrays.asList(1, 2, 3);
        assertThrows(NullPointerException.class, () -> CollectionUtils.union(a, b));
    }

    @Test
    public void testUnion_EmptyCollections() {
        Iterable<Integer> a = Collections.emptyList();
        Iterable<Integer> b = Collections.emptyList();
        Collection<Integer> result = CollectionUtils.union(a, b);
        assertEquals(Collections.emptyList(), result);
    }
}
