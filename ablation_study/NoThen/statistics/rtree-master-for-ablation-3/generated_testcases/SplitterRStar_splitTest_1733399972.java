
package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.HasGeometry;
import com.github.davidmoten.rtree.geometry.ListPair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SplitterRStar_splitTest {

    @Test
    public void testSplit() {
        // Given
        SplitterRStar splitter = new SplitterRStar();
        HasGeometry item1 = mock(HasGeometry.class);
        HasGeometry item2 = mock(HasGeometry.class);
        List<HasGeometry> items = Arrays.asList(item1, item2);
        int minSize = 1;

        // When
        ListPair<HasGeometry> result = splitter.split(items, minSize);

        // Then
        assertEquals(2, result.group1().size() + result.group2().size());
    }
}
