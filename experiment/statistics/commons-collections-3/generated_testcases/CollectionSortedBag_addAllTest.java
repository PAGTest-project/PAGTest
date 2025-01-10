
package org.apache.commons.collections4.bag;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionSortedBag_addAllTest {

    @Test
    public void testAddAll() {
        // Given
        CollectionSortedBag<Integer> bag = new CollectionSortedBag<>(new TreeBag<>());
        Collection<Integer> coll = Arrays.asList(1, 2, 3);

        // When
        boolean result = bag.addAll(coll);

        // Then
        assertTrue(result);
        assertEquals(3, bag.size());
        assertTrue(bag.containsAll(coll));
    }
}
