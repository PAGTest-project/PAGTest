
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooleanComparator_hashCodeTest {

    @Test
    public void testHashCodeTrueFirst() {
        BooleanComparator comparator = BooleanComparator.getTrueFirstComparator();
        int expectedHash = -1 * "BooleanComparator".hashCode();
        assertEquals(expectedHash, comparator.hashCode());
    }

    @Test
    public void testHashCodeFalseFirst() {
        BooleanComparator comparator = BooleanComparator.getFalseFirstComparator();
        int expectedHash = "BooleanComparator".hashCode();
        assertEquals(expectedHash, comparator.hashCode());
    }
}
