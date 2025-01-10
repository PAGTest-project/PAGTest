
package com.github.davidmoten.rtree.geometry.internal;

import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LineDouble_distanceTest {

    @Test
    public void testDistanceWhenRectangleContainsLinePoints() {
        Rectangle mockRectangle = mock(Rectangle.class);
        when(mockRectangle.contains(anyDouble(), anyDouble())).thenReturn(true);

        LineDouble line = LineDouble.create(1.0, 1.0, 2.0, 2.0);
        double result = line.distance(mockRectangle);

        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void testDistanceWhenRectangleDoesNotContainLinePoints() {
        Rectangle mockRectangle = mock(Rectangle.class);
        when(mockRectangle.contains(anyDouble(), anyDouble())).thenReturn(false);
        when(mockRectangle.x1()).thenReturn(0.0);
        when(mockRectangle.y1()).thenReturn(0.0);
        when(mockRectangle.x2()).thenReturn(3.0);
        when(mockRectangle.y2()).thenReturn(3.0);

        LineDouble line = LineDouble.create(1.0, 1.0, 2.0, 2.0);
        double result = line.distance(mockRectangle);

        assertEquals(0.0, result, 0.0001);
    }
}
