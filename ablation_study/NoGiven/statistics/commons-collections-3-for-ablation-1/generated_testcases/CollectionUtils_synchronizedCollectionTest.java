
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class CollectionUtils_synchronizedCollectionTest {

    @Test
    void testSynchronizedCollection_withNonNullCollection() {
        Collection<String> inputCollection = new ArrayList<>();
        inputCollection.add("item1");
        inputCollection.add("item2");

        Collection<String> result = CollectionUtils.synchronizedCollection(inputCollection);

        assertNotNull(result);
        assertEquals(inputCollection.size(), result.size());
        assertTrue(result.containsAll(inputCollection));
    }

    @Test
    void testSynchronizedCollection_withNullCollection() {
        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.synchronizedCollection(null);
        });
    }
}
