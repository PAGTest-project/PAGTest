
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class RTree_asStringTest {

    private RTree<Object, Rectangle> tree;

    @Before
    public void setUp() {
        tree = RTree.star().maxChildren(4).<Object, Rectangle> create();
    }

    @Test
    public void testAsStringWithEmptyTree() {
        assertEquals("", tree.asString());
    }

    @Test
    public void testAsStringWithNonEmptyTree() {
        Entry<Object, Rectangle> e1 = RTreeTest.e(1);
        List<Entry<Object, Rectangle>> list = new ArrayList<Entry<Object, Rectangle>>();
        for (int i = 1; i <= 17; i++)
            list.add(e1);
        tree = tree.add(list);
        assertTrue(tree.asString().length() > 0);
    }

    @Test
    public void testAsStringAfterDeletion() {
        Entry<Object, Rectangle> e1 = RTreeTest.e(1);
        List<Entry<Object, Rectangle>> list = new ArrayList<Entry<Object, Rectangle>>();
        for (int i = 1; i <= 17; i++)
            list.add(e1);
        tree = tree.add(list);
        tree = tree.delete(e1, true);
        assertTrue(tree.asString().length() > 0);
    }

    @Test
    public void testAsStringAfterSearch() {
        Entry<Object, Rectangle> e1 = RTreeTest.e(1);
        List<Entry<Object, Rectangle>> list = new ArrayList<Entry<Object, Rectangle>>();
        for (int i = 1; i <= 17; i++)
            list.add(e1);
        tree = tree.add(list);
        tree.search(geometry -> true).subscribe();
        assertTrue(tree.asString().length() > 0);
    }

    private static Entry<Object, Rectangle> e(int i) {
        return Entries.entry(new Object(), Geometries.rectangle(0, 0, i, i));
    }
}
