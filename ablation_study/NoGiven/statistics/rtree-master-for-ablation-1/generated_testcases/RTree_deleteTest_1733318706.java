
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

import rx.Observable;

public class RTree_deleteTest {

    private RTree<Object, Rectangle> tree;

    @Before
    public void setUp() {
        tree = RTree.star().maxChildren(4).<Object, Rectangle> create();
    }

    @Test
    public void testDeleteSingleEntry() {
        Entry<Object, Rectangle> e1 = RTreeTest.e(1);
        tree = tree.add(e1);
        Observable<RTree<Object, Rectangle>> result = tree.delete(Observable.just(e1), false);
        result.forEach(t -> assertTrue(t.entries().isEmpty().toBlocking().single()));
    }

    @Test
    public void testDeleteMultipleEntries() {
        Entry<Object, Rectangle> e1 = RTreeTest.e(1);
        Entry<Object, Rectangle> e2 = RTreeTest.e(2);
        List<Entry<Object, Rectangle>> entries = Arrays.asList(e1, e2);
        tree = tree.add(entries);
        Observable<RTree<Object, Rectangle>> result = tree.delete(Observable.from(entries), false);
        result.forEach(t -> assertEquals(0, t.entries().count().toBlocking().single()));
    }

    @Test
    public void testDeleteAllEntries() {
        Entry<Object, Rectangle> e1 = RTreeTest.e(1);
        Entry<Object, Rectangle> e2 = RTreeTest.e(2);
        List<Entry<Object, Rectangle>> entries = Arrays.asList(e1, e2);
        tree = tree.add(entries);
        Observable<RTree<Object, Rectangle>> result = tree.delete(Observable.from(entries), true);
        result.forEach(t -> assertEquals(0, t.entries().count().toBlocking().single()));
    }

    @Test
    public void testDeleteNonExistentEntry() {
        Entry<Object, Rectangle> e1 = RTreeTest.e(1);
        Entry<Object, Rectangle> e2 = RTreeTest.e(2);
        tree = tree.add(e1);
        Observable<RTree<Object, Rectangle>> result = tree.delete(Observable.just(e2), false);
        result.forEach(t -> assertEquals(1, t.entries().count().toBlocking().single()));
    }

    @Test
    public void testDeleteAndAddEntry() {
        Entry<Object, Rectangle> e1 = RTreeTest.e(1);
        tree = tree.add(e1);
        Observable<RTree<Object, Rectangle>> result = tree.delete(Observable.just(e1), false);
        result.forEach(t -> {
            assertTrue(t.entries().isEmpty().toBlocking().single());
            t = t.add(e1);
            assertEquals(1, t.entries().count().toBlocking().single());
        });
    }

    @Test
    public void testDeleteAndSearchEntry() {
        Entry<Object, Rectangle> e1 = RTreeTest.e(1);
        tree = tree.add(e1);
        Observable<RTree<Object, Rectangle>> result = tree.delete(Observable.just(e1), false);
        result.forEach(t -> {
            assertTrue(t.entries().isEmpty().toBlocking().single());
            t.search(e1.geometry()).forEach(entry -> assertTrue(false));
        });
    }

    private static Entry<Object, Rectangle> e(int id) {
        return Entries.entry(new Object(), Geometries.rectangle(0, 0, id, id));
    }
}
