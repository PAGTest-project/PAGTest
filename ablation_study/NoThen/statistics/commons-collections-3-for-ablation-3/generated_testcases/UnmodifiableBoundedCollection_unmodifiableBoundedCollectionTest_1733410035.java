
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class UnmodifiableBoundedCollection_unmodifiableBoundedCollectionTest {

    @Test
    void testUnmodifiableBoundedCollectionWithUnmodifiableCollection() {
        // Given
        BoundedCollection<String> coll = new UnmodifiableBoundedCollection<>(new MyBoundedCollection<>());

        // When
        BoundedCollection<String> result = UnmodifiableBoundedCollection.unmodifiableBoundedCollection(coll);

        // Then
        assertSame(coll, result);
    }

    @Test
    void testUnmodifiableBoundedCollectionWithModifiableCollection() {
        // Given
        BoundedCollection<String> coll = new MyBoundedCollection<>();

        // When
        BoundedCollection<String> result = UnmodifiableBoundedCollection.unmodifiableBoundedCollection(coll);

        // Then
        assertInstanceOf(UnmodifiableBoundedCollection.class, result);
    }

    private static class MyBoundedCollection<E> implements BoundedCollection<E> {
        @Override
        public boolean isFull() {
            return false;
        }

        @Override
        public int maxSize() {
            return 0;
        }

        @Override
        public Iterator<E> iterator() {
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
    }
}
