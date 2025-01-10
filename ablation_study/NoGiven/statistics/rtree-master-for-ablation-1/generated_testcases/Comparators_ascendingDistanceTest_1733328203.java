
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Comparator;

public class Comparators_ascendingDistanceTest {

    @Test
    public void testAscendingDistance() {
        Rectangle r = mock(Rectangle.class);
        Geometry g1 = mock(Geometry.class);
        Geometry g2 = mock(Geometry.class);
        Entry<Object, Geometry> e1 = mock(Entry.class);
        Entry<Object, Geometry> e2 = mock(Entry.class);

        when(e1.geometry()).thenReturn(g1);
        when(e2.geometry()).thenReturn(g2);
        when(g1.distance(r)).thenReturn(1.0);
        when(g2.distance(r)).thenReturn(2.0);

        Comparator<Entry<Object, Geometry>> comparator = Comparators.ascendingDistance(r);
        int result = comparator.compare(e1, e2);

        assertEquals(-1, result);
    }
}
