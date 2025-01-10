
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
    private static final Point SYDNEY = Geometries.point(151.2094, -33.8688);
    private static final Point CANBERRA = Geometries.point(149.1244, -35.2809);
    private static final Point BRISBANE = Geometries.point(153.0251, -27.4698);

    @Before
    public void setUp() {
        tree = RTree.star().create();
        tree = tree.add("Sydney", SYDNEY);
        tree = tree.add("Canberra", CANBERRA);
        tree = tree.add("Brisbane", BRISBANE);
    }

    @Test
    public void testNearestWithinDistance() {
        Rectangle searchArea = Geometries.rectangle(149.1244, -35.2809, 149.1244, -35.2809);
        double maxDistance = 300; // km
        int maxCount = 1;

        List<Entry<String, Point>> result = tree.nearest(searchArea, maxDistance, maxCount)
                .toList().toBlocking().single();

        assertEquals(1, result.size());
        assertEquals("Sydney", result.get(0).value());
    }

    @Test
    public void testNearestWithMaxCount() {
        Rectangle searchArea = Geometries.rectangle(149.1244, -35.2809, 149.1244, -35.2809);
        double maxDistance = 1000; // km
        int maxCount = 2;

        List<Entry<String, Point>> result = tree.nearest(searchArea, maxDistance, maxCount)
                .toList().toBlocking().single();

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(e -> e.value().equals("Sydney")));
        assertTrue(result.stream().anyMatch(e -> e.value().equals("Canberra")));
    }

    @Test
    public void testNearestWithNoMatches() {
        Rectangle searchArea = Geometries.rectangle(149.1244, -35.2809, 149.1244, -35.2809);
        double maxDistance = 50; // km
        int maxCount = 1;

        List<Entry<String, Point>> result = tree.nearest(searchArea, maxDistance, maxCount)
                .toList().toBlocking().single();

        assertTrue(result.isEmpty());
    }
}
