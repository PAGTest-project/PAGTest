
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PointDouble_hashCodeTest {

    @Test
    public void testHashCode() {
        PointDouble point1 = PointDouble.create(1.0, 2.0);
        PointDouble point2 = PointDouble.create(1.0, 2.0);

        int hashCode1 = point1.hashCode();
        int hashCode2 = point2.hashCode();

        assertEquals(hashCode1, hashCode2);
        assertTrue(point1.equals(point2));
    }
}
