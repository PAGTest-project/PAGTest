
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Line2D_ptSegDistSqTest {

    @Test
    public void testPtSegDistSq_PointOnSideOfX1Y1() {
        double result = Line2D.ptSegDistSq(0, 0, 2, 2, -1, -1);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testPtSegDistSq_PointOnSideOfX2Y2() {
        double result = Line2D.ptSegDistSq(0, 0, 2, 2, 3, 3);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testPtSegDistSq_PointBetweenX1Y1AndX2Y2() {
        double result = Line2D.ptSegDistSq(0, 0, 2, 2, 1, 1);
        assertEquals(0.0, result, 0.0001);
    }
}
