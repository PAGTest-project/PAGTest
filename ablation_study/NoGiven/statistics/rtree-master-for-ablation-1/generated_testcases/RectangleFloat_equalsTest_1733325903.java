
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RectangleFloat_equalsTest {

    @Test
    public void testEqualsWithSameObject() {
        RectangleFloat rect = RectangleFloat.create(1.0f, 2.0f, 3.0f, 4.0f);
        assertTrue(rect.equals(rect));
    }

    @Test
    public void testEqualsWithDifferentObject() {
        RectangleFloat rect1 = RectangleFloat.create(1.0f, 2.0f, 3.0f, 4.0f);
        RectangleFloat rect2 = RectangleFloat.create(1.0f, 2.0f, 3.0f, 4.0f);
        assertTrue(rect1.equals(rect2));
    }

    @Test
    public void testEqualsWithDifferentValues() {
        RectangleFloat rect1 = RectangleFloat.create(1.0f, 2.0f, 3.0f, 4.0f);
        RectangleFloat rect2 = RectangleFloat.create(1.0f, 2.0f, 3.0f, 5.0f);
        assertFalse(rect1.equals(rect2));
    }

    @Test
    public void testEqualsWithNull() {
        RectangleFloat rect = RectangleFloat.create(1.0f, 2.0f, 3.0f, 4.0f);
        assertFalse(rect.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        RectangleFloat rect = RectangleFloat.create(1.0f, 2.0f, 3.0f, 4.0f);
        assertFalse(rect.equals(new Object()));
    }
}
