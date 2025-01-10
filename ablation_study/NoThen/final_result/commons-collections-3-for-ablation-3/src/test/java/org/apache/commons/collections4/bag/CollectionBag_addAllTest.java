
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;

public class CollectionBag_addAllTest {

    @Test
    public void testAddAll() {
        // Given
        Bag<String> bag = new CollectionBag<>(new HashBag<>());
        Collection<String> coll = Arrays.asList("A", "B", "C");

        // When
        boolean changed = bag.addAll(coll);

        // Then
        assertTrue(changed);
        assertTrue(bag.containsAll(coll));
    }
}
