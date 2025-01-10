
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
        List<Comparator<Integer>> comparators = new ArrayList<>();
        comparators.add(Comparator.naturalOrder());
        BitSet orderingBits = new BitSet();
        ComparatorChain<Integer> chain = new ComparatorChain<>(comparators, orderingBits);

        // When
        int hash = chain.hashCode();

        // Then
        assertEquals(comparators.hashCode() ^ orderingBits.hashCode(), hash);
    }
}
