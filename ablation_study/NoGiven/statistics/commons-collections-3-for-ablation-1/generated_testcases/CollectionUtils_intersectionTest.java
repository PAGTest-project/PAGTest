
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CollectionUtils_intersectionTest {

    @Test
    public void testIntersection_NormalCase() {
        Collection<Integer> a = Arrays.asList(1, 2, 3, 4);
        Collection<Integer> b = Arrays.asList(3, 4, 5, 6);
        Collection<Integer> expected = Arrays.asList(3, 4);

        Collection<Integer> result = CollectionUtils.intersection(a, b);

        assertEquals(expected, result);
    }

    @Test
    public void testIntersection_NullInput() {
        Collection<Integer> a = null;
        Collection<Integer> b = Arrays.asList(3, 4, 5, 6);

        assertThrows(NullPointerException.class, () -> CollectionUtils.intersection(a, b));
    }

    @Test
    public void testIntersection_EmptyCollections() {
        Collection<Integer> a = Collections.emptyList();
        Collection<Integer> b = Collections.emptyList();
        Collection<Integer> expected = Collections.emptyList();

        Collection<Integer> result = CollectionUtils.intersection(a, b);

        assertEquals(expected, result);
    }
}
