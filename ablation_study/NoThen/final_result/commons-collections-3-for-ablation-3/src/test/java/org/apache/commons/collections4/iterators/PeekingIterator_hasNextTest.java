
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;

public class PeekingIterator_hasNextTest {

    private List<String> testList;
    private static final String[] testArray = {"a", "b", "c"};

    @SuppressWarnings("unchecked")
    @BeforeEach
    protected void setUp() throws Exception {
        testList = new ArrayList<>(Arrays.asList((String[]) testArray));
    }

    @SuppressWarnings("unchecked")
    private PeekingIterator<String> makeObject() {
        return new PeekingIterator<>(testList.iterator());
    }

    private void validate(Iterator<String> iter, String... items) {
        for (String item : items) {
            assertTrue(iter.hasNext());
            assertEquals(item, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithElements() {
        PeekingIterator<String> it = makeObject();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void testHasNextWithNoElements() {
        PeekingIterator<String> it = new PeekingIterator<>(Collections.emptyIterator());
        assertFalse(it.hasNext());
    }

    @Test
    public void testHasNextAfterPeek() {
        PeekingIterator<String> it = makeObject();
        it.peek();
        assertTrue(it.hasNext());
        it.next();
        it.peek();
        assertTrue(it.hasNext());
        it.next();
        it.peek();
        assertTrue(it.hasNext());
        it.next();
        it.peek();
        assertFalse(it.hasNext());
    }

    @Test
    public void testHasNextAfterElement() {
        PeekingIterator<String> it = makeObject();
        it.element();
        assertTrue(it.hasNext());
        it.next();
        it.element();
        assertTrue(it.hasNext());
        it.next();
        it.element();
        assertTrue(it.hasNext());
        it.next();
        assertThrows(NoSuchElementException.class, it::element);
        assertFalse(it.hasNext());
    }
}
