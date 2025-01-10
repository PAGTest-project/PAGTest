
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ComparatorChain_equalsTest {

    private ComparatorChain<Integer> chain1;
    private ComparatorChain<Integer> chain2;
    private ComparatorChain<Integer> chain3;

    @BeforeEach
    public void setUp() {
        chain1 = new ComparatorChain<>();
        chain2 = new ComparatorChain<>(new ComparableComparator<>(), false);
        chain3 = new ComparatorChain<>(new ArrayList<>(), new BitSet());
    }

    @Test
    public void testEqualsSameInstance() {
        assertTrue(chain1.equals(chain1), "An instance should be equal to itself");
    }

    @Test
    public void testEqualsNull() {
        assertFalse(chain1.equals(null), "An instance should not be equal to null");
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(chain1.equals("Not a ComparatorChain"), "An instance should not be equal to an object of a different class");
    }

    @Test
    public void testEqualsSameState() {
        ComparatorChain<Integer> chain4 = new ComparatorChain<>(new ComparableComparator<>(), false);
        assertTrue(chain2.equals(chain4), "Two instances with the same state should be equal");
    }

    @Test
    public void testEqualsDifferentState() {
        ComparatorChain<Integer> chain4 = new ComparatorChain<>(new ComparableComparator<>(), true);
        assertFalse(chain2.equals(chain4), "Two instances with different states should not be equal");
    }

    @Test
    public void testEqualsDifferentComparatorChain() {
        List<Comparator<Integer>> comparators = new ArrayList<>();
        comparators.add(new ComparableComparator<>());
        BitSet bits = new BitSet();
        bits.set(0);
        ComparatorChain<Integer> chain4 = new ComparatorChain<>(comparators, bits);
        assertFalse(chain2.equals(chain4), "Two instances with different comparator chains should not be equal");
    }

    @Test
    public void testEqualsDifferentOrderingBits() {
        List<Comparator<Integer>> comparators = new ArrayList<>();
        comparators.add(new ComparableComparator<>());
        BitSet bits = new BitSet();
        bits.set(0);
        ComparatorChain<Integer> chain4 = new ComparatorChain<>(comparators, bits);
        assertFalse(chain2.equals(chain4), "Two instances with different ordering bits should not be equal");
    }
}
