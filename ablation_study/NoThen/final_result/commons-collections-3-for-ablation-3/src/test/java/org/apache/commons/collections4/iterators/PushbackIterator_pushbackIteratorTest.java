
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PushbackIterator_pushbackIteratorTest {

    @Test
    void testPushbackIteratorWithNonNullIterator() {
        // Given
        Iterator<String> mockIterator = mock(Iterator.class);

        // When
        PushbackIterator<String> result = PushbackIterator.pushbackIterator(mockIterator);

        // Then
        assertNotNull(result);
        assertNotSame(mockIterator, result);
    }

    @Test
    void testPushbackIteratorWithPushbackIteratorInstance() {
        // Given
        PushbackIterator<String> mockPushbackIterator = mock(PushbackIterator.class);

        // When
        PushbackIterator<String> result = PushbackIterator.pushbackIterator(mockPushbackIterator);

        // Then
        assertSame(mockPushbackIterator, result);
    }

    @Test
    void testPushbackIteratorWithNullIterator() {
        // Given
        Iterator<String> nullIterator = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            PushbackIterator.pushbackIterator(nullIterator);
        });
    }
}
