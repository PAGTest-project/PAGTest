
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeekingIterator_elementTest {

    private PeekingIterator<String> peekingIterator;
    private List<String> elements;

    @BeforeEach
    public void setUp() {
        elements = new ArrayList<>(Arrays.asList("A", "B", "C"));
        peekingIterator = new PeekingIterator<>(elements.iterator());
    }

    @Test
    public void testElementWithElements() {
        assertEquals("A", peekingIterator.element());
        assertEquals("A", peekingIterator.next());
        assertEquals("B", peekingIterator.element());
    }

    @Test
    public void testElementWithEmptyIterator() {
        Iterator<String> emptyIterator = makeEmptyIterator();
        PeekingIterator<String> emptyPeekingIterator = new PeekingIterator<>(emptyIterator);
        assertThrows(NoSuchElementException.class, () -> emptyPeekingIterator.element());
    }

    @Test
    public void testElementAfterExhaustion() {
        while (peekingIterator.hasNext()) {
            peekingIterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> peekingIterator.element());
    }

    private Iterator<String> makeEmptyIterator() {
        return new ArrayList<String>().iterator();
    }
}
