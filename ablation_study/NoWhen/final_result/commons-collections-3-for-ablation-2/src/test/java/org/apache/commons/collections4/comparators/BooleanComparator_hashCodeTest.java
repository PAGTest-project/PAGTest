
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BooleanComparator_hashCodeTest {

    @Test
    public void testHashCodeTrueFirst() {
        BooleanComparator comparator = new BooleanComparator(true);
        int expectedHash = "BooleanComparator".hashCode();
        assertEquals(-1 * expectedHash, comparator.hashCode());
    }

    @Test
    public void testHashCodeFalseFirst() {
        BooleanComparator comparator = new BooleanComparator(false);
        int expectedHash = "BooleanComparator".hashCode();
        assertEquals(expectedHash, comparator.hashCode());
    }

    @Test
    public void testHashCodeConsistencyWithEquals() {
        BooleanComparator comparator1 = new BooleanComparator(true);
        BooleanComparator comparator2 = new BooleanComparator(true);
        BooleanComparator comparator3 = new BooleanComparator(false);

        assertAll("hashCode consistency with equals",
            () -> assertEquals(comparator1.hashCode(), comparator2.hashCode()),
            () -> assertNotEquals(comparator1.hashCode(), comparator3.hashCode())
        );
    }
}
