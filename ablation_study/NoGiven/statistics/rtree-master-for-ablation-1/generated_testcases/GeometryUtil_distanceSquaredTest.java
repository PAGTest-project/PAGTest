
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GeometryUtil_distanceSquaredTest {

    @Test
    public void testDistanceSquared() {
        // Given
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 4.0;
        double y2 = 6.0;

        // When
        double result = GeometryUtil.distanceSquared(x1, y1, x2, y2);

        // Then
        assertEquals(25.0, result, 0.0001);
    }
}
