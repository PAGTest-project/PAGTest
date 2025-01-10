
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class RectangleDouble_intersectsTest {

    @Test
    public void testIntersectsWithRectangleDouble() {
        RectangleDouble rd1 = RectangleDouble.create(0, 0, 10, 10);
        RectangleDouble rd2 = RectangleDouble.create(5, 5, 15, 15);
        assertTrue(rd1.intersects(rd2));
    }

    @Test
    public void testIntersectsWithNonRectangleDouble() {
        RectangleDouble rd = RectangleDouble.create(0, 0, 10, 10);
        Rectangle mockRectangle = mock(Rectangle.class);
        when(mockRectangle.x1()).thenReturn(5.0);
        when(mockRectangle.y1()).thenReturn(5.0);
        when(mockRectangle.x2()).thenReturn(15.0);
        when(mockRectangle.y2()).thenReturn(15.0);
        assertTrue(rd.intersects(mockRectangle));
    }

    @Test
    public void testDoesNotIntersectWithRectangleDouble() {
        RectangleDouble rd1 = RectangleDouble.create(0, 0, 10, 10);
        RectangleDouble rd2 = RectangleDouble.create(11, 11, 20, 20);
        assertFalse(rd1.intersects(rd2));
    }

    @Test
    public void testDoesNotIntersectWithNonRectangleDouble() {
        RectangleDouble rd = RectangleDouble.create(0, 0, 10, 10);
        Rectangle mockRectangle = mock(Rectangle.class);
        when(mockRectangle.x1()).thenReturn(11.0);
        when(mockRectangle.y1()).thenReturn(11.0);
        when(mockRectangle.x2()).thenReturn(20.0);
        when(mockRectangle.y2()).thenReturn(20.0);
        assertFalse(rd.intersects(mockRectangle));
    }
}
