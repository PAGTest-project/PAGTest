
package com.github.davidmoten.rtree;

import com.github.davidmoten.guavamini.Lists;
import com.github.davidmoten.rtree.geometry.HasGeometry;
import com.github.davidmoten.rtree.geometry.ListPair;
import com.github.davidmoten.rtree.internal.util.Pair;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SplitterQuadratic_splitTest {

    @Test
    public void testSplitWithTwoItems() {
        SplitterQuadratic splitter = new SplitterQuadratic();
        HasGeometry item1 = new TestHasGeometry();
        HasGeometry item2 = new TestHasGeometry();
        List<HasGeometry> items = Arrays.asList(item1, item2);

        ListPair<HasGeometry> result = splitter.split(items, 1);

        assertEquals(1, result.group1().size());
        assertEquals(1, result.group2().size());
        assertTrue(result.group1().contains(item1) || result.group1().contains(item2));
        assertTrue(result.group2().contains(item1) || result.group2().contains(item2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitWithLessThanTwoItems() {
        SplitterQuadratic splitter = new SplitterQuadratic();
        HasGeometry item1 = new TestHasGeometry();
        List<HasGeometry> items = Arrays.asList(item1);

        splitter.split(items, 1);
    }

    private static class TestHasGeometry implements HasGeometry {
        @Override
        public Geometry geometry() {
            return null;
        }
    }
}
