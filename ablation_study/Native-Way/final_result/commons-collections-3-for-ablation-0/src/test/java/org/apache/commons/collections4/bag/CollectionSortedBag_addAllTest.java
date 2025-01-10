
package org.apache.commons.collections4.bag;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class CollectionSortedBag_addAllTest {

    @Test
    void testAddAll_EmptyCollection() {
        CollectionSortedBag<Integer> bag = new CollectionSortedBag<>(new TreeBag<>());
        assertFalse(bag.addAll(Collections.emptyList()));
    }

    @Test
    void testAddAll_NonEmptyCollection() {
        CollectionSortedBag<Integer> bag = new CollectionSortedBag<>(new TreeBag<>());
        assertTrue(bag.addAll(Arrays.asList(1, 2, 3)));
    }
}
