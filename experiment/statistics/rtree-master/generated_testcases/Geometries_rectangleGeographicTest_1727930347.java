
package com.github.davidmoten.rtree.geometry;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Geometries_rectangleGeographicTest {

    @Test
    public void testRectangleGeographicNormalization() {
        Rectangle rectangle = Geometries.rectangleGeographic(181, 25, 182, 26);
        assertEquals(-179, rectangle.x1(), 0.0001);
        assertEquals(-178, rectangle.x2(), 0.0001);
        assertEquals(25, rectangle.y1(), 0.0001);
        assertEquals(26, rectangle.y2(), 0.0001);
    }

    @Test
    public void testRectangleGeographicNoNormalizationNeeded() {
        Rectangle rectangle = Geometries.rectangleGeographic(10, 20, 11, 21);
        assertEquals(10, rectangle.x1(), 0.0001);
        assertEquals(11, rectangle.x2(), 0.0001);
        assertEquals(20, rectangle.y1(), 0.0001);
        assertEquals(21, rectangle.y2(), 0.0001);
    }

    @Test
    public void testRectangleGeographicCrossing180thMeridian() {
        Rectangle rectangle = Geometries.rectangleGeographic(-179, 25, 179, 26);
        assertEquals(-179, rectangle.x1(), 0.0001);
        assertEquals(181, rectangle.x2(), 0.0001);
        assertEquals(25, rectangle.y1(), 0.0001);
        assertEquals(26, rectangle.y2(), 0.0001);
    }
}
