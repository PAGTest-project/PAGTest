
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PointDouble_equalsTest {

    @Test
    public void testEqualsSameInstance() {
        PointDouble point = PointDouble.create(1.0, 2.0);
        assertTrue(point.equals(point));
    }

    @Test
    public void testEqualsNull() {
        PointDouble point = PointDouble.create(1.0, 2.0);
        assertFalse(point.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        PointDouble point = PointDouble.create(1.0, 2.0);
        assertFalse(point.equals(new Object()));
    }

    @Test
    public void testEqualsDifferentCoordinates() {
        PointDouble point1 = PointDouble.create(1.0, 2.0);
        PointDouble point2 = PointDouble.create(3.0, 4.0);
        assertFalse(point1.equals(point2));
    }

    @Test
    public void testEqualsSameCoordinates() {
        PointDouble point1 = PointDouble.create(1.0, 2.0);
        PointDouble point2 = PointDouble.create(1.0, 2.0);
        assertTrue(point1.equals(point2));
    }
}
