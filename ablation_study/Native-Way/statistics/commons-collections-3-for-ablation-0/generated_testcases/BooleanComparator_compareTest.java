
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BooleanComparator_compareTest {

    private BooleanComparator trueFirstComparator;
    private BooleanComparator falseFirstComparator;

    @BeforeEach
    public void setUp() {
        trueFirstComparator = new BooleanComparator(true);
        falseFirstComparator = new BooleanComparator(false);
    }

    @Test
    public void testCompareTrueFirst_TrueAndTrue() {
        assertEquals(0, trueFirstComparator.compare(Boolean.TRUE, Boolean.TRUE));
    }

    @Test
    public void testCompareTrueFirst_TrueAndFalse() {
        assertEquals(-1, trueFirstComparator.compare(Boolean.TRUE, Boolean.FALSE));
    }

    @Test
    public void testCompareTrueFirst_FalseAndTrue() {
        assertEquals(1, trueFirstComparator.compare(Boolean.FALSE, Boolean.TRUE));
    }

    @Test
    public void testCompareTrueFirst_FalseAndFalse() {
        assertEquals(0, trueFirstComparator.compare(Boolean.FALSE, Boolean.FALSE));
    }

    @Test
    public void testCompareFalseFirst_TrueAndTrue() {
        assertEquals(0, falseFirstComparator.compare(Boolean.TRUE, Boolean.TRUE));
    }

    @Test
    public void testCompareFalseFirst_TrueAndFalse() {
        assertEquals(1, falseFirstComparator.compare(Boolean.TRUE, Boolean.FALSE));
    }

    @Test
    public void testCompareFalseFirst_FalseAndTrue() {
        assertEquals(-1, falseFirstComparator.compare(Boolean.FALSE, Boolean.TRUE));
    }

    @Test
    public void testCompareFalseFirst_FalseAndFalse() {
        assertEquals(0, falseFirstComparator.compare(Boolean.FALSE, Boolean.FALSE));
    }
}
