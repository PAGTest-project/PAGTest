
package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.HasGeometry;
import com.github.davidmoten.rtree.geometry.ListPair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SplitterRStar_splitTest {

    @Test
    public void testSplitWithValidInput() {
        SplitterRStar splitter = new SplitterRStar();
        List<HasGeometry> items = Arrays.asList(
                new MockHasGeometry(1.0, 2.0, 3.0, 4.0),
                new MockHasGeometry(2.0, 3.0, 4.0, 5.0),
                new MockHasGeometry(3.0, 4.0, 5.0, 6.0)
        );
        int minSize = 1;

        ListPair<HasGeometry> result = splitter.split(items, minSize);

        assertNotNull(result);
        assertEquals(2, result.group1().size() + result.group2().size());
    }

    private static class MockHasGeometry implements HasGeometry {
        private final double x1, y1, x2, y2;

        public MockHasGeometry(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public Geometry geometry() {
            return new Geometry() {
                @Override
                public Rectangle mbr() {
                    return new Rectangle() {
                        @Override
                        public double x1() {
                            return x1;
                        }

                        @Override
                        public double y1() {
                            return y1;
                        }

                        @Override
                        public double x2() {
                            return x2;
                        }

                        @Override
                        public double y2() {
                            return y2;
                        }

                        @Override
                        public double intersectionArea(Rectangle r) {
                            return 0;
                        }
                    };
                }
            };
        }
    }

    private interface Geometry {
        Rectangle mbr();
    }

    private interface Rectangle {
        double x1();
        double y1();
        double x2();
        double y2();
        double intersectionArea(Rectangle r);
    }
}
