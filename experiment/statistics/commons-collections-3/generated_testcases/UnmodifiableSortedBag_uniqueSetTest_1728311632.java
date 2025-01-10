
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableSortedBag_uniqueSetTest {

    private SortedBag<String> bag;
    private UnmodifiableSortedBag<String> unmodifiableSortedBag;

    @BeforeEach
    public void setUp() {
        bag = new MockSortedBag<>();
        unmodifiableSortedBag = new UnmodifiableSortedBag<>(bag);
    }

    @Test
    public void testUniqueSet() {
        Set<String> uniqueSet = unmodifiableSortedBag.uniqueSet();
        assertTrue(uniqueSet instanceof UnmodifiableSet);
    }

    private static class MockSortedBag<E> implements SortedBag<E> {
        @Override
        public E first() {
            return null;
        }

        @Override
        public E last() {
            return null;
        }

        @Override
        public Comparator<? super E> comparator() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<E> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(E e) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {
        }

        @Override
        public boolean add(E object, int count) {
            return false;
        }

        @Override
        public boolean remove(Object object, int count) {
            return false;
        }

        @Override
        public int getCount(Object object) {
            return 0;
        }

        @Override
        public Set<E> uniqueSet() {
            return new HashSet<>();
        }
    }
}
