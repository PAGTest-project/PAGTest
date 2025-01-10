
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Shape_equalsTest {

    private Shape shape1;
    private Shape shape2;
    private Shape shape3;

    @BeforeEach
    public void setUp() {
        shape1 = Shape.fromKM(3, 24);
        shape2 = Shape.fromKM(3, 24);
        shape3 = Shape.fromKM(4, 24);
    }

    @Test
    public void testEquals_SameObject() {
        assertEquals(shape1, shape1);
    }

    @Test
    public void testEquals_EqualObjects() {
        assertEquals(shape1, shape2);
    }

    @Test
    public void testEquals_DifferentObjects() {
        assertNotEquals(shape1, shape3);
    }

    @Test
    public void testEquals_DifferentClass() {
        assertNotEquals(shape1, new Object());
    }

    @Test
    public void testEquals_Null() {
        assertNotEquals(shape1, null);
    }

    @Test
    public void testHashCode_EqualObjects() {
        assertEquals(shape1.hashCode(), shape2.hashCode());
    }

    @Test
    public void testHashCode_DifferentObjects() {
        assertNotEquals(shape1.hashCode(), shape3.hashCode());
    }
}
