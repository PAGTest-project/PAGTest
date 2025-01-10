
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Rectangle;
import rx.Observable;

public class RTree_nearestTest {

    private RTree<Integer, Rectangle> tree;

    @Before
    public void setUp() {
        tree = RTree.create();
        tree = tree.add(1, Geometries.rectangle(0, 0, 1, 1));
        tree = tree.add(2, Geometries.rectangle(2, 2, 3, 3));
    }

    @Test
    public void testNearestWithinDistance() {
        Rectangle searchRectangle = Geometries.rectangle(1.5, 1.5, 2.5, 2.5);
        Observable<Entry<Integer, Rectangle>> result = tree.nearest(searchRectangle, 1.0, 1);
        List<Entry<Integer, Rectangle>> entries = result.toList().toBlocking().single();
        assertEquals(1, entries.size());
        assertEquals(2, (int) entries.get(0).value());
    }

    @Test
    public void testNearestWithNoMatches() {
        Rectangle searchRectangle = Geometries.rectangle(4, 4, 5, 5);
        Observable<Entry<Integer, Rectangle>> result = tree.nearest(searchRectangle, 1.0, 1);
        List<Entry<Integer, Rectangle>> entries = result.toList().toBlocking().single();
        assertTrue(entries.isEmpty());
    }

    @Test
    public void testNearestWithMaxCount() {
        Rectangle searchRectangle = Geometries.rectangle(0.5, 0.5, 2.5, 2.5);
        Observable<Entry<Integer, Rectangle>> result = tree.nearest(searchRectangle, 2.0, 2);
        List<Entry<Integer, Rectangle>> entries = result.toList().toBlocking().single();
        assertEquals(2, entries.size());
        assertEquals(1, (int) entries.get(0).value());
        assertEquals(2, (int) entries.get(1).value());
    }
}
