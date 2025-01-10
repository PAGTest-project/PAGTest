
package com.github.davidmoten.rtree;

import com.github.davidmoten.guavamini.Lists;
import com.github.davidmoten.rtree.geometry.HasGeometry;
import com.github.davidmoten.rtree.geometry.ListPair;
import com.github.davidmoten.rtree.internal.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SplitterQuadratic_splitTest {

    @Test
    public void testSplitWithMinimumSize() {
        SplitterQuadratic splitter = new SplitterQuadratic();

        List<MockHasGeometry> items = new ArrayList<>();
        items.add(new MockHasGeometry());
        items.add(new MockHasGeometry());
        items.add(new MockHasGeometry());
        items.add(new MockHasGeometry());

        ListPair<MockHasGeometry> result = splitter.split(items, 2);

        assertEquals(2, result.group1().size());
        assertEquals(2, result.group2().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitWithLessThanTwoItems() {
        SplitterQuadratic splitter = new SplitterQuadratic();

        List<MockHasGeometry> items = new ArrayList<>();
        items.add(new MockHasGeometry());

        splitter.split(items, 1);
    }

    private static class MockHasGeometry implements HasGeometry {
        @Override
        public Geometry geometry() {
            return null;
        }
    }
}
