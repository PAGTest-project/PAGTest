
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtils_unmodifiableCollectionTest {

    @Test
    public void testUnmodifiableCollection_NonNullCollection() {
        Collection<String> collection = new ArrayList<>();
        collection.add("item1");
        collection.add("item2");

        Collection<String> unmodifiableCollection = CollectionUtils.unmodifiableCollection(collection);

        assertEquals(2, unmodifiableCollection.size());
        assertTrue(unmodifiableCollection.contains("item1"));
        assertTrue(unmodifiableCollection.contains("item2"));
    }

    @Test
    public void testUnmodifiableCollection_NullCollection() {
        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.unmodifiableCollection(null);
        });
    }
}
