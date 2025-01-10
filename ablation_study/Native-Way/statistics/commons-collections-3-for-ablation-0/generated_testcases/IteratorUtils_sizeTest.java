
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IteratorUtils_sizeTest {

    @Test
    public void testSizeWithNonNullIterator() {
        Iterator<Object> mockIterator = mock(Iterator.class);
        when(mockIterator.hasNext()).thenReturn(true, true, false);
        when(mockIterator.next()).thenReturn(new Object(), new Object());

        int result = IteratorUtils.size(mockIterator);
        assertEquals(2, result);
    }

    @Test
    public void testSizeWithNullIterator() {
        int result = IteratorUtils.size(null);
        assertEquals(0, result);
    }
}
