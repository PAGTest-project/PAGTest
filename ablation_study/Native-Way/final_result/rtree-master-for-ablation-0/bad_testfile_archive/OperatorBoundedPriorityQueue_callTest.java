
package com.github.davidmoten.rtree.internal.operators;

import com.github.davidmoten.rtree.internal.util.BoundedPriorityQueue;
import org.junit.Test;
import rx.Subscriber;

import java.util.Comparator;
import java.util.List;

import static org.mockito.Mockito.*;

public class OperatorBoundedPriorityQueue_callTest {

    @Test
    public void testCall() {
        // Given
        int maximumSize = 5;
        Comparator<Integer> comparator = Comparator.naturalOrder();
        OperatorBoundedPriorityQueue<Integer> operator = new OperatorBoundedPriorityQueue<>(maximumSize, comparator);

        Subscriber<Integer> child = mock(Subscriber.class);
        BoundedPriorityQueue<Integer> q = new BoundedPriorityQueue<>(maximumSize, comparator);
        List<Integer> list = List.of(1, 2, 3);

        when(child.isUnsubscribed()).thenReturn(false);

        Subscriber<? super Integer> subscriber = operator.call(child);

        // When
        subscriber.onNext(1);
        subscriber.onNext(2);
        subscriber.onNext(3);
        subscriber.onCompleted();

        // Then
        verify(child).onNext(1);
        verify(child).onNext(2);
        verify(child).onNext(3);
        verify(child).onCompleted();
    }

    @Test
    public void testCallOnError() {
        // Given
        int maximumSize = 5;
        Comparator<Integer> comparator = Comparator.naturalOrder();
        OperatorBoundedPriorityQueue<Integer> operator = new OperatorBoundedPriorityQueue<>(maximumSize, comparator);

        Subscriber<Integer> child = mock(Subscriber.class);
        Subscriber<? super Integer> subscriber = operator.call(child);

        Throwable error = new Throwable("Test error");

        // When
        subscriber.onError(error);

        // Then
        verify(child).onError(error);
    }
}
