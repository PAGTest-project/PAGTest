
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.HasGeometry;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Util_mbrTest {

    @Test
    public void testMbrWithSingleItemDoublePrecision() {
        HasGeometry item = mock(HasGeometry.class);
        Rectangle rect = mock(Rectangle.class);
        when(item.geometry()).thenReturn(rect);
        when(rect.mbr()).thenReturn(rect);
        when(rect.isDoublePrecision()).thenReturn(true);
        when(rect.x1()).thenReturn(1.0);
        when(rect.y1()).thenReturn(2.0);
        when(rect.x2()).thenReturn(3.0);
        when(rect.y2()).thenReturn(4.0);

        Collection<HasGeometry> items = Arrays.asList(item);
        Rectangle result = Util.mbr(items);

        assertEquals(1.0, result.x1(), 0.001);
        assertEquals(2.0, result.y1(), 0.001);
        assertEquals(3.0, result.x2(), 0.001);
        assertEquals(4.0, result.y2(), 0.001);
        assertTrue(result.isDoublePrecision());
    }

    @Test
    public void testMbrWithMultipleItemsSinglePrecision() {
        HasGeometry item1 = mock(HasGeometry.class);
        Rectangle rect1 = mock(Rectangle.class);
        when(item1.geometry()).thenReturn(rect1);
        when(rect1.mbr()).thenReturn(rect1);
        when(rect1.isDoublePrecision()).thenReturn(false);
        when(rect1.x1()).thenReturn(1.0);
        when(rect1.y1()).thenReturn(2.0);
        when(rect1.x2()).thenReturn(3.0);
        when(rect1.y2()).thenReturn(4.0);

        HasGeometry item2 = mock(HasGeometry.class);
        Rectangle rect2 = mock(Rectangle.class);
        when(item2.geometry()).thenReturn(rect2);
        when(rect2.mbr()).thenReturn(rect2);
        when(rect2.isDoublePrecision()).thenReturn(false);
        when(rect2.x1()).thenReturn(2.0);
        when(rect2.y1()).thenReturn(3.0);
        when(rect2.x2()).thenReturn(4.0);
        when(rect2.y2()).thenReturn(5.0);

        Collection<HasGeometry> items = Arrays.asList(item1, item2);
        Rectangle result = Util.mbr(items);

        assertEquals(1.0, result.x1(), 0.001);
        assertEquals(2.0, result.y1(), 0.001);
        assertEquals(4.0, result.x2(), 0.001);
        assertEquals(5.0, result.y2(), 0.001);
        assertTrue(!result.isDoublePrecision());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMbrWithEmptyCollection() {
        Collection<HasGeometry> items = Arrays.asList();
        Util.mbr(items);
    }
}
