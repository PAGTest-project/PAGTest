
package com.github.davidmoten.rtree.geometry;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.davidmoten.rtree.geometry.Rectangle;

@RunWith(MockitoJUnitRunner.class)
public class ListPair_areaSumTest {

    @Mock
    private Group<HasGeometry> mockGroup1;

    @Mock
    private Group<HasGeometry> mockGroup2;

    @Mock
    private Rectangle mockRectangle1;

    @Mock
    private Rectangle mockRectangle2;

    @Test
    public void testAreaSum() {
        // Given
        when(mockGroup1.geometry()).thenReturn(mockRectangle1);
        when(mockGroup2.geometry()).thenReturn(mockRectangle2);
        when(mockRectangle1.area()).thenReturn(10.0);
        when(mockRectangle2.area()).thenReturn(20.0);

        ListPair<HasGeometry> listPair = new ListPair<>(Arrays.asList(mockGroup1), Arrays.asList(mockGroup2));

        // When
        double result = listPair.areaSum();

        // Then
        assertEquals(30.0, result, 0.001);
    }
}
