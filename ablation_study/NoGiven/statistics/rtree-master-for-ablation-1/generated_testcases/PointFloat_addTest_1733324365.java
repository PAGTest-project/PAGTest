
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PointFloat_addTest {

    @Test
    public void testAddWithRectangleDouble() {
        PointFloat point = PointFloat.create(1.0f, 2.0f);
        RectangleDouble rectangleDouble = RectangleDouble.create(0.5, 1.5, 1.5, 2.5);
        Rectangle result = point.add(rectangleDouble);
        assertTrue(result instanceof RectangleDouble);
        assertEquals(0.5, result.x1(), 0.001);
        assertEquals(1.5, result.y1(), 0.001);
        assertEquals(1.5, result.x2(), 0.001);
        assertEquals(2.5, result.y2(), 0.001);
    }

    @Test
    public void testAddWithRectangleFloat() {
        PointFloat point = PointFloat.create(1.0f, 2.0f);
        RectangleFloat rectangleFloat = RectangleFloat.create(0.5f, 1.5f, 1.5f, 2.5f);
        Rectangle result = point.add(rectangleFloat);
        assertTrue(result instanceof RectangleFloat);
        assertEquals(0.5, result.x1(), 0.001);
        assertEquals(1.5, result.y1(), 0.001);
        assertEquals(1.5, result.x2(), 0.001);
        assertEquals(2.5, result.y2(), 0.001);
    }

    @Test
    public void testAddWithPointFloat() {
        PointFloat point = PointFloat.create(1.0f, 2.0f);
        PointFloat pointFloat = PointFloat.create(0.5f, 1.5f);
        Rectangle result = point.add(pointFloat);
        assertTrue(result instanceof RectangleFloat);
        assertEquals(0.5, result.x1(), 0.001);
        assertEquals(1.5, result.y1(), 0.001);
        assertEquals(1.0, result.x2(), 0.001);
        assertEquals(2.0, result.y2(), 0.001);
    }

    @Test
    public void testAddWithPointDouble() {
        PointFloat point = PointFloat.create(1.0f, 2.0f);
        PointDouble pointDouble = PointDouble.create(0.5, 1.5);
        Rectangle result = point.add(pointDouble);
        assertTrue(result instanceof RectangleDouble);
        assertEquals(0.5, result.x1(), 0.001);
        assertEquals(1.5, result.y1(), 0.001);
        assertEquals(1.0, result.x2(), 0.001);
        assertEquals(2.0, result.y2(), 0.001);
    }
}
