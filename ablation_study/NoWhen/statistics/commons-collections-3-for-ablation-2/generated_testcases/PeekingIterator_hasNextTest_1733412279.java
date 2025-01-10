
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    @SuppressWarnings("unchecked")
    public void testHasNextWithElements() {
        PeekingIterator<String> it = makeObject();
        assertTrue(it.hasNext());
        validate(it, (String[]) testArray);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHasNextWithNoElements() {
        PeekingIterator<String> it = new PeekingIterator<>(new ArrayList<String>().iterator());
        assertFalse(it.hasNext());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHasNextAfterPeek() {
        PeekingIterator<String> it = makeObject();
        it.peek();
        assertTrue(it.hasNext());
        validate(it, (String[]) testArray);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHasNextAfterElement() {
        PeekingIterator<String> it = makeObject();
        it.element();
        assertTrue(it.hasNext());
        validate(it, (String[]) testArray);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHasNextAfterNext() {
        PeekingIterator<String> it = makeObject();
        it.next();
        assertTrue(it.hasNext());
        validate(it, (String[]) testArray);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHasNextAfterExhaustion() {
        PeekingIterator<String> it = makeObject();
        validate(it, (String[]) testArray);
        assertFalse(it.hasNext());
    }
}
