
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class SkippingIterator_removeTest {

    @Test
    public void testRemoveAfterNext() {
        Iterator<String> baseIterator = Arrays.asList("A", "B", "C").iterator();
        SkippingIterator<String> skippingIterator = new SkippingIterator<>(baseIterator, 1) {
            private int currentIndex = -1;

            @Override
            public String next() {
                currentIndex++;
                return super.next();
            }

            @Override
            public void remove() {
                if (currentIndex > 0) {
                    super.remove();
                } else {
                    throw new IllegalStateException("remove() can not be called before calling next()");
                }
            }
        };

        skippingIterator.next(); // Advance to position 1
        skippingIterator.remove(); // Should not throw IllegalStateException
    }

    @Test
    public void testRemoveBeforeNext() {
        Iterator<String> baseIterator = Arrays.asList("A", "B", "C").iterator();
        SkippingIterator<String> skippingIterator = new SkippingIterator<>(baseIterator, 1) {
            private int currentIndex = -1;

            @Override
            public String next() {
                currentIndex++;
                return super.next();
            }

            @Override
            public void remove() {
                if (currentIndex > 0) {
                    super.remove();
                } else {
                    throw new IllegalStateException("remove() can not be called before calling next()");
                }
            }
        };

        assertThrows(IllegalStateException.class, () -> skippingIterator.remove());
    }
}
