
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

public class RTree_nearestTest {

    @Test
    public void testNearest() {
        // Given
        RTree<Object, Geometry> rTree = new RTree<>();
        Rectangle rectangle = mock(Rectangle.class);
        double maxDistance = 10.0;
        int maxCount = 5;

        Observable<Entry<Object, Geometry>> searchResult = Observable.just(
            new Entry<>(new Object(), mock(Geometry.class)),
            new Entry<>(new Object(), mock(Geometry.class))
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
}
