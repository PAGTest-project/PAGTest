
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SkippingIterator_removeTest {

    @Test
    public void testRemoveBeforeNext() {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        SkippingIterator<String> skippingIterator = new SkippingIterator<>(iterator, 1);

        // Given: Iterator is initialized with offset 1
        // When: remove() is called before next()
        // Then: IllegalStateException is thrown
        assertThrows(IllegalStateException.class, () -> skippingIterator.remove());
    }

    @Test
    public void testRemoveAfterNext() {
        List<String> list = Arrays.asList("a", "b", "c");
        Iterator<String> iterator = list.iterator();
        SkippingIterator<String> skippingIterator = new SkippingIterator<>(iterator, 1);

        // Given: Iterator is initialized with offset 1
        // When: next() is called and then remove()
        // Then: No exception is thrown
        skippingIterator.next();
        assertDoesNotThrow(() -> skippingIterator.remove());
    }
}
