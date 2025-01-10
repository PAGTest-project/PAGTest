
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.github.davidmoten.rtree.geometry.HasGeometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class Comparators_areaIncreaseThenAreaComparatorTest {

    @Test
    public void testAreaIncreaseThenAreaComparator() {
        Rectangle r = mock(Rectangle.class);
        HasGeometry g1 = mock(HasGeometry.class);
        HasGeometry g2 = mock(HasGeometry.class);

        // Mocking areaIncrease and area methods
        when(Comparators.areaIncrease(r, g1)).thenReturn(10.0);
        when(Comparators.areaIncrease(r, g2)).thenReturn(5.0);
        when(Comparators.area(r, g1)).thenReturn(20.0);
        when(Comparators.area(r, g2)).thenReturn(15.0);

        Comparator<HasGeometry> comparator = Comparators.areaIncreaseThenAreaComparator(r);

        // Test when areaIncrease is different
        assertEquals(-1, comparator.compare(g1, g2));

        // Test when areaIncrease is the same but area is different
        when(Comparators.areaIncrease(r, g1)).thenReturn(5.0);
        assertEquals(1, comparator.compare(g1, g2));

        // Test when both areaIncrease and area are the same
        when(Comparators.area(r, g1)).thenReturn(15.0);
        assertEquals(0, comparator.compare(g1, g2));
    }
}
