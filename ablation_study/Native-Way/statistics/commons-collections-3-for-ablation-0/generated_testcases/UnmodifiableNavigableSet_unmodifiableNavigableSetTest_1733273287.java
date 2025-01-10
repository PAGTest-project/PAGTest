
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.NavigableSet;
import java.util.TreeSet;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

public class UnmodifiableNavigableSet_unmodifiableNavigableSetTest {

    @Test
    public void testUnmodifiableNavigableSet_UnmodifiableInstance() {
        NavigableSet<String> originalSet = new TreeSet<>();
        NavigableSet<String> unmodifiableSet = new UnmodifiableNavigableSet<>(originalSet);

        NavigableSet<String> result = UnmodifiableNavigableSet.unmodifiableNavigableSet(unmodifiableSet);

        assertSame(unmodifiableSet, result);
    }

    @Test
    public void testUnmodifiableNavigableSet_ModifiableInstance() {
        NavigableSet<String> originalSet = new TreeSet<>();

        NavigableSet<String> result = UnmodifiableNavigableSet.unmodifiableNavigableSet(originalSet);

        assertNotSame(originalSet, result);
        assertSame(UnmodifiableNavigableSet.class, result.getClass());
    }
}
