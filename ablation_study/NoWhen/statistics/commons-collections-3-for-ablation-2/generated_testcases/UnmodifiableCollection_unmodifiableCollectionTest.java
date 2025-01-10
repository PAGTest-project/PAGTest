
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnmodifiableCollection_unmodifiableCollectionTest {

    @Test
    public void testUnmodifiableCollectionWithUnmodifiableInput() {
        Collection<String> unmodifiableColl = new UnmodifiableCollection<>(new ArrayList<>());
        Collection<String> result = org.apache.commons.collections4.CollectionUtils.unmodifiableCollection(unmodifiableColl);
        assertSame(unmodifiableColl, result);
    }

    @Test
    public void testUnmodifiableCollectionWithModifiableInput() {
        Collection<String> modifiableColl = new ArrayList<>();
        Collection<String> result = org.apache.commons.collections4.CollectionUtils.unmodifiableCollection(modifiableColl);
        assertThrows(UnsupportedOperationException.class, () -> result.add("test"));
    }
}
