
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorChain_addComparatorTest {

    @Test
    public void testAddComparator() {
        // Given
        List<Comparator<String>> comparators = new ArrayList<>();
        BitSet bits = new BitSet();
        ComparatorChain<String> chain = new ComparatorChain<>(comparators, bits);
        Comparator<String> comparator = Comparator.naturalOrder();

        // When
        chain.addComparator(comparator, true);

        // Then
        assertEquals(1, chain.size());
        assertTrue(bits.get(0));
    }
}
