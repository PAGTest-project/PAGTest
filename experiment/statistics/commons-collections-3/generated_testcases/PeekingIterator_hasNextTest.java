
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
    public void testHasNextWhenExhausted() {
        PeekingIterator<String> it = makeObject();
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void testHasNextWhenSlotFilled() {
        PeekingIterator<String> it = makeObject();
        it.peek(); // Fill the slot
        assertTrue(it.hasNext());
    }

    @Test
    public void testHasNextWhenIteratorHasNext() {
        PeekingIterator<String> it = makeObject();
        assertTrue(it.hasNext());
    }

    @Test
    public void testHasNextWhenBothExhaustedAndSlotNotFilled() {
        PeekingIterator<String> it = makeObject();
        it.next();
        it.next();
        it.next();
        it.peek(); // Ensure slot is not filled
        assertFalse(it.hasNext());
    }
}
