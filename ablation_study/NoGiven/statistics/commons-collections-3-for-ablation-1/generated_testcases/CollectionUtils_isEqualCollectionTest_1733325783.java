
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

class CollectionUtils_isEqualCollectionTest {

    @Test
    void testIsEqualCollection_EqualCollections() {
        Collection<String> a = Arrays.asList("a", "b", "c");
        Collection<String> b = Arrays.asList("a", "b", "c");
        Equator<String> equator = (s1, s2) -> s1.equals(s2);

        assertTrue(CollectionUtils.isEqualCollection(a, b, equator));
    }

    @Test
    void testIsEqualCollection_DifferentSizes() {
        Collection<String> a = Arrays.asList("a", "b");
        Collection<String> b = Arrays.asList("a", "b", "c");
        Equator<String> equator = (s1, s2) -> s1.equals(s2);

        assertFalse(CollectionUtils.isEqualCollection(a, b, equator));
    }

    @Test
    void testIsEqualCollection_NullCollections() {
        Collection<String> a = null;
        Collection<String> b = Arrays.asList("a", "b", "c");
        Equator<String> equator = (s1, s2) -> s1.equals(s2);

        assertThrows(NullPointerException.class, () -> CollectionUtils.isEqualCollection(a, b, equator));
    }

    @Test
    void testIsEqualCollection_NullEquator() {
        Collection<String> a = Arrays.asList("a", "b", "c");
        Collection<String> b = Arrays.asList("a", "b", "c");
        Equator<String> equator = null;

        assertThrows(NullPointerException.class, () -> CollectionUtils.isEqualCollection(a, b, equator));
    }
}
