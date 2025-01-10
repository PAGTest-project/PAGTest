
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
        Collection<String> unmodifiable = new UnmodifiableCollection<>(original);

        Collection<String> result = UnmodifiableCollection.unmodifiableCollection(unmodifiable);

        assertSame(unmodifiable, result);
    }

    @Test
    public void testUnmodifiableCollectionWithModifiableCollection() {
        Collection<String> original = new ArrayList<>();

        Collection<String> result = UnmodifiableCollection.unmodifiableCollection(original);

        assertTrue(result instanceof Unmodifiable);
    }
}
