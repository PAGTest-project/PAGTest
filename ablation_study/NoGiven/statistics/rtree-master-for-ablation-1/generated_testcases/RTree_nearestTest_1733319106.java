
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class RTree_nearestTest {

    @Test
    public void testNearest() {
        // Given
        RTree<Object, Geometry> rTree = mock(RTree.class);
        Rectangle rectangle = mock(Rectangle.class);
        Observable<Entry<Object, Geometry>> searchResult = Observable.just(mock(Entry.class));
        when(rTree.search(rectangle, 10.0)).thenReturn(searchResult);

        // When
        Observable<Entry<Object, Geometry>> result = rTree.nearest(rectangle, 10.0, 5);

        // Then
        TestSubscriber<Entry<Object, Geometry>> testSubscriber = new TestSubscriber<>();
        result.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
    }
}
