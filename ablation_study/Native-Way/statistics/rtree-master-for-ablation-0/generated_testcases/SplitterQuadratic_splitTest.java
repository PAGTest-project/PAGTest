
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
    public void testSplitWithMinimumSize() {
        SplitterQuadratic splitter = new SplitterQuadratic();

        // Create a list of items with size >= 2
        List<MockHasGeometry> items = Arrays.asList(
                new MockHasGeometry(),
                new MockHasGeometry(),
                new MockHasGeometry(),
                new MockHasGeometry()
        );

        ListPair<MockHasGeometry> result = splitter.split(items, 2);

        // Assert that the result contains two groups
        assertEquals(4, result.group1().size() + result.group2().size());
        assertTrue(result.group1().size() >= 2);
        assertTrue(result.group2().size() >= 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitWithLessThanMinimumSize() {
        SplitterQuadratic splitter = new SplitterQuadratic();

        // Create a list of items with size < 2
        List<MockHasGeometry> items = Arrays.asList(
                new MockHasGeometry()
        );

        splitter.split(items, 2);
    }

    private static class MockHasGeometry implements HasGeometry {
        @Override
        public Rectangle geometry() {
            return new Rectangle() {
                @Override
                public Rectangle mbr() {
                    return this;
                }

                @Override
                public Rectangle add(Rectangle r) {
                    return this;
                }

                @Override
                public double area() {
                    return 0;
                }

                @Override
                public boolean isDoublePrecision() {
                    return false;
                }
            };
        }
    }
}
