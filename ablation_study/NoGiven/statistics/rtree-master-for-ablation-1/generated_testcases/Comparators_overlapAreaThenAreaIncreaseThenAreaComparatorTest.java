
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.davidmoten.rtree.geometry.HasGeometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class Comparators_overlapAreaThenAreaIncreaseThenAreaComparatorTest {

    private Rectangle rectangle;
    private HasGeometry g1;
    private HasGeometry g2;
    private List<HasGeometry> list;

    @Before
    public void setUp() {
        rectangle = mock(Rectangle.class);
        g1 = mock(HasGeometry.class);
        g2 = mock(HasGeometry.class);
        list = Arrays.asList(g1, g2);
    }

    @Test
    public void testOverlapAreaThenAreaIncreaseThenAreaComparator() {
        // Given
        when(Comparators.overlapArea(rectangle, list, g1)).thenReturn(10.0f);
        when(Comparators.overlapArea(rectangle, list, g2)).thenReturn(20.0f);

        // When
        Comparator<HasGeometry> comparator = Comparators.overlapAreaThenAreaIncreaseThenAreaComparator(rectangle, list);

        // Then
        assertEquals(-1, comparator.compare(g1, g2));
    }

    @Test
    public void testOverlapAreaThenAreaIncreaseThenAreaComparator_SameOverlapArea() {
        // Given
        when(Comparators.overlapArea(rectangle, list, g1)).thenReturn(10.0f);
        when(Comparators.overlapArea(rectangle, list, g2)).thenReturn(10.0f);
        when(Comparators.areaIncrease(rectangle, g1)).thenReturn(5.0);
        when(Comparators.areaIncrease(rectangle, g2)).thenReturn(15.0);

        // When
        Comparator<HasGeometry> comparator = Comparators.overlapAreaThenAreaIncreaseThenAreaComparator(rectangle, list);

        // Then
        assertEquals(-1, comparator.compare(g1, g2));
    }

    @Test
    public void testOverlapAreaThenAreaIncreaseThenAreaComparator_SameOverlapAreaAndAreaIncrease() {
        // Given
        when(Comparators.overlapArea(rectangle, list, g1)).thenReturn(10.0f);
        when(Comparators.overlapArea(rectangle, list, g2)).thenReturn(10.0f);
        when(Comparators.areaIncrease(rectangle, g1)).thenReturn(10.0);
        when(Comparators.areaIncrease(rectangle, g2)).thenReturn(10.0);
        when(Comparators.area(rectangle, g1)).thenReturn(100.0);
        when(Comparators.area(rectangle, g2)).thenReturn(200.0);

        // When
        Comparator<HasGeometry> comparator = Comparators.overlapAreaThenAreaIncreaseThenAreaComparator(rectangle, list);

        // Then
        assertEquals(-1, comparator.compare(g1, g2));
    }
}
