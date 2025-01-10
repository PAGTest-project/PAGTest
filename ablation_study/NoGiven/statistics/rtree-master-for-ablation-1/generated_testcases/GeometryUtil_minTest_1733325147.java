
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GeometryUtil_minTest {

    @Test
    public void testMinWithFirstArgumentSmaller() {
        double result = GeometryUtil.min(1.0, 2.0);
        assertEquals(1.0, result, 0.0);
    }

    @Test
    public void testMinWithSecondArgumentSmaller() {
        double result = GeometryUtil.min(2.0, 1.0);
        assertEquals(1.0, result, 0.0);
    }

    @Test
    public void testMinWithEqualArguments() {
        double result = GeometryUtil.min(1.0, 1.0);
        assertEquals(1.0, result, 0.0);
    }
}
