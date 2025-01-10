
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorChain_equalsTest {

    @Test
    void testEquals() {
        // Given
        Comparator<Integer> comparator1 = Comparator.naturalOrder();
        Comparator<Integer> comparator2 = Comparator.reverseOrder();
        List<Comparator<Integer>> comparators = new ArrayList<>();
        comparators.add(comparator1);
        BitSet bits = new BitSet();
        ComparatorChain<Integer> chain1 = new ComparatorChain<>(comparators, bits);

        List<Comparator<Integer>> comparators2 = new ArrayList<>();
        comparators2.add(comparator1);
        ComparatorChain<Integer> chain2 = new ComparatorChain<>(comparators2, bits);

        List<Comparator<Integer>> comparators3 = new ArrayList<>();
        comparators3.add(comparator2);
        ComparatorChain<Integer> chain3 = new ComparatorChain<>(comparators3, bits);

        // When and Then
        assertTrue(chain1.equals(chain2)); // Same comparators and bits
        assertFalse(chain1.equals(chain3)); // Different comparators
        assertFalse(chain1.equals(null)); // Null object
        assertFalse(chain1.equals(new Object())); // Different class
        assertTrue(chain1.equals(chain1)); // Same instance
    }
}
