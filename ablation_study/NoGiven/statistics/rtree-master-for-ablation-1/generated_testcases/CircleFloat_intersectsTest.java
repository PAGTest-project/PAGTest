
package com.github.davidmoten.rtree.geometry.internal;

import com.github.davidmoten.rtree.geometry.Circle;
import org.junit.Test;
import static org.junit.Assert.*;

public class CircleFloat_intersectsTest {

    @Test
    public void testIntersectsWhenCirclesIntersect() {
        CircleFloat circle1 = CircleFloat.create(0, 0, 2);
        CircleFloat circle2 = CircleFloat.create(1, 1, 2);
        assertTrue(circle1.intersects(circle2));
    }

    @Test
    public void testIntersectsWhenCirclesDoNotIntersect() {
        CircleFloat circle1 = CircleFloat.create(0, 0, 2);
        CircleFloat circle2 = CircleFloat.create(5, 5, 2);
        assertFalse(circle1.intersects(circle2));
    }
}
