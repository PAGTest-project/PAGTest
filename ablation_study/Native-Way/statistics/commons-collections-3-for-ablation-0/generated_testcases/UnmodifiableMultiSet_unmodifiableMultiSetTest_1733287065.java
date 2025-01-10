
package org.apache.commons.collections4.multiset;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableMultiSet_unmodifiableMultiSetTest {

    @Test
    void testUnmodifiableMultiSetWithUnmodifiableInput() {
        MultiSet<String> unmodifiableMultiSet = new UnmodifiableMultiSet<>(new HashMultiSet<>());
        MultiSet<String> result = UnmodifiableMultiSet.unmodifiableMultiSet(unmodifiableMultiSet);
        assertSame(unmodifiableMultiSet, result);
    }

    @Test
    void testUnmodifiableMultiSetWithModifiableInput() {
        MultiSet<String> modifiableMultiSet = new HashMultiSet<>();
        MultiSet<String> result = UnmodifiableMultiSet.unmodifiableMultiSet(modifiableMultiSet);
        assertTrue(result instanceof UnmodifiableMultiSet);
        assertNotSame(modifiableMultiSet, result);
    }
}
