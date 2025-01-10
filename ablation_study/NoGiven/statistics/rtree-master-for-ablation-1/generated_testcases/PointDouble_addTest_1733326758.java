
package com.github.davidmoten.rtree.geometry.internal;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointDouble_addTest {

    @Test
    public void testAdd() {
        // Given
        PointDouble point = PointDouble.create(1.0, 2.0);
        Rectangle rectangle = Geometries.rectangle(0.0, 1.0, 3.0, 4.0);

        // When
        Rectangle result = point.add(rectangle);

        // Then
        assertEquals(0.0, result.x1(), 0.001);
        assertEquals(1.0, result.y1(), 0.001);
        assertEquals(3.0, result.x2(), 0.001);
        assertEquals(4.0, result.y2(), 0.001);
    }
}
