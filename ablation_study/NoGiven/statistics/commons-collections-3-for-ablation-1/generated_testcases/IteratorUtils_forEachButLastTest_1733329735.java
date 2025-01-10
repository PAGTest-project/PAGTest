
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;

public class IteratorUtils_forEachButLastTest {

    @Test
    public void testForEachButLast_SingleElement() {
        Iterator<String> iterator = mock(Iterator.class);
        Closure<String> closure = mock(Closure.class);

        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn("single");

        String result = IteratorUtils.forEachButLast(iterator, closure);

        assertEquals("single", result);
        verify(closure, never()).accept(any());
    }

    @Test
    public void testForEachButLast_MultipleElements() {
        Iterator<String> iterator = mock(Iterator.class);
        Closure<String> closure = mock(Closure.class);

        when(iterator.hasNext()).thenReturn(true, true, false);
        when(iterator.next()).thenReturn("first", "last");

        String result = IteratorUtils.forEachButLast(iterator, closure);

        assertEquals("last", result);
        verify(closure).accept("first");
    }

    @Test
    public void testForEachButLast_NullIterator() {
        Closure<String> closure = mock(Closure.class);

        String result = IteratorUtils.forEachButLast(null, closure);

        assertNull(result);
        verify(closure, never()).accept(any());
    }

    @Test
    public void testForEachButLast_NullClosure() {
        Iterator<String> iterator = mock(Iterator.class);

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.forEachButLast(iterator, null);
        });
    }
}
