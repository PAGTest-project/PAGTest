
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Line2D_ptSegDistTest {

    @Test
    public void testPtSegDist() {
        double result = Line2D.ptSegDist(0, 0, 1, 1, 0, 1);
        assertEquals(Math.sqrt(2) / 2, result, 0.0001);
    }
}
