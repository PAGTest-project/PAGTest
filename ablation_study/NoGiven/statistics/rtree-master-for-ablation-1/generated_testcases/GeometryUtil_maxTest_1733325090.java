
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GeometryUtil_maxTest {

    @Test
    public void testMaxWithFirstArgumentGreater() {
        double result = GeometryUtil.max(5.0, 3.0);
        assertEquals(5.0, result, 0.0);
    }

    @Test
    public void testMaxWithSecondArgumentGreater() {
        double result = GeometryUtil.max(3.0, 5.0);
        assertEquals(5.0, result, 0.0);
    }

    @Test
    public void testMaxWithEqualArguments() {
        double result = GeometryUtil.max(3.0, 3.0);
        assertEquals(3.0, result, 0.0);
    }
}
