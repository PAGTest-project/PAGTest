
package com.github.davidmoten.rtree.internal.operators;

import com.github.davidmoten.rtree.internal.util.BoundedPriorityQueue;
import org.junit.Test;
import rx.Subscriber;
import rx.observers.TestSubscriber;

import java.util.Comparator;

import static org.mockito.Mockito.*;

public class OperatorBoundedPriorityQueue_callTest {

    @Test
    public void testCallMethod() {
        // Given
        int maximumSize = 5;
        Comparator<Integer> comparator = Comparator.naturalOrder();
        OperatorBoundedPriorityQueue<Integer> operator = new OperatorBoundedPriorityQueue<>(maximumSize, comparator);
        TestSubscriber<Integer> child = new TestSubscriber<>();

        // When
        Subscriber<? super Integer> subscriber = operator.call(child);
        subscriber.onStart();
        subscriber.onNext(1);
        subscriber.onNext(2);
        subscriber.onCompleted();

        // Then
        child.assertNoErrors();
        child.assertCompleted();
        child.assertValues(1, 2);
    }

    @Test
    public void testCallMethodWithUnsubscription() {
        // Given
        int maximumSize = 5;
        Comparator<Integer> comparator = Comparator.naturalOrder();
        OperatorBoundedPriorityQueue<Integer> operator = new OperatorBoundedPriorityQueue<>(maximumSize, comparator);
        TestSubscriber<Integer> child = new TestSubscriber<>();

        // When
        Subscriber<? super Integer> subscriber = operator.call(child);
        subscriber.onStart();
        subscriber.onNext(1);
        subscriber.unsubscribe();
        subscriber.onNext(2);
        subscriber.onCompleted();

        // Then
        child.assertNoErrors();
        child.assertNotCompleted();
        child.assertValues(1);
    }

    @Test
    public void testCallMethodWithError() {
        // Given
        int maximumSize = 5;
        Comparator<Integer> comparator = Comparator.naturalOrder();
        OperatorBoundedPriorityQueue<Integer> operator = new OperatorBoundedPriorityQueue<>(maximumSize, comparator);
        TestSubscriber<Integer> child = new TestSubscriber<>();

        // When
        Subscriber<? super Integer> subscriber = operator.call(child);
        subscriber.onStart();
        subscriber.onNext(1);
        subscriber.onError(new RuntimeException("Test error"));

        // Then
        child.assertError(RuntimeException.class);
        child.assertNotCompleted();
        child.assertValues(1);
    }
}
