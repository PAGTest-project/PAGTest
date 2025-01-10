
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RectangleDouble_addTest {

    @Test
    public void testAdd() {
        RectangleDouble r1 = RectangleDouble.create(0, 0, 2, 2);
        RectangleDouble r2 = RectangleDouble.create(1, 1, 3, 3);

        RectangleDouble result = r1.add(r2);

        assertEquals(0.0, result.x1(), 0.001);
        assertEquals(0.0, result.y1(), 0.001);
        assertEquals(3.0, result.x2(), 0.001);
        assertEquals(3.0, result.y2(), 0.001);
    }

    @Test
    public void testAddNonOverlapping() {
        RectangleDouble r1 = RectangleDouble.create(0, 0, 2, 2);
        RectangleDouble r2 = RectangleDouble.create(3, 3, 5, 5);

        RectangleDouble result = r1.add(r2);

        assertEquals(0.0, result.x1(), 0.001);
        assertEquals(0.0, result.y1(), 0.001);
        assertEquals(5.0, result.x2(), 0.001);
        assertEquals(5.0, result.y2(), 0.001);
    }

    @Test
    public void testAddSameRectangle() {
        RectangleDouble r1 = RectangleDouble.create(1, 1, 3, 3);

        RectangleDouble result = r1.add(r1);

        assertEquals(1.0, result.x1(), 0.001);
        assertEquals(1.0, result.y1(), 0.001);
        assertEquals(3.0, result.x2(), 0.001);
        assertEquals(3.0, result.y2(), 0.001);
    }
}
