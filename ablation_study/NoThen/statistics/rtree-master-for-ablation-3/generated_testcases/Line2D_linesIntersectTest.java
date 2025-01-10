
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class Line2D_linesIntersectTest {

    @Test
    public void testLinesIntersectWhenIntersecting() {
        // Given
        double x1 = 0, y1 = 0, x2 = 2, y2 = 2;
        double x3 = 0, y3 = 2, x4 = 2, y4 = 0;

        // When
        boolean result = Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);

        // Then
        assertTrue(result);
    }

    @Test
    public void testLinesIntersectWhenNotIntersecting() {
        // Given
        double x1 = 0, y1 = 0, x2 = 1, y2 = 1;
        double x3 = 2, y3 = 2, x4 = 3, y4 = 3;

        // When
        boolean result = Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);

        // Then
        assertFalse(result);
    }
}
