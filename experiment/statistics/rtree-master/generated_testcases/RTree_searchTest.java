
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.functions.Func2;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class RTree_searchTest {

    private RTree<String, Rectangle> rTree;
    private Func2<Rectangle, Rectangle, Boolean> intersects;

    @Before
    public void setUp() {
        rTree = RTree.star().create();
        intersects = mock(Func2.class);
    }

    @Test
    public void testSearchWithIntersects() {
        Rectangle rectangle = com.github.davidmoten.rtree.geometry.Geometries.rectangle(0, 0, 1, 1);
        when(intersects.call(any(), eq(rectangle))).thenReturn(true);

        Observable<Entry<String, Rectangle>> result = rTree.search(rectangle, intersects);

        assertEquals(0, result.count().toBlocking().single().intValue());
    }

    @Test
    public void testSearchWithNonIntersectingGeometry() {
        Rectangle rectangle = com.github.davidmoten.rtree.geometry.Geometries.rectangle(0, 0, 1, 1);
        when(intersects.call(any(), eq(rectangle))).thenReturn(false);

        Observable<Entry<String, Rectangle>> result = rTree.search(rectangle, intersects);

        assertEquals(0, result.count().toBlocking().single().intValue());
    }
}
