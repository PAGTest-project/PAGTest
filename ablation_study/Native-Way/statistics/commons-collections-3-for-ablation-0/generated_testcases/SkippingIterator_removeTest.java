
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Iterator;

public class SkippingIterator_removeTest {

    @Test
    public void testRemoveBeforeNext() {
        Iterator<Object> mockIterator = mock(Iterator.class);
        SkippingIterator<Object> skippingIterator = new SkippingIterator<>(mockIterator, 0);

        assertThrows(IllegalStateException.class, () -> skippingIterator.remove());
    }

    @Test
    public void testRemoveAfterNext() {
        Iterator<Object> mockIterator = mock(Iterator.class);
        when(mockIterator.hasNext()).thenReturn(true, false);
        when(mockIterator.next()).thenReturn(new Object());

        SkippingIterator<Object> skippingIterator = new SkippingIterator<>(mockIterator, 0);
        skippingIterator.next();
        skippingIterator.remove();

        verify(mockIterator).remove();
    }
}
