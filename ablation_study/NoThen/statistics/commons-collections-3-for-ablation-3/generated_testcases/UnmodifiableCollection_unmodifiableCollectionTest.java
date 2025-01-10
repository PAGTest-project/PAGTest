
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableCollection_unmodifiableCollectionTest {

    @Test
    public void testUnmodifiableCollectionWithUnmodifiableCollection() {
        Collection<String> original = new ArrayList<>();
        original.add("test");
        Collection<String> unmodifiable = UnmodifiableCollection.unmodifiableCollection(original);

        // Ensure the returned collection is an instance of UnmodifiableCollection
        assertTrue(unmodifiable instanceof UnmodifiableCollection);

        // Ensure the original collection is not modified
        assertSame(original, ((UnmodifiableCollection<String>) unmodifiable).decorated());
    }

    @Test
    public void testUnmodifiableCollectionWithAlreadyUnmodifiableCollection() {
        Collection<String> original = new ArrayList<>();
        original.add("test");
        Collection<String> unmodifiable = UnmodifiableCollection.unmodifiableCollection(original);
        Collection<String> alreadyUnmodifiable = UnmodifiableCollection.unmodifiableCollection(unmodifiable);

        // Ensure the returned collection is the same instance as the input unmodifiable collection
        assertSame(unmodifiable, alreadyUnmodifiable);
    }
}
