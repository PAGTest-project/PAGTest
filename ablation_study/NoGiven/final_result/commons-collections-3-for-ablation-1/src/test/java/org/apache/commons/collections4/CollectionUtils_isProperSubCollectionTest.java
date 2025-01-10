
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtils_isProperSubCollectionTest {

    @Test
    void testIsProperSubCollection_ProperSubCollection() {
        Collection<?> a = Arrays.asList(1, 2);
        Collection<?> b = Arrays.asList(1, 2, 3);
        assertTrue(CollectionUtils.isProperSubCollection(a, b));
    }

    @Test
    void testIsProperSubCollection_NotProperSubCollection() {
        Collection<?> a = Arrays.asList(1, 2, 3);
        Collection<?> b = Arrays.asList(1, 2);
        assertFalse(CollectionUtils.isProperSubCollection(a, b));
    }

    @Test
    void testIsProperSubCollection_NullA() {
        Collection<?> b = Arrays.asList(1, 2, 3);
        assertThrows(NullPointerException.class, () -> CollectionUtils.isProperSubCollection(null, b));
    }

    @Test
    void testIsProperSubCollection_NullB() {
        Collection<?> a = Arrays.asList(1, 2);
        assertThrows(NullPointerException.class, () -> CollectionUtils.isProperSubCollection(a, null));
    }
}
