
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorChain_hashCodeTest {

    @Test
    public void testHashCode_BothNonNull() {
        List<Comparator<String>> comparators = new ArrayList<>();
        comparators.add(Comparator.naturalOrder());
        BitSet bits = new BitSet();
        ComparatorChain<String> chain = new ComparatorChain<>(comparators, bits);

        int expectedHash = comparators.hashCode() ^ bits.hashCode();
        assertEquals(expectedHash, chain.hashCode());
    }

    @Test
    public void testHashCode_ComparatorChainNull() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        chain.setOrderingBits(new BitSet());

        int expectedHash = chain.getOrderingBits().hashCode();
        assertEquals(expectedHash, chain.hashCode());
    }

    @Test
    public void testHashCode_OrderingBitsNull() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        chain.setComparatorChain(new ArrayList<>());
        chain.getComparatorChain().add(Comparator.naturalOrder());

        int expectedHash = chain.getComparatorChain().hashCode();
        assertEquals(expectedHash, chain.hashCode());
    }

    @Test
    public void testHashCode_BothNull() {
        ComparatorChain<String> chain = new ComparatorChain<>();

        int expectedHash = 0;
        assertEquals(expectedHash, chain.hashCode());
    }
}
