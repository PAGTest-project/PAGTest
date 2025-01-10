
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableIterator_unmodifiableIteratorTest {

    @Test
    void testUnmodifiableIteratorWithModifiableIterator() {
        Iterator<String> modifiableIterator = new Iterator<String>() {
            private int count = 0;
            @Override
            public boolean hasNext() {
                return count < 1;
            }
            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                count++;
                return "element";
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        Iterator<String> unmodifiableIterator = UnmodifiableIterator.unmodifiableIterator(modifiableIterator);
        assertTrue(unmodifiableIterator.hasNext());
        assertEquals("element", unmodifiableIterator.next());
        assertThrows(UnsupportedOperationException.class, unmodifiableIterator::remove);
    }

    @Test
    void testUnmodifiableIteratorWithUnmodifiableIterator() {
        Iterator<String> unmodifiableIterator = new UnmodifiableIterator<>(new Iterator<String>() {
            private int count = 0;
            @Override
            public boolean hasNext() {
                return count < 1;
            }
            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                count++;
                return "element";
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });

        Iterator<String> resultIterator = UnmodifiableIterator.unmodifiableIterator(unmodifiableIterator);
        assertSame(unmodifiableIterator, resultIterator);
    }

    @Test
    void testUnmodifiableIteratorWithNullIterator() {
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableIterator.unmodifiableIterator(null);
        });
    }
}
