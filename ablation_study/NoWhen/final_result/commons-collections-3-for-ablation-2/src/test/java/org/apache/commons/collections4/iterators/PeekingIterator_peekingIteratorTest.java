
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeekingIterator_peekingIteratorTest {

    private List<String> testList;
    private static final String[] testArray = {"a", "b", "c"};

    @SuppressWarnings("unchecked")
    @BeforeEach
    protected void setUp() throws Exception {
        testList = new ArrayList<>(Arrays.asList((String[]) testArray));
    }

    @Test
    public void testPeekingIteratorWithNonNullIterator() {
        Iterator<String> iterator = testList.iterator();
        PeekingIterator<String> peekingIterator = PeekingIterator.peekingIterator(iterator);
        assertNotNull(peekingIterator);
        assertEquals("a", peekingIterator.peek());
        assertEquals("a", peekingIterator.element());
        validate(peekingIterator, testArray);
    }

    @Test
    public void testPeekingIteratorWithNullIterator() {
        Iterator<String> iterator = null;
        assertThrows(NullPointerException.class, () -> {
            PeekingIterator.peekingIterator(iterator);
        });
    }

    @Test
    public void testPeekingIteratorWithPeekingIterator() {
        Iterator<String> iterator = testList.iterator();
        PeekingIterator<String> originalPeekingIterator = new PeekingIterator<>(iterator);
        PeekingIterator<String> peekingIterator = PeekingIterator.peekingIterator(originalPeekingIterator);
        assertSame(originalPeekingIterator, peekingIterator);
    }

    private void validate(Iterator<String> iter, String... items) {
        for (String item : items) {
            assertTrue(iter.hasNext());
            assertEquals(item, iter.next());
        }
        assertFalse(iter.hasNext());
    }
}
