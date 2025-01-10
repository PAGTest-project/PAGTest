
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableBoundedCollection_unmodifiableBoundedCollectionTest {

    private BoundedCollection<String> boundedCollection;

    @BeforeEach
    public void setUp() {
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));
        boundedCollection = new BoundedCollectionImpl<>(list, 5);
    }

    @Test
    public void testUnmodifiableBoundedCollectionWithUnmodifiableCollection() {
        BoundedCollection<String> unmodifiableBoundedCollection = UnmodifiableBoundedCollection.unmodifiableBoundedCollection(boundedCollection);
        assertTrue(unmodifiableBoundedCollection instanceof Unmodifiable);
    }

    @Test
    public void testUnmodifiableBoundedCollectionWithModifiableCollection() {
        BoundedCollection<String> unmodifiableBoundedCollection = UnmodifiableBoundedCollection.unmodifiableBoundedCollection(boundedCollection);
        assertNotSame(boundedCollection, unmodifiableBoundedCollection);
        assertTrue(unmodifiableBoundedCollection instanceof Unmodifiable);
    }

    @Test
    public void testUnmodifiableBoundedCollectionWithNullCollection() {
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableBoundedCollection.unmodifiableBoundedCollection(null);
        });
    }

    private static class BoundedCollectionImpl<E> implements BoundedCollection<E> {
        private final Collection<E> collection;
        private final int maxSize;

        public BoundedCollectionImpl(Collection<E> collection, int maxSize) {
            this.collection = collection;
            this.maxSize = maxSize;
        }

        @Override
        public boolean isFull() {
            return collection.size() >= maxSize;
        }

        @Override
        public int maxSize() {
            return maxSize;
        }

        @Override
        public boolean add(E e) {
            return collection.add(e);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return collection.addAll(c);
        }

        @Override
        public void clear() {
            collection.clear();
        }

        @Override
        public boolean contains(Object o) {
            return collection.contains(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return collection.containsAll(c);
        }

        @Override
        public boolean isEmpty() {
            return collection.isEmpty();
        }

        @Override
        public Iterator<E> iterator() {
            return collection.iterator();
        }

        @Override
        public boolean remove(Object o) {
            return collection.remove(o);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return collection.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return collection.retainAll(c);
        }

        @Override
        public int size() {
            return collection.size();
        }

        @Override
        public Object[] toArray() {
            return collection.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return collection.toArray(a);
        }
    }
}
