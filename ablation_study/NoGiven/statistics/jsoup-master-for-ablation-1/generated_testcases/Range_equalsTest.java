
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Range_equalsTest {
    private Range trackedRange;
    private Range untrackedRange;
    private Range implicitRange;

    @BeforeEach
    public void setUp() {
        trackedRange = new Range(new Range.Position(0, 1, 1), new Range.Position(19, 2, 12));
        untrackedRange = Range.Untracked;
        implicitRange = new Range(new Range.Position(0, 1, 1), new Range.Position(0, 1, 1));
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(trackedRange.equals(trackedRange));
    }

    @Test
    public void testEquals_NullObject() {
        assertFalse(trackedRange.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(trackedRange.equals("Not a Range"));
    }

    @Test
    public void testEquals_TrackedRangesEqual() {
        Range anotherTrackedRange = new Range(new Range.Position(0, 1, 1), new Range.Position(19, 2, 12));
        assertTrue(trackedRange.equals(anotherTrackedRange));
    }

    @Test
    public void testEquals_TrackedRangesNotEqual() {
        Range differentTrackedRange = new Range(new Range.Position(1, 1, 2), new Range.Position(20, 2, 13));
        assertFalse(trackedRange.equals(differentTrackedRange));
    }

    @Test
    public void testEquals_UntrackedRange() {
        assertFalse(trackedRange.equals(untrackedRange));
    }

    @Test
    public void testEquals_ImplicitRange() {
        assertTrue(implicitRange.equals(implicitRange));
        assertFalse(trackedRange.equals(implicitRange));
    }

    @Test
    public void testHashCodeConsistency() {
        Range anotherTrackedRange = new Range(new Range.Position(0, 1, 1), new Range.Position(19, 2, 12));
        assertEquals(trackedRange.hashCode(), anotherTrackedRange.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("1,1:0-2,12:19", trackedRange.toString());
        assertEquals("-1,-1:-1--1,-1:-1", untrackedRange.toString());
        assertEquals("1,1:0-1,1:0", implicitRange.toString());
    }
}
