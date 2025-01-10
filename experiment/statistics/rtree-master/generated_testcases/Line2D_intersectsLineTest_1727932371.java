
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class Line2D_intersectsLineTest {

    @Test
    public void testIntersectsLine_IntersectingLines() {
        Line2D line1 = new Line2D(0, 0, 2, 2);
        Line2D line2 = new Line2D(0, 2, 2, 0);
        assertTrue(line1.intersectsLine(line2));
    }

    @Test
    public void testIntersectsLine_NonIntersectingLines() {
        Line2D line1 = new Line2D(0, 0, 2, 2);
        Line2D line2 = new Line2D(0, 3, 3, 0);
        assertFalse(line1.intersectsLine(line2));
    }
}
