
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
        Comparator<Object> comparator1 = Comparator.naturalOrder();
        Comparator<Object> comparator2 = Comparator.naturalOrder();
        List<Comparator<Object>> comparators = new ArrayList<>();
        comparators.add(comparator1);
        BitSet bits = new BitSet();
        ComparatorChain<Object> chain1 = new ComparatorChain<>(comparators, bits);
        ComparatorChain<Object> chain2 = new ComparatorChain<>(comparators, bits);
        ComparatorChain<Object> chain3 = new ComparatorChain<>(comparators, new BitSet());
        ComparatorChain<Object> chain4 = new ComparatorChain<>(new ArrayList<>(), bits);

        // When and Then
        assertTrue(chain1.equals(chain1)); // Same instance
        assertFalse(chain1.equals(null)); // Null object
        assertTrue(chain1.equals(chain2)); // Same comparators and bits
        assertFalse(chain1.equals(chain3)); // Different bits
        assertFalse(chain1.equals(chain4)); // Different comparators
        assertFalse(chain1.equals(new Object())); // Different class
    }
}
