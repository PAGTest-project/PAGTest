
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class NonLeafDefault_searchWithoutBackpressureTest {

    private NonLeafDefault<String, Rectangle> nonLeafDefault;
    private Context<String, Rectangle> context;
    private List<? extends Node<String, Rectangle>> children;
    private Subscriber<Entry<String, Rectangle>> subscriber;
    private Func1<Geometry, Boolean> criterion;

    @Before
    public void setUp() {
        context = mock(Context.class);
        children = Collections.singletonList(mock(Node.class));
        nonLeafDefault = new NonLeafDefault<>(children, context);
        subscriber = mock(Subscriber.class);
        criterion = mock(Func1.class);
    }

    @Test
    public void testSearchWithoutBackpressure() {
        nonLeafDefault.searchWithoutBackpressure(criterion, subscriber);
        verify(subscriber, never()).onNext(any());
        verify(subscriber, never()).onCompleted();
        verify(subscriber, never()).onError(any());
    }
}
