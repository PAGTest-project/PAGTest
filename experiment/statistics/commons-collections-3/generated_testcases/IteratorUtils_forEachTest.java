
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IteratorUtils_forEachTest {

    @Test
    void testForEach_NullClosure() {
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.forEach(mock(Iterator.class), null);
        });
    }

    @Test
    void testForEach_NonNullIterator() {
        Iterator<String> iterator = mock(Iterator.class);
        Closure<String> closure = mock(Closure.class);

        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn("element");

        IteratorUtils.forEach(iterator, closure);

        verify(closure, times(1)).accept("element");
    }

    @Test
    void testForEach_NullIterator() {
        Closure<String> closure = mock(Closure.class);

        IteratorUtils.forEach(null, closure);

        verifyNoInteractions(closure);
    }
}
