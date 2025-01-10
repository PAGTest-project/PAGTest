
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

public class UnmodifiableSet_unmodifiableSetTest {

    @Test
    public void testUnmodifiableSet_WithUnmodifiableSet() {
        Set<String> originalSet = new HashSet<>();
        originalSet.add("element1");
        Set<String> unmodifiableSet = new UnmodifiableSet<>(originalSet);

        Set<String> result = UnmodifiableSet.unmodifiableSet(unmodifiableSet);

        assertSame(unmodifiableSet, result);
    }

    @Test
    public void testUnmodifiableSet_WithModifiableSet() {
        Set<String> originalSet = new HashSet<>();
        originalSet.add("element1");

        Set<String> result = UnmodifiableSet.unmodifiableSet(originalSet);

        assertTrue(result instanceof UnmodifiableSet);
    }
}
