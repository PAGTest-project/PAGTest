
package org.apache.commons.collections4.multiset;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

public class UnmodifiableMultiSet_uniqueSetTest {

    @Test
    public void testUniqueSet() {
        final MultiSet<String> multiset = new HashMultiSet<>();
        multiset.add("element1");
        multiset.add("element2");
        final MultiSet<String> unmodifiableMultiSet = UnmodifiableMultiSet.unmodifiableMultiSet(multiset);
        final Set<String> uniqueSet = unmodifiableMultiSet.uniqueSet();

        assertSame(uniqueSet.size(), multiset.uniqueSet().size());
        assertTrue(uniqueSet instanceof Unmodifiable);
    }
}
