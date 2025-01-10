
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CircleFloat_equalsTest {

    @Test
    public void testEqualsWithSameObject() {
        CircleFloat circle = CircleFloat.create(1.0f, 2.0f, 3.0f);
        assertTrue(circle.equals(circle));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        CircleFloat circle = CircleFloat.create(1.0f, 2.0f, 3.0f);
        assertFalse(circle.equals(new Object()));
    }

    @Test
    public void testEqualsWithDifferentValues() {
        CircleFloat circle1 = CircleFloat.create(1.0f, 2.0f, 3.0f);
        CircleFloat circle2 = CircleFloat.create(4.0f, 5.0f, 6.0f);
        assertFalse(circle1.equals(circle2));
    }

    @Test
    public void testEqualsWithSameValues() {
        CircleFloat circle1 = CircleFloat.create(1.0f, 2.0f, 3.0f);
        CircleFloat circle2 = CircleFloat.create(1.0f, 2.0f, 3.0f);
        assertTrue(circle1.equals(circle2));
    }
}
