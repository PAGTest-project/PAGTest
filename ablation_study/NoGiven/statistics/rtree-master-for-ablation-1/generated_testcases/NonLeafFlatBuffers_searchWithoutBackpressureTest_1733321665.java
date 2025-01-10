
package com.github.davidmoten.rtree.fbs;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.fbs.generated.Bounds_;
import com.github.davidmoten.rtree.fbs.generated.Entry_;
import com.github.davidmoten.rtree.fbs.generated.Geometry_;
import com.github.davidmoten.rtree.fbs.generated.Node_;
import rx.Subscriber;
import rx.functions.Func1;

public class NonLeafFlatBuffers_searchWithoutBackpressureTest {

    private NonLeafFlatBuffers<String, Geometry> nonLeaf;
    private Node_ node;
    private Func1<byte[], String> deserializer;
    private Func1<Geometry, Boolean> criterion;
    private Subscriber<Entry<String, Geometry>> subscriber;

    @Before
    public void setUp() {
        node = mock(Node_.class);
        deserializer = mock(Func1.class);
        criterion = mock(Func1.class);
        subscriber = mock(Subscriber.class);
        nonLeaf = new NonLeafFlatBuffers<>(node, null, deserializer);
    }

    @Test
    public void testSearchWithoutBackpressure() {
        // Given
        when(node.childrenLength()).thenReturn(1);
        when(node.entriesLength()).thenReturn(0);
        Node_ child = mock(Node_.class);
        when(node.children(any(Node_.class), anyInt())).thenReturn(child);
        when(subscriber.isUnsubscribed()).thenReturn(false);

        // When
        nonLeaf.searchWithoutBackpressure(criterion, subscriber);

        // Then
        verify(node).childrenLength();
        verify(node).children(any(Node_.class), anyInt());
        verify(subscriber, never()).onNext(any());
    }
}
