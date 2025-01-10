
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RectangleDouble_intersectionAreaTest {

    @Test
    public void testIntersectionAreaNoIntersection() {
        RectangleDouble rect1 = RectangleDouble.create(0, 0, 2, 2);
        RectangleDouble rect2 = RectangleDouble.create(3, 3, 5, 5);
        assertEquals(0, rect1.intersectionArea(rect2), 0.0001);
    }

    @Test
    public void testIntersectionAreaWithIntersection() {
        RectangleDouble rect1 = RectangleDouble.create(0, 0, 3, 3);
        RectangleDouble rect2 = RectangleDouble.create(2, 2, 5, 5);
        assertEquals(1, rect1.intersectionArea(rect2), 0.0001);
    }
}
