
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class GeometryUtil_distanceTest {

    @Test
    public void testDistanceWhenIntersects() {
        // Given
        double x1 = 1, y1 = 1, x2 = 3, y2 = 3;
        double a1 = 2, b1 = 2, a2 = 4, b2 = 4;

        // When
        double result = GeometryUtil.distance(x1, y1, x2, y2, a1, b1, a2, b2);

        // Then
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testDistanceWhenNotIntersects() {
        // Given
        double x1 = 1, y1 = 1, x2 = 2, y2 = 2;
        double a1 = 3, b1 = 3, a2 = 4, b2 = 4;

        // When
        double result = GeometryUtil.distance(x1, y1, x2, y2, a1, b1, a2, b2);

        // Then
        assertEquals(Math.sqrt(2), result, 0.0001);
    }
}
