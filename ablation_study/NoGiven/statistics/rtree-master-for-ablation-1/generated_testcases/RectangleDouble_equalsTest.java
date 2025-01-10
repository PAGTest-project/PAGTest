
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RectangleDouble_equalsTest {

    @Test
    public void testEqualsWithSameObject() {
        RectangleDouble rect = RectangleDouble.create(0, 0, 1, 1);
        assertTrue(rect.equals(rect));
    }

    @Test
    public void testEqualsWithDifferentObject() {
        RectangleDouble rect1 = RectangleDouble.create(0, 0, 1, 1);
        RectangleDouble rect2 = RectangleDouble.create(0, 0, 1, 1);
        assertTrue(rect1.equals(rect2));
    }

    @Test
    public void testEqualsWithDifferentCoordinates() {
        RectangleDouble rect1 = RectangleDouble.create(0, 0, 1, 1);
        RectangleDouble rect2 = RectangleDouble.create(1, 1, 2, 2);
        assertFalse(rect1.equals(rect2));
    }

    @Test
    public void testEqualsWithNonRectangleDoubleObject() {
        RectangleDouble rect = RectangleDouble.create(0, 0, 1, 1);
        assertFalse(rect.equals("Not a RectangleDouble"));
    }
}
