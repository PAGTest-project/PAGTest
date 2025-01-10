
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtils_isSubCollectionTest {

    @Test
    public void testIsSubCollection_True() {
        Collection<?> a = Arrays.asList(1, 2, 3);
        Collection<?> b = Arrays.asList(1, 2, 3, 4, 5);
        assertTrue(CollectionUtils.isSubCollection(a, b));
    }

    @Test
    public void testIsSubCollection_False() {
        Collection<?> a = Arrays.asList(1, 2, 3, 6);
        Collection<?> b = Arrays.asList(1, 2, 3, 4, 5);
        assertFalse(CollectionUtils.isSubCollection(a, b));
    }

    @Test
    public void testIsSubCollection_NullA() {
        Collection<?> b = Arrays.asList(1, 2, 3, 4, 5);
        assertThrows(NullPointerException.class, () -> CollectionUtils.isSubCollection(null, b));
    }

    @Test
    public void testIsSubCollection_NullB() {
        Collection<?> a = Arrays.asList(1, 2, 3);
        assertThrows(NullPointerException.class, () -> CollectionUtils.isSubCollection(a, null));
    }
}
