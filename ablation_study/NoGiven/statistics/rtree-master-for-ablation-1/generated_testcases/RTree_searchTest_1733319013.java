
package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;
import rx.Observable;
import rx.functions.Func2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RTree_searchTest {

    @Test
    public void testSearch() {
        // Given
        RTree<String, Rectangle> rTree = RTree.create();
        Rectangle geometry = mock(Rectangle.class);
        double maxDistance = 10.0;
        Func2<Rectangle, Rectangle, Double> distanceFunc = mock(Func2.class);

        Observable<Entry<String, Rectangle>> expectedObservable = Observable.empty();
        Observable<Entry<String, Rectangle>> mockObservable = mock(Observable.class);

        when(geometry.mbr()).thenReturn(geometry);
        when(distanceFunc.call(any(), eq(geometry))).thenReturn(5.0);
        when(rTree.search(any(Func1.class))).thenReturn(mockObservable);
        when(mockObservable.filter(any(Func1.class))).thenReturn(expectedObservable);

        // When
        Observable<Entry<String, Rectangle>> result = rTree.search(geometry, maxDistance, distanceFunc);

        // Then
        assertEquals(expectedObservable, result);
    }
}
