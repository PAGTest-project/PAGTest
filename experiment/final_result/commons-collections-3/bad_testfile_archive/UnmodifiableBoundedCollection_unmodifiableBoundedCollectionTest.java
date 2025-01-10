
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

class UnmodifiableBoundedCollection_unmodifiableBoundedCollectionTest {

    @Test
    void testUnmodifiableBoundedCollectionWithUnmodifiableCollection() {
        // Given
        BoundedCollection<String> mockCollection = new MockUnmodifiableBoundedCollection<>();

        // When
        BoundedCollection<String> result = UnmodifiableBoundedCollection.unmodifiableBoundedCollection(mockCollection);

        // Then
        assertSame(mockCollection, result);
    }

    @Test
    void testUnmodifiableBoundedCollectionWithModifiableCollection() {
        // Given
        BoundedCollection<String> mockCollection = new MockBoundedCollection<>();

        // When
        BoundedCollection<String> result = UnmodifiableBoundedCollection.unmodifiableBoundedCollection(mockCollection);

        // Then
        assertTrue(result instanceof UnmodifiableBoundedCollection);
        assertNotSame(mockCollection, result);
    }

    private static class MockUnmodifiableBoundedCollection<E> implements BoundedCollection<E>, Unmodifiable {
        @Override
        public boolean isFull() { return false; }
        @Override
        public int maxSize() { return 0; }
        @Override
        public boolean add(E object) { throw new UnsupportedOperationException(); }
        @Override
        public boolean addAll(Collection<? extends E> coll) { throw new UnsupportedOperationException(); }
        @Override
        public void clear() { throw new UnsupportedOperationException(); }
        @Override
        public Iterator<E> iterator() { throw new UnsupportedOperationException(); }
        @Override
        public boolean remove(Object object) { throw new UnsupportedOperationException(); }
        @Override
        public boolean removeAll(Collection<?> coll) { throw new UnsupportedOperationException(); }
        @Override
        public boolean removeIf(Predicate<? super E> filter) { throw new UnsupportedOperationException(); }
        @Override
        public boolean retainAll(Collection<?> coll) { throw new UnsupportedOperationException(); }
        @Override
        public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException(); }
        @Override
        public Object[] toArray() { throw new UnsupportedOperationException(); }
        @Override
        public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException(); }
    }

    private static class MockBoundedCollection<E> implements BoundedCollection<E> {
        @Override
        public boolean isFull() { return false; }
        @Override
        public int maxSize() { return 0; }
        @Override
        public boolean add(E object) { return false; }
        @Override
        public boolean addAll(Collection<? extends E> coll) { return false; }
        @Override
        public void clear() {}
        @Override
        public Iterator<E> iterator() { return null; }
        @Override
        public boolean remove(Object object) { return false; }
        @Override
        public boolean removeAll(Collection<?> coll) { return false; }
        @Override
        public boolean removeIf(Predicate<? super E> filter) { return false; }
        @Override
        public boolean retainAll(Collection<?> coll) { return false; }
        @Override
        public boolean containsAll(Collection<?> c) { return false; }
        @Override
        public Object[] toArray() { return new Object[0]; }
        @Override
        public <T> T[] toArray(T[] a) { return a; }
    }
}
