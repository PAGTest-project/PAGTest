
package com.github.davidmoten.rtree.geometry.internal;

import com.github.davidmoten.rtree.geometry.Circle;
import org.junit.Test;
import static org.junit.Assert.*;

public class CircleDouble_intersectsTest {

    @Test
    public void testIntersectsWhenCirclesIntersect() {
        CircleDouble circle1 = CircleDouble.create(0, 0, 2);
        CircleDouble circle2 = CircleDouble.create(3, 0, 2);
        assertTrue(circle1.intersects(circle2));
    }

    @Test
    public void testIntersectsWhenCirclesDoNotIntersect() {
        CircleDouble circle1 = CircleDouble.create(0, 0, 2);
        CircleDouble circle2 = CircleDouble.create(5, 0, 2);
        assertFalse(circle1.intersects(circle2));
    }
}
