
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RectangleFloat_intersectionAreaTest {

    @Test
    public void testIntersectionAreaNoIntersection() {
        RectangleFloat rect1 = RectangleFloat.create(0, 0, 2, 2);
        RectangleFloat rect2 = RectangleFloat.create(3, 3, 5, 5);
        assertEquals(0, rect1.intersectionArea(rect2), 0.0001);
    }

    @Test
    public void testIntersectionAreaWithIntersection() {
        RectangleFloat rect1 = RectangleFloat.create(0, 0, 3, 3);
        RectangleFloat rect2 = RectangleFloat.create(2, 2, 5, 5);
        assertEquals(1, rect1.intersectionArea(rect2), 0.0001);
    }
}
