
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

    private PeekingIterator<String> makeObject() {
        return new PeekingIterator<>(testList.iterator());
    }

    @Test
    public void testHasNextWhenIteratorHasNext() {
        PeekingIterator<String> it = makeObject();
        assertTrue(it.hasNext());
    }

    @Test
    public void testHasNextWhenIteratorIsExhausted() {
        PeekingIterator<String> it = makeObject();
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void testHasNextWhenSlotIsFilled() {
        PeekingIterator<String> it = makeObject();
        it.peek(); // This should fill the slot
        assertTrue(it.hasNext());
    }

    @Test
    public void testHasNextWhenSlotIsFilledAndIteratorIsExhausted() {
        PeekingIterator<String> it = makeObject();
        it.next();
        it.next();
        it.next();
        it.peek(); // This should not fill the slot as iterator is exhausted
        assertFalse(it.hasNext());
    }
}
