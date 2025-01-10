
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.Context;
import org.junit.Test;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class NonLeafDefault_searchWithoutBackpressureTest {

    @Test
    public void testSearchWithoutBackpressure() {
        // Given
        List<Node<String, Geometry>> children = Arrays.asList(mock(Node.class), mock(Node.class));
        Context<String, Geometry> context = mock(Context.class);
        NonLeafDefault<String, Geometry> nonLeaf = new NonLeafDefault<>(children, context);

        Func1<Geometry, Boolean> criterion = mock(Func1.class);
        Subscriber<Entry<String, Geometry>> subscriber = mock(Subscriber.class);

        // When
        nonLeaf.searchWithoutBackpressure(criterion, subscriber);

        // Then
        verify(criterion, times(1)).call(any(Geometry.class));
        verify(subscriber, times(1)).onNext(any(Entry.class));
    }
}
