
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BooleanComparator_compareTest {

    @Test
    public void testCompareTrueFirst() {
        BooleanComparator comparator = BooleanComparator.getTrueFirstComparator();

        assertEquals(-1, comparator.compare(Boolean.TRUE, Boolean.FALSE));
        assertEquals(1, comparator.compare(Boolean.FALSE, Boolean.TRUE));
        assertEquals(0, comparator.compare(Boolean.TRUE, Boolean.TRUE));
        assertEquals(0, comparator.compare(Boolean.FALSE, Boolean.FALSE));
    }

    @Test
    public void testCompareFalseFirst() {
        BooleanComparator comparator = BooleanComparator.getFalseFirstComparator();

        assertEquals(1, comparator.compare(Boolean.TRUE, Boolean.FALSE));
        assertEquals(-1, comparator.compare(Boolean.FALSE, Boolean.TRUE));
        assertEquals(0, comparator.compare(Boolean.TRUE, Boolean.TRUE));
        assertEquals(0, comparator.compare(Boolean.FALSE, Boolean.FALSE));
    }

    @Test
    public void testCompareNullArguments() {
        BooleanComparator comparator = BooleanComparator.getTrueFirstComparator();

        assertThrows(NullPointerException.class, () -> comparator.compare(null, Boolean.TRUE));
        assertThrows(NullPointerException.class, () -> comparator.compare(Boolean.TRUE, null));
        assertThrows(NullPointerException.class, () -> comparator.compare(null, null));
    }
}
