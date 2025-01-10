
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_findTest {

    @Test
    public void testFindRandomAccessList() {
        List<Integer> list = ImmutableList.of(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i == 3;
        int result = Static.find(list, predicate);
        assertEquals(2, result);
    }

    @Test
    public void testFindNonRandomAccessList() {
        List<Integer> list = new NonRandomAccessList<>(ImmutableList.of(1, 2, 3, 4, 5));
        Predicate<Integer> predicate = i -> i == 3;
        int result = Static.find(list, predicate);
        assertEquals(2, result);
    }

    @Test
    public void testFindNoMatch() {
        List<Integer> list = ImmutableList.of(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i == 6;
        int result = Static.find(list, predicate);
        assertEquals(-1, result);
    }

    private static class NonRandomAccessList<E> implements List<E> {
        private final List<E> delegate;

        NonRandomAccessList(List<E> delegate) {
            this.delegate = delegate;
        }

        @Override
        public int size() {
            return delegate.size();
        }

        @Override
        public boolean isEmpty() {
            return delegate.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return delegate.contains(o);
        }

        @Override
        public Iterator<E> iterator() {
            return delegate.iterator();
        }

        @Override
        public Object[] toArray() {
            return delegate.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return delegate.toArray(a);
        }

        @Override
        public boolean add(E e) {
            return delegate.add(e);
        }

        @Override
        public boolean remove(Object o) {
            return delegate.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return delegate.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return delegate.addAll(c);
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            return delegate.addAll(index, c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return delegate.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return delegate.retainAll(c);
        }

        @Override
        public void clear() {
            delegate.clear();
        }

        @Override
        public E get(int index) {
            return delegate.get(index);
        }

        @Override
        public E set(int index, E element) {
            return delegate.set(index, element);
        }

        @Override
        public void add(int index, E element) {
            delegate.add(index, element);
        }

        @Override
        public E remove(int index) {
            return delegate.remove(index);
        }

        @Override
        public int indexOf(Object o) {
            return delegate.indexOf(o);
        }

        @Override
        public int lastIndexOf(Object o) {
            return delegate.lastIndexOf(o);
        }

        @Override
        public ListIterator<E> listIterator() {
            return delegate.listIterator();
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            return delegate.listIterator(index);
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            return delegate.subList(fromIndex, toIndex);
        }
    }
}
