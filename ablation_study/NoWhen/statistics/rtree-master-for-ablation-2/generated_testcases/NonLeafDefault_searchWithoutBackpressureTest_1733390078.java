
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.Context;
import org.junit.Test;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class NonLeafDefault_searchWithoutBackpressureTest {

    @Test
    public void testSearchWithoutBackpressure() {
        // Given
        List<Node<String, Geometry>> children = Collections.emptyList();
        Context<String, Geometry> context = mock(Context.class);
        NonLeafDefault<String, Geometry> nonLeafDefault = new NonLeafDefault<>(children, context);

        Func1<? super Geometry, Boolean> criterion = mock(Func1.class);
        Subscriber<? super Entry<String, Geometry>> subscriber = mock(Subscriber.class);

        // When
        nonLeafDefault.searchWithoutBackpressure(criterion, subscriber);

        // Then
        verify(subscriber, never()).onNext(any());
        verify(subscriber, never()).onError(any());
        verify(subscriber, never()).onCompleted();
    }
}
