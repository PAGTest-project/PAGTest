
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorChain_hashCodeTest {

    @Test
    public void testHashCode() {
        // Given
        List<Comparator<String>> comparators = new ArrayList<>();
        comparators.add(Comparator.naturalOrder());
        BitSet bits = new BitSet();
        ComparatorChain<String> chain = new ComparatorChain<>(comparators, bits);

        // When
        int hash = chain.hashCode();

        // Then
        assertEquals(comparators.hashCode() ^ bits.hashCode(), hash);
    }
}
