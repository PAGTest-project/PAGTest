
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
        BoundedPriorityQueue<Integer> q = mock(BoundedPriorityQueue.class);
        when(q.asOrderedList()).thenReturn(List.of(1, 2, 3));

        // When
        Subscriber<? super Integer> result = operator.call(child);
        result.onNext(1);
        result.onNext(2);
        result.onNext(3);
        result.onCompleted();

        // Then
        verify(child, times(3)).onNext(anyInt());
        verify(child).onCompleted();
    }

    @Test
    public void testCallWithError() {
        // Given
        int maximumSize = 5;
        Comparator<Integer> comparator = Comparator.naturalOrder();
        OperatorBoundedPriorityQueue<Integer> operator = new OperatorBoundedPriorityQueue<>(maximumSize, comparator);
        Subscriber<Integer> child = mock(Subscriber.class);

        // When
        Subscriber<? super Integer> result = operator.call(child);
        result.onError(new RuntimeException("Test error"));

        // Then
        verify(child).onError(any(RuntimeException.class));
    }
}
