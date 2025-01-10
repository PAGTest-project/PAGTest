
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LineDouble_equalsTest {

    @Test
    public void testEqualsWithSameObject() {
        LineDouble line = LineDouble.create(1.0, 2.0, 3.0, 4.0);
        assertTrue(line.equals(line));
    }

    @Test
    public void testEqualsWithEqualObject() {
        LineDouble line1 = LineDouble.create(1.0, 2.0, 3.0, 4.0);
        LineDouble line2 = LineDouble.create(1.0, 2.0, 3.0, 4.0);
        assertTrue(line1.equals(line2));
    }

    @Test
    public void testEqualsWithDifferentObject() {
        LineDouble line1 = LineDouble.create(1.0, 2.0, 3.0, 4.0);
        LineDouble line2 = LineDouble.create(5.0, 6.0, 7.0, 8.0);
        assertFalse(line1.equals(line2));
    }

    @Test
    public void testEqualsWithNull() {
        LineDouble line = LineDouble.create(1.0, 2.0, 3.0, 4.0);
        assertFalse(line.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        LineDouble line = LineDouble.create(1.0, 2.0, 3.0, 4.0);
        assertFalse(line.equals("not a LineDouble"));
    }
}
