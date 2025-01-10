
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Leaf;
import com.github.davidmoten.rtree.geometry.Geometry;
import org.junit.Test;
import rx.Subscriber;
import rx.functions.Func1;

import static org.mockito.Mockito.*;

public class LeafHelper_searchTest {

    @Test
    public void testSearch_ConditionFalse_NoEntriesProcessed() {
        // Given
        Func1<Geometry, Boolean> condition = mock(Func1.class);
        Subscriber<Entry<String, Geometry>> subscriber = mock(Subscriber.class);
        Leaf<String, Geometry> leaf = mock(Leaf.class);

        when(condition.call(any())).thenReturn(false);
        when(leaf.geometry()).thenReturn(mock(Geometry.class));

        // When
        LeafHelper.search(condition, subscriber, leaf);

        // Then
        verify(subscriber, never()).onNext(any());
        verify(subscriber, never()).isUnsubscribed();
    }

    @Test
    public void testSearch_ConditionTrue_AllEntriesProcessed() {
        // Given
        Func1<Geometry, Boolean> condition = mock(Func1.class);
        Subscriber<Entry<String, Geometry>> subscriber = mock(Subscriber.class);
        Leaf<String, Geometry> leaf = mock(Leaf.class);
        Entry<String, Geometry> entry1 = mock(Entry.class);
        Entry<String, Geometry> entry2 = mock(Entry.class);

        when(condition.call(any())).thenReturn(true);
        when(leaf.geometry()).thenReturn(mock(Geometry.class));
        when(leaf.count()).thenReturn(2);
        when(leaf.entry(0)).thenReturn(entry1);
        when(leaf.entry(1)).thenReturn(entry2);
        when(entry1.geometry()).thenReturn(mock(Geometry.class));
        when(entry2.geometry()).thenReturn(mock(Geometry.class));

        // When
        LeafHelper.search(condition, subscriber, leaf);

        // Then
        verify(subscriber, times(2)).onNext(any());
        verify(subscriber, never()).isUnsubscribed();
    }

    @Test
    public void testSearch_SubscriberUnsubscribed_NoEntriesProcessed() {
        // Given
        Func1<Geometry, Boolean> condition = mock(Func1.class);
        Subscriber<Entry<String, Geometry>> subscriber = mock(Subscriber.class);
        Leaf<String, Geometry> leaf = mock(Leaf.class);
        Entry<String, Geometry> entry1 = mock(Entry.class);

        when(condition.call(any())).thenReturn(true);
        when(subscriber.isUnsubscribed()).thenReturn(true);
        when(leaf.geometry()).thenReturn(mock(Geometry.class));
        when(leaf.count()).thenReturn(1);
        when(leaf.entry(0)).thenReturn(entry1);
        when(entry1.geometry()).thenReturn(mock(Geometry.class));

        // When
        LeafHelper.search(condition, subscriber, leaf);

        // Then
        verify(subscriber, never()).onNext(any());
        verify(subscriber, times(1)).isUnsubscribed();
    }
}
