
package com.github.davidmoten.rtree.geometry.internal;

import org.junit.Test;
import static org.junit.Assert.*;

public class CircleDouble_equalsTest {

    @Test
    public void testEquals_SameObject() {
        CircleDouble circle = CircleDouble.create(1.0, 2.0, 3.0);
        assertTrue(circle.equals(circle));
    }

    @Test
    public void testEquals_DifferentClass() {
        CircleDouble circle = CircleDouble.create(1.0, 2.0, 3.0);
        assertFalse(circle.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentValues() {
        CircleDouble circle1 = CircleDouble.create(1.0, 2.0, 3.0);
        CircleDouble circle2 = CircleDouble.create(4.0, 5.0, 6.0);
        assertFalse(circle1.equals(circle2));
    }

    @Test
    public void testEquals_SameValues() {
        CircleDouble circle1 = CircleDouble.create(1.0, 2.0, 3.0);
        CircleDouble circle2 = CircleDouble.create(1.0, 2.0, 3.0);
        assertTrue(circle1.equals(circle2));
    }
}
