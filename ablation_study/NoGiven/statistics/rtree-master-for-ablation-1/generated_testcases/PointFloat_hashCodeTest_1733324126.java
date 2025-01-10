
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PointFloat_hashCodeTest {

    @Test
    public void testHashCode() {
        PointFloat point = PointFloat.create(1.0f, 2.0f);
        int expectedHashCode = 31 * (31 + Float.floatToIntBits(1.0f)) + Float.floatToIntBits(2.0f);
        assertEquals(expectedHashCode, point.hashCode());
    }
}
