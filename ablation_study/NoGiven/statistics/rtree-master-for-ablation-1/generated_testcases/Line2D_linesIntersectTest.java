
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class Line2D_linesIntersectTest {

    @Test
    public void testLinesIntersect() {
        // Test case 1: Lines intersect
        assertTrue(Line2D.linesIntersect(0, 0, 2, 2, 0, 2, 2, 0));

        // Test case 2: Lines do not intersect
        assertFalse(Line2D.linesIntersect(0, 0, 2, 2, 3, 3, 4, 4));
    }
}
