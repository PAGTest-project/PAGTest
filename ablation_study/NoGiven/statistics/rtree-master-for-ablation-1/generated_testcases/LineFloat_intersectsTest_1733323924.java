
package com.github.davidmoten.rtree.geometry.internal;

import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LineFloat_intersectsTest {

    @Test
    public void testIntersects_LineIntersectsRectangle() {
        // Given
        LineFloat line = LineFloat.create(0, 0, 2, 2);
        Rectangle rectangle = mock(Rectangle.class);
        when(rectangle.x1()).thenReturn(1.0);
        when(rectangle.y1()).thenReturn(1.0);
        when(rectangle.x2()).thenReturn(3.0);
        when(rectangle.y2()).thenReturn(3.0);

        // When
        boolean result = line.intersects(rectangle);

        // Then
        assertTrue(result);
    }

    @Test
    public void testIntersects_LineDoesNotIntersectRectangle() {
        // Given
        LineFloat line = LineFloat.create(0, 0, 1, 1);
        Rectangle rectangle = mock(Rectangle.class);
        when(rectangle.x1()).thenReturn(2.0);
        when(rectangle.y1()).thenReturn(2.0);
        when(rectangle.x2()).thenReturn(3.0);
        when(rectangle.y2()).thenReturn(3.0);

        // When
        boolean result = line.intersects(rectangle);

        // Then
        assertFalse(result);
    }
}
