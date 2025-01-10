
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BooleanComparator_hashCodeTest {

    @Test
    public void testHashCodeTrueFirst() {
        BooleanComparator comparator = new BooleanComparator(true);
        int expectedHash = -1 * "BooleanComparator".hashCode();
        assertEquals(expectedHash, comparator.hashCode());
    }

    @Test
    public void testHashCodeFalseFirst() {
        BooleanComparator comparator = new BooleanComparator(false);
        int expectedHash = "BooleanComparator".hashCode();
        assertEquals(expectedHash, comparator.hashCode());
    }
}
