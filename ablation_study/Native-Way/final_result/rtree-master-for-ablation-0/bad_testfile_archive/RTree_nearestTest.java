
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
        RTree<Object, Geometry> rTree = RTree.create();
        Rectangle rectangle = mock(Rectangle.class);
        double maxDistance = 10.0;
        int maxCount = 5;

        Observable<Entry<Object, Geometry>> searchResult = Observable.just(
            new EntryMock(new Object(), mock(Geometry.class)),
            new EntryMock(new Object(), mock(Geometry.class))
        );

        // Mock the search method to return the predefined observable
        when(rTree.search(rectangle, maxDistance)).thenReturn(searchResult);

        // When
        Observable<Entry<Object, Geometry>> result = rTree.nearest(rectangle, maxDistance, maxCount);

        // Then
        TestSubscriber<Entry<Object, Geometry>> testSubscriber = new TestSubscriber<>();
        result.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        assertEquals(2, testSubscriber.getOnNextEvents().size());
    }

    private static class EntryMock<T, S extends Geometry> extends Entry<T, S> {
        public EntryMock(T value, S geometry) {
            super(value, geometry);
        }
    }
}
