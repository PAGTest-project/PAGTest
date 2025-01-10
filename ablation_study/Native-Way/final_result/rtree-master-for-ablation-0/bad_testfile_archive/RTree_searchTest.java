
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Rectangle;
import rx.Observable;
import rx.functions.Func2;

public class RTree_searchTest {
    private RTree<String, Point> tree;

    @Before
    public void setUp() {
        tree = RTree.star().create();
        tree = tree.add("Entry1", Geometries.point(1, 1));
        tree = tree.add("Entry2", Geometries.point(2, 2));
    }

    @Test
    public void testSearchWithPoint() {
        Point point = Geometries.point(1.5, 1.5);
        Observable<Entry<String, Point>> result = tree.search(point, 1.0);
        assertEquals(2L, result.count().toBlocking().single().longValue());
    }

    @Test
    public void testSearchWithRectangle() {
        Rectangle rectangle = Geometries.rectangle(0, 0, 2, 2);
        Observable<Entry<String, Point>> result = tree.search(rectangle, 1.0);
        assertEquals(2L, result.count().toBlocking().single().longValue());
    }

    @Test
    public void testSearchWithCustomDistanceFunction() {
        Point point = Geometries.point(1.5, 1.5);
        Func2<Point, Point, Double> distanceFunction = (p1, p2) -> Math.abs(p1.x() - p2.x()) + Math.abs(p1.y() - p2.y());
        Observable<Entry<String, Point>> result = tree.search(point, 1.0, distanceFunction);
        assertEquals(2L, result.count().toBlocking().single().longValue());
    }
}
