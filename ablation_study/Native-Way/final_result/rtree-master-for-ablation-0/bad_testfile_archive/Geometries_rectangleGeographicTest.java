
package com.github.davidmoten.rtree.geometry;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Geometries_rectangleGeographicTest {

    private static final double PRECISION = 0.000001;

    @Test
    public void testRectangleGeographicNormalCase() {
        Rectangle result = Geometries.rectangleGeographic(10, 20, 30, 40);
        assertEquals(10, result.x1(), PRECISION);
        assertEquals(20, result.y1(), PRECISION);
        assertEquals(30, result.x2(), PRECISION);
        assertEquals(40, result.y2(), PRECISION);
    }

    @Test
    public void testRectangleGeographicCrossingAntiMeridian() {
        Rectangle result = Geometries.rectangleGeographic(170, 20, -170, 40);
        assertEquals(170, result.x1(), PRECISION);
        assertEquals(20, result.y1(), PRECISION);
        assertEquals(190, result.x2(), PRECISION);
        assertEquals(40, result.y2(), PRECISION);
    }

    @Test
    public void testRectangleGeographicSameLongitude() {
        Rectangle result = Geometries.rectangleGeographic(10, 20, 10, 40);
        assertEquals(10, result.x1(), PRECISION);
        assertEquals(20, result.y1(), PRECISION);
        assertEquals(10, result.x2(), PRECISION);
        assertEquals(40, result.y2(), PRECISION);
    }

    @Test
    public void testRectangleGeographicNegativeLongitude() {
        Rectangle result = Geometries.rectangleGeographic(-10, 20, -30, 40);
        assertEquals(-10, result.x1(), PRECISION);
        assertEquals(20, result.y1(), PRECISION);
        assertEquals(-30, result.x2(), PRECISION);
        assertEquals(40, result.y2(), PRECISION);
    }

    @Test
    public void testRectangleGeographicNormalizeLongitude() {
        Rectangle result = Geometries.rectangleGeographic(190, 20, 210, 40);
        assertEquals(-170, result.x1(), PRECISION);
        assertEquals(20, result.y1(), PRECISION);
        assertEquals(-150, result.x2(), PRECISION);
        assertEquals(40, result.y2(), PRECISION);
    }
}
