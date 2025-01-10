
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorChain_equalsTest {

    @Test
    void testEquals_SameInstance() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        assertTrue(chain.equals(chain));
    }

    @Test
    void testEquals_NullObject() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        assertFalse(chain.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        Object obj = new Object();
        assertFalse(chain.equals(obj));
    }

    @Test
    void testEquals_SameClassDifferentState() {
        ComparatorChain<String> chain1 = new ComparatorChain<>(List.of(Comparator.naturalOrder()), new BitSet());
        ComparatorChain<String> chain2 = new ComparatorChain<>(List.of(Comparator.reverseOrder()), new BitSet());
        assertFalse(chain1.equals(chain2));
    }

    @Test
    void testEquals_SameClassSameState() {
        List<Comparator<String>> comparators = new ArrayList<>();
        comparators.add(Comparator.naturalOrder());
        BitSet bits = new BitSet();
        ComparatorChain<String> chain1 = new ComparatorChain<>(comparators, bits);
        ComparatorChain<String> chain2 = new ComparatorChain<>(comparators, bits);
        assertTrue(chain1.equals(chain2));
    }
}
