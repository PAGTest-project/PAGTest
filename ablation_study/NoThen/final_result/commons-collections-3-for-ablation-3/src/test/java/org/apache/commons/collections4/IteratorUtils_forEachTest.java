
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.NOPClosure;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class IteratorUtils_forEachTest {

    @Test
    public void testForEach_NullIterator() {
        Closure<Object> closure = NOPClosure.nopClosure();
        IteratorUtils.forEach(null, closure);
        // No exception should be thrown
    }

    @Test
    public void testForEach_NonNullIterator() {
        Iterator<String> iterator = mock(Iterator.class);
        Closure<String> closure = mock(Closure.class);

        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn("test");

        IteratorUtils.forEach(iterator, closure);

        verify(closure, times(1)).accept("test");
    }

    @Test
    public void testForEach_NullClosure() {
        Iterator<String> iterator = mock(Iterator.class);
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.forEach(iterator, null);
        });
    }

    @Test
    public void testForEach_IteratorThrowsException() {
        Iterator<String> iterator = mock(Iterator.class);
        Closure<String> closure = mock(Closure.class);

        when(iterator.hasNext()).thenReturn(true);
        when(iterator.next()).thenThrow(new NoSuchElementException());

        assertThrows(NoSuchElementException.class, () -> {
            IteratorUtils.forEach(iterator, closure);
        });
    }
}
