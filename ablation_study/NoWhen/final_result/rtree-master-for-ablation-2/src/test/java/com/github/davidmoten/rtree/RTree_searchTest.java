
package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;
import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Geometries;
import rx.Observable;
import rx.functions.Func2;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RTree_searchTest {

    @Test
    public void testSearch() {
        // Given
        RTree<String, Point> tree = RTree.create();
        Point point = Geometries.point(1, 1);
        Func2<Point, Point, Double> distanceFunc = (p1, p2) -> p1.distance(p2.mbr());
        double maxDistance = 10.0;

        // When
        Observable<Entry<String, Point>> result = tree.search(point, maxDistance, distanceFunc);

        // Then
        assertTrue(result.isEmpty().toBlocking().single());
    }
}
