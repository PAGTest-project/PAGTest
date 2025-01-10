
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LineFloat_equalsTest {

    @Test
    public void testEqualsWithSameObject() {
        LineFloat line = LineFloat.create(1.0, 2.0, 3.0, 4.0);
        assertTrue(line.equals(line));
    }

    @Test
    public void testEqualsWithEqualObject() {
        LineFloat line1 = LineFloat.create(1.0, 2.0, 3.0, 4.0);
        LineFloat line2 = LineFloat.create(1.0, 2.0, 3.0, 4.0);
        assertTrue(line1.equals(line2));
    }

    @Test
    public void testEqualsWithDifferentObject() {
        LineFloat line1 = LineFloat.create(1.0, 2.0, 3.0, 4.0);
        LineFloat line2 = LineFloat.create(5.0, 6.0, 7.0, 8.0);
        assertFalse(line1.equals(line2));
    }

    @Test
    public void testEqualsWithNull() {
        LineFloat line = LineFloat.create(1.0, 2.0, 3.0, 4.0);
        assertFalse(line.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        LineFloat line = LineFloat.create(1.0, 2.0, 3.0, 4.0);
        assertFalse(line.equals(new Object()));
    }
}
