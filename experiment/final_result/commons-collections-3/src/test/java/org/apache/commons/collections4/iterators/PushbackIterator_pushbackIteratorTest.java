
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PushbackIterator_pushbackIteratorTest {

    private Iterator<String> testIterator;

    @BeforeEach
    public void setUp() {
        testIterator = new Iterator<String>() {
            private int index = 0;
            private final String[] elements = {"a", "b", "c"};

            @Override
            public boolean hasNext() {
                return index < elements.length;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[index++];
            }
        };
    }

    @Test
    public void testPushbackIteratorWithRegularIterator() {
        PushbackIterator<String> pushbackIterator = PushbackIterator.pushbackIterator(testIterator);
        assertTrue(pushbackIterator.hasNext());
        assertEquals("a", pushbackIterator.next());
        pushbackIterator.pushback("a");
        assertEquals("a", pushbackIterator.next());
        assertEquals("b", pushbackIterator.next());
        assertEquals("c", pushbackIterator.next());
    }

    @Test
    public void testPushbackIteratorWithPushbackIterator() {
        PushbackIterator<String> originalPushbackIterator = new PushbackIterator<>(testIterator);
        PushbackIterator<String> pushbackIterator = PushbackIterator.pushbackIterator(originalPushbackIterator);
        assertTrue(pushbackIterator.hasNext());
        assertEquals("a", pushbackIterator.next());
        pushbackIterator.pushback("a");
        assertEquals("a", pushbackIterator.next());
        assertEquals("b", pushbackIterator.next());
        assertEquals("c", pushbackIterator.next());
    }

    @Test
    public void testPushbackIteratorWithNullIterator() {
        assertThrows(NullPointerException.class, () -> {
            PushbackIterator.pushbackIterator(null);
        });
    }
}
