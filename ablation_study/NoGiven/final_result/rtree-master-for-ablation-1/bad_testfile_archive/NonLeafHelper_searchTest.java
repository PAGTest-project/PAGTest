
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.NonLeaf;
import com.github.davidmoten.rtree.geometry.Geometry;
import org.junit.Before;
import org.junit.Test;
import rx.Subscriber;
import rx.functions.Func1;

import static org.mockito.Mockito.*;

public class NonLeafHelper_searchTest {

    private Func1<Geometry, Boolean> criterion;
    private Subscriber<Entry<Object, Geometry>> subscriber;
    private NonLeaf<Object, Geometry> node;

    @Before
    public void setUp() {
        criterion = mock(Func1.class);
        subscriber = mock(Subscriber.class);
        node = mock(NonLeaf.class);
    }

    @Test
    public void testSearch_CriterionFalse() {
        when(criterion.call(any())).thenReturn(false);
        NonLeafHelper.search(criterion, subscriber, node);
        verify(criterion).call(node.geometry().mbr());
        verifyNoMoreInteractions(subscriber, node);
    }

    @Test
    public void testSearch_CriterionTrue_SubscriberUnsubscribed() {
        when(criterion.call(any())).thenReturn(true);
        when(subscriber.isUnsubscribed()).thenReturn(true);
        when(node.count()).thenReturn(1);

        NonLeafHelper.search(criterion, subscriber, node);

        verify(criterion).call(node.geometry().mbr());
        verify(subscriber).isUnsubscribed();
        verifyNoMoreInteractions(subscriber, node);
    }

    @Test
    public void testSearch_CriterionTrue_SubscriberNotUnsubscribed() {
        when(criterion.call(any())).thenReturn(true);
        when(subscriber.isUnsubscribed()).thenReturn(false);
        when(node.count()).thenReturn(1);
        Node<Object, Geometry> child = mock(Node.class);
        when(node.child(0)).thenReturn(child);

        NonLeafHelper.search(criterion, subscriber, node);

        verify(criterion).call(node.geometry().mbr());
        verify(subscriber).isUnsubscribed();
        verify(node).child(0);
        verify(child).searchWithoutBackpressure(criterion, subscriber);
        verifyNoMoreInteractions(subscriber, node, child);
    }
}
