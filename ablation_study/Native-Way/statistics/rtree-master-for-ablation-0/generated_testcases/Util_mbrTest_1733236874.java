
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

    @Test(expected = IllegalArgumentException.class)
    public void testMbrWithEmptyCollection() {
        Util.mbr(Arrays.asList());
    }

    @Test
    public void testMbrWithSingleItem() {
        Rectangle mockRectangle = mock(Rectangle.class);
        when(mockRectangle.x1()).thenReturn(1.0);
        when(mockRectangle.y1()).thenReturn(2.0);
        when(mockRectangle.x2()).thenReturn(3.0);
        when(mockRectangle.y2()).thenReturn(4.0);
        when(mockRectangle.isDoublePrecision()).thenReturn(false);

        HasGeometry mockItem = mock(HasGeometry.class);
        when(mockItem.geometry()).thenReturn(mockRectangle);

        Rectangle result = Util.mbr(Arrays.asList(mockItem));

        assertEquals(1.0f, result.x1(), 0.0);
        assertEquals(2.0f, result.y1(), 0.0);
        assertEquals(3.0f, result.x2(), 0.0);
        assertEquals(4.0f, result.y2(), 0.0);
    }

    @Test
    public void testMbrWithMultipleItemsDoublePrecision() {
        Rectangle mockRectangle1 = mock(Rectangle.class);
        when(mockRectangle1.x1()).thenReturn(1.0);
        when(mockRectangle1.y1()).thenReturn(2.0);
        when(mockRectangle1.x2()).thenReturn(3.0);
        when(mockRectangle1.y2()).thenReturn(4.0);
        when(mockRectangle1.isDoublePrecision()).thenReturn(true);

        Rectangle mockRectangle2 = mock(Rectangle.class);
        when(mockRectangle2.x1()).thenReturn(0.0);
        when(mockRectangle2.y1()).thenReturn(1.0);
        when(mockRectangle2.x2()).thenReturn(2.0);
        when(mockRectangle2.y2()).thenReturn(3.0);
        when(mockRectangle2.isDoublePrecision()).thenReturn(false);

        HasGeometry mockItem1 = mock(HasGeometry.class);
        when(mockItem1.geometry()).thenReturn(mockRectangle1);

        HasGeometry mockItem2 = mock(HasGeometry.class);
        when(mockItem2.geometry()).thenReturn(mockRectangle2);

        Rectangle result = Util.mbr(Arrays.asList(mockItem1, mockItem2));

        assertEquals(0.0, result.x1(), 0.0);
        assertEquals(1.0, result.y1(), 0.0);
        assertEquals(3.0, result.x2(), 0.0);
        assertEquals(4.0, result.y2(), 0.0);
        assertTrue(result.isDoublePrecision());
    }
}
