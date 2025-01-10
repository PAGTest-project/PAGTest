
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Line2D_ptSegDistSqTest {

    @Test
    public void testPtSegDistSqPointOnSideOfX1Y1() {
        double result = Line2D.ptSegDistSq(0, 0, 1, 1, -1, -1);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testPtSegDistSqPointOnSideOfX2Y2() {
        double result = Line2D.ptSegDistSq(0, 0, 1, 1, 2, 2);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testPtSegDistSqPointBetweenX1Y1AndX2Y2() {
        double result = Line2D.ptSegDistSq(0, 0, 1, 1, 0.5, 0.5);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testPtSegDistSqPointExactlyOnLine() {
        double result = Line2D.ptSegDistSq(0, 0, 1, 1, 0.5, 0.5);
        assertEquals(0.0, result, 0.0001);
    }
}
