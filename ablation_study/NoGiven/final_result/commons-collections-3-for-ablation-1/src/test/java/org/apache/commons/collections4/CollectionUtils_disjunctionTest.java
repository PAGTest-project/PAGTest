
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CollectionUtils_disjunctionTest {

    @Test
    public void testDisjunction_NormalCase() {
        Collection<Integer> a = Arrays.asList(1, 2, 3, 4);
        Collection<Integer> b = Arrays.asList(3, 4, 5, 6);

        Collection<Integer> result = CollectionUtils.disjunction(a, b);

        assertEquals(Arrays.asList(1, 2, 5, 6), result);
    }

    @Test
    public void testDisjunction_NullInput() {
        Collection<Integer> a = null;
        Collection<Integer> b = Arrays.asList(3, 4, 5, 6);

        assertThrows(NullPointerException.class, () -> CollectionUtils.disjunction(a, b));
    }

    @Test
    public void testDisjunction_EmptyCollections() {
        Collection<Integer> a = Collections.emptyList();
        Collection<Integer> b = Collections.emptyList();

        Collection<Integer> result = CollectionUtils.disjunction(a, b);

        assertEquals(Collections.emptyList(), result);
    }
}
