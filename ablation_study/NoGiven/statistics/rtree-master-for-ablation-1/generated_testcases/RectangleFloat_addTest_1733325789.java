
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Rectangle;

public class RectangleFloat_addTest {

    @Test
    public void testAddWithRectangleDouble() {
        RectangleFloat rectFloat = RectangleFloat.create(1, 1, 3, 3);
        Rectangle rectDouble = RectangleDouble.create(2, 2, 4, 4);
        Rectangle result = rectFloat.add(rectDouble);
        assertTrue(result instanceof RectangleDouble);
        assertEquals(1.0, result.x1(), 0.001);
        assertEquals(1.0, result.y1(), 0.001);
        assertEquals(4.0, result.x2(), 0.001);
        assertEquals(4.0, result.y2(), 0.001);
    }

    @Test
    public void testAddWithRectangleFloat() {
        RectangleFloat rectFloat1 = RectangleFloat.create(1, 1, 3, 3);
        RectangleFloat rectFloat2 = RectangleFloat.create(2, 2, 4, 4);
        Rectangle result = rectFloat1.add(rectFloat2);
        assertTrue(result instanceof RectangleFloat);
        assertEquals(1.0f, ((RectangleFloat) result).x1, 0.001f);
        assertEquals(1.0f, ((RectangleFloat) result).y1, 0.001f);
        assertEquals(4.0f, ((RectangleFloat) result).x2, 0.001f);
        assertEquals(4.0f, ((RectangleFloat) result).y2, 0.001f);
    }

    @Test
    public void testAddWithPointFloat() {
        RectangleFloat rectFloat = RectangleFloat.create(1, 1, 3, 3);
        PointFloat pointFloat = PointFloat.create(2, 2);
        Rectangle result = rectFloat.add(pointFloat);
        assertTrue(result instanceof RectangleFloat);
        assertEquals(1.0f, ((RectangleFloat) result).x1, 0.001f);
        assertEquals(1.0f, ((RectangleFloat) result).y1, 0.001f);
        assertEquals(3.0f, ((RectangleFloat) result).x2, 0.001f);
        assertEquals(3.0f, ((RectangleFloat) result).y2, 0.001f);
    }
}
