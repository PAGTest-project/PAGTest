
package com.github.davidmoten.rtree.internal.operators;

import com.github.davidmoten.rtree.internal.util.BoundedPriorityQueue;
import org.junit.Test;
import rx.Subscriber;
import rx.observers.TestSubscriber;

import java.util.Comparator;

import static org.mockito.Mockito.*;

public class OperatorBoundedPriorityQueue_callTest {

    @Test
    public void testCall() {
        // Given
        int maximumSize = 5;
        Comparator<Integer> comparator = Comparator.naturalOrder();
        OperatorBoundedPriorityQueue<Integer> operator = new OperatorBoundedPriorityQueue<>(maximumSize, comparator);
        TestSubscriber<Integer> child = new TestSubscriber<>();

        // When
        Subscriber<? super Integer> result = operator.call(child);

        // Then
        result.onNext(1);
        result.onNext(2);
        result.onCompleted();

        child.assertValues(1, 2);
        child.assertCompleted();
    }

    @Test
    public void testCallWithUnsubscription() {
        // Given
        int maximumSize = 5;
        Comparator<Integer> comparator = Comparator.naturalOrder();
        OperatorBoundedPriorityQueue<Integer> operator = new OperatorBoundedPriorityQueue<>(maximumSize, comparator);
        TestSubscriber<Integer> child = new TestSubscriber<>();

        // When
        Subscriber<? super Integer> result = operator.call(child);
        result.onNext(1);
        result.onNext(2);
        result.unsubscribe();
        result.onCompleted();

        // Then
        child.assertValues(1, 2);
        child.assertNotCompleted();
    }

    @Test
    public void testCallWithError() {
        // Given
        int maximumSize = 5;
        Comparator<Integer> comparator = Comparator.naturalOrder();
        OperatorBoundedPriorityQueue<Integer> operator = new OperatorBoundedPriorityQueue<>(maximumSize, comparator);
        TestSubscriber<Integer> child = new TestSubscriber<>();

        // When
        Subscriber<? super Integer> result = operator.call(child);
        result.onNext(1);
        result.onError(new RuntimeException("Test error"));

        // Then
        child.assertValue(1);
        child.assertError(RuntimeException.class);
    }
}
