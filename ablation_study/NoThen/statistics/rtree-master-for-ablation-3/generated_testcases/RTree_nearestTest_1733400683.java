
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Rectangle;

import rx.Observable;

public class RTree_nearestTest {

    private RTree<String, Point> tree;

    @Before
    public void setUp() {
        tree = RTree.star().create();
        tree = tree.add("Sydney", Geometries.point(151.209900, -33.865143));
        tree = tree.add("Canberra", Geometries.point(149.128684, -35.282001));
        tree = tree.add("Brisbane", Geometries.point(153.025131, -27.469770));
    }

    @Test
    public void testNearestWithinDistance() {
        Rectangle searchArea = Geometries.rectangle(151.0, -34.0, 152.0, -33.0);
        Observable<Entry<String, Point>> nearestEntries = tree.nearest(searchArea, 300, 1);
        List<Entry<String, Point>> list = nearestEntries.toList().toBlocking().single();

        assertEquals(1, list.size());
        assertEquals("Sydney", list.get(0).value());
    }

    @Test
    public void testNearestWithMaxCount() {
        Rectangle searchArea = Geometries.rectangle(149.0, -35.0, 154.0, -27.0);
        Observable<Entry<String, Point>> nearestEntries = tree.nearest(searchArea, 1000, 2);
        List<Entry<String, Point>> list = nearestEntries.toList().toBlocking().single();

        assertEquals(2, list.size());
        assertTrue(list.stream().anyMatch(e -> e.value().equals("Sydney")));
        assertTrue(list.stream().anyMatch(e -> e.value().equals("Brisbane")));
    }

    @Test
    public void testNearestWithNoMatches() {
        Rectangle searchArea = Geometries.rectangle(100.0, -35.0, 101.0, -34.0);
        Observable<Entry<String, Point>> nearestEntries = tree.nearest(searchArea, 100, 1);
        List<Entry<String, Point>> list = nearestEntries.toList().toBlocking().single();

        assertEquals(0, list.size());
    }
}
