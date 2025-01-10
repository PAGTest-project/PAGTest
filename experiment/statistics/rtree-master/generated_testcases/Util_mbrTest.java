
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.HasGeometry;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Util_mbrTest {

    @Test
    public void testMbrWithSingleItem() {
        HasGeometry item = mock(HasGeometry.class);
        Rectangle rect = mock(Rectangle.class);
        when(item.geometry()).thenReturn(rect);
        when(rect.mbr()).thenReturn(rect);
        when(rect.x1()).thenReturn(1.0);
        when(rect.y1()).thenReturn(2.0);
        when(rect.x2()).thenReturn(3.0);
        when(rect.y2()).thenReturn(4.0);
        when(rect.isDoublePrecision()).thenReturn(false);

        Collection<? extends HasGeometry> items = Arrays.asList(item);
        Rectangle result = Util.mbr(items);

        assertEquals(Geometries.rectangle(1.0f, 2.0f, 3.0f, 4.0f), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMbrWithEmptyCollection() {
        Collection<? extends HasGeometry> items = Arrays.asList();
        Util.mbr(items);
    }

    @Test
    public void testMbrWithDoublePrecision() {
        HasGeometry item1 = mock(HasGeometry.class);
        Rectangle rect1 = mock(Rectangle.class);
        when(item1.geometry()).thenReturn(rect1);
        when(rect1.mbr()).thenReturn(rect1);
        when(rect1.x1()).thenReturn(1.0);
        when(rect1.y1()).thenReturn(2.0);
        when(rect1.x2()).thenReturn(3.0);
        when(rect1.y2()).thenReturn(4.0);
        when(rect1.isDoublePrecision()).thenReturn(false);

        HasGeometry item2 = mock(HasGeometry.class);
        Rectangle rect2 = mock(Rectangle.class);
        when(item2.geometry()).thenReturn(rect2);
        when(rect2.mbr()).thenReturn(rect2);
        when(rect2.x1()).thenReturn(0.0);
        when(rect2.y1()).thenReturn(1.0);
        when(rect2.x2()).thenReturn(2.0);
        when(rect2.y2()).thenReturn(3.0);
        when(rect2.isDoublePrecision()).thenReturn(true);

        Collection<? extends HasGeometry> items = Arrays.asList(item1, item2);
        Rectangle result = Util.mbr(items);

        assertEquals(Geometries.rectangle(0.0, 1.0, 3.0, 4.0), result);
    }
}
