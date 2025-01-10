
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkippingIterator_nextTest {

    @Test
    public void testNext() {
        Iterator<String> iterator = Arrays.asList("a", "b", "c", "d").iterator();
        SkippingIterator<String> skippingIterator = new SkippingIterator<>(iterator, 2);

        assertEquals("c", skippingIterator.next());
        assertEquals("d", skippingIterator.next());
    }
}
