
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BooleanComparator_compareTest {

    @Test
    public void testCompareTrueFirst() {
        BooleanComparator comparator = new BooleanComparator(true);
        assertEquals(-1, comparator.compare(Boolean.TRUE, Boolean.FALSE));
        assertEquals(1, comparator.compare(Boolean.FALSE, Boolean.TRUE));
        assertEquals(0, comparator.compare(Boolean.TRUE, Boolean.TRUE));
        assertEquals(0, comparator.compare(Boolean.FALSE, Boolean.FALSE));
    }

    @Test
    public void testCompareFalseFirst() {
        BooleanComparator comparator = new BooleanComparator(false);
        assertEquals(1, comparator.compare(Boolean.TRUE, Boolean.FALSE));
        assertEquals(-1, comparator.compare(Boolean.FALSE, Boolean.TRUE));
        assertEquals(0, comparator.compare(Boolean.TRUE, Boolean.TRUE));
        assertEquals(0, comparator.compare(Boolean.FALSE, Boolean.FALSE));
    }
}
