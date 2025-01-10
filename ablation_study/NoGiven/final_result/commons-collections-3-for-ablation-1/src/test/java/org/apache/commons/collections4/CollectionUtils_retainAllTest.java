
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtils_retainAllTest {

    @Test
    public void testRetainAll_NormalCase() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        Collection<Integer> retain = Arrays.asList(3, 4, 5, 6);

        Collection<Integer> result = CollectionUtils.retainAll(collection, retain);

        assertEquals(Arrays.asList(3, 4, 5), result);
    }

    @Test
    public void testRetainAll_NullCollection() {
        Collection<Integer> retain = Arrays.asList(3, 4, 5, 6);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            CollectionUtils.retainAll(null, retain);
        });

        assertEquals("collection", exception.getMessage());
    }

    @Test
    public void testRetainAll_NullRetain() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            CollectionUtils.retainAll(collection, null);
        });

        assertEquals("retain", exception.getMessage());
    }
}
