
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Rectangle;

import rx.Observable;
import rx.functions.Func2;

public class RTree_searchTest {

    private RTree<Object, Geometry> tree;

    @Before
    public void setUp() {
        List<Entry<Object, Geometry>> entries = new ArrayList<>();
        entries.add(Entries.entry("entry1", Point.create(1.0, 1.0)));
        entries.add(Entries.entry("entry2", Point.create(2.0, 2.0)));
        entries.add(Entries.entry("entry3", Point.create(3.0, 3.0)));
        tree = RTree.star().maxChildren(4).<Object, Geometry>create().add(entries);
    }

    @Test
    public void testSearchWithinMaxDistance() {
        Point searchPoint = Point.create(1.5, 1.5);
        double maxDistance = 1.5;
        Func2<Geometry, Geometry, Double> distanceFunc = (g1, g2) -> g1.distance(g2.mbr());

        Observable<Entry<Object, Geometry>> result = tree.search(searchPoint, maxDistance, distanceFunc);

        List<Entry<Object, Geometry>> entries = result.toList().toBlocking().single();
        assertEquals(2, entries.size());
        assertTrue(entries.stream().anyMatch(e -> e.value().equals("entry1")));
        assertTrue(entries.stream().anyMatch(e -> e.value().equals("entry2")));
    }

    @Test
    public void testSearchOutsideMaxDistance() {
        Point searchPoint = Point.create(10.0, 10.0);
        double maxDistance = 1.0;
        Func2<Geometry, Geometry, Double> distanceFunc = (g1, g2) -> g1.distance(g2.mbr());

        Observable<Entry<Object, Geometry>> result = tree.search(searchPoint, maxDistance, distanceFunc);

        List<Entry<Object, Geometry>> entries = result.toList().toBlocking().single();
        assertEquals(0, entries.size());
    }
}
