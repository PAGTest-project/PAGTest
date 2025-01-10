
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IteratorUtils_forEachTest {

    @Test
    void testForEachWithNonNullClosureAndNonNullIterator() {
        // Given
        Iterator<String> iterator = mock(Iterator.class);
        Closure<String> closure = mock(Closure.class);

        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn("element");

        // When
        IteratorUtils.forEach(iterator, closure);

        // Then
        verify(closure, times(1)).accept("element");
    }

    @Test
    void testForEachWithNonNullClosureAndNullIterator() {
        // Given
        Closure<String> closure = mock(Closure.class);

        // When
        IteratorUtils.forEach(null, closure);

        // Then
        verifyNoInteractions(closure);
    }

    @Test
    void testForEachWithNullClosure() {
        // Given
        Iterator<String> iterator = mock(Iterator.class);

        // When / Then
        assertThrows(NullPointerException.class, () -> IteratorUtils.forEach(iterator, null));
    }
}
