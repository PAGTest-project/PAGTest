
package com.github.davidmoten.rtree;

import com.github.davidmoten.guavamini.Lists;
import com.github.davidmoten.rtree.geometry.HasGeometry;
import com.github.davidmoten.rtree.geometry.ListPair;
import com.github.davidmoten.rtree.geometry.Rectangle;
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
        List<HasGeometry> items = Arrays.asList(new TestHasGeometry(), new TestHasGeometry());
        ListPair<HasGeometry> result = splitter.split(items, 1);

        assertEquals(1, result.group1().size());
        assertEquals(1, result.group2().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitWithLessThanTwoItems() {
        SplitterQuadratic splitter = new SplitterQuadratic();
        List<HasGeometry> items = Arrays.asList(new TestHasGeometry());
        splitter.split(items, 1);
    }

    private static class TestHasGeometry implements HasGeometry {
        @Override
        public Rectangle geometry() {
            return Rectangle.create(0, 0, 1, 1);
        }
    }
}
