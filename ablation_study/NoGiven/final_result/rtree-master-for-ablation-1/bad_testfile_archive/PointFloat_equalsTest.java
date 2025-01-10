
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PointFloat_equalsTest {

    @Test
    public void testEqualsSameInstance() {
        PointFloat point = PointFloat.create(1.0f, 2.0f);
        assertTrue(point.equals(point));
    }

    @Test
    public void testEqualsNull() {
        PointFloat point = PointFloat.create(1.0f, 2.0f);
        assertFalse(point.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        PointFloat point = PointFloat.create(1.0f, 2.0f);
        Object obj = new Object();
        assertFalse(point.equals(obj));
    }

    @Test
    public void testEqualsDifferentX() {
        PointFloat point1 = PointFloat.create(1.0f, 2.0f);
        PointFloat point2 = PointFloat.create(3.0f, 2.0f);
        assertFalse(point1.equals(point2));
    }

    @Test
    public void testEqualsDifferentY() {
        PointFloat point1 = PointFloat.create(1.0f, 2.0f);
        PointFloat point2 = PointFloat.create(1.0f, 3.0f);
        assertFalse(point1.equals(point2));
    }

    @Test
    public void testEqualsSameCoordinates() {
        PointFloat point1 = PointFloat.create(1.0f, 2.0f);
        PointFloat point2 = PointFloat.create(1.0f, 2.0f);
        assertTrue(point1.equals(point2));
    }
}
