
package com.github.davidmoten.rtree.geometry;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Geometries_rectangleGeographicTest {

    @Test
    public void testRectangleGeographicNormalCase() {
        Rectangle result = Geometries.rectangleGeographic(10.0f, 20.0f, 30.0f, 40.0f);
        assertEquals(10.0f, result.x1(), 0.0001);
        assertEquals(20.0f, result.y1(), 0.0001);
        assertEquals(30.0f, result.x2(), 0.0001);
        assertEquals(40.0f, result.y2(), 0.0001);
    }

    @Test
    public void testRectangleGeographicWithX2LessThanX1() {
        Rectangle result = Geometries.rectangleGeographic(30.0f, 20.0f, 10.0f, 40.0f);
        assertEquals(30.0f, result.x1(), 0.0001);
        assertEquals(20.0f, result.y1(), 0.0001);
        assertEquals(370.0f, result.x2(), 0.0001);
        assertEquals(40.0f, result.y2(), 0.0001);
    }
}
