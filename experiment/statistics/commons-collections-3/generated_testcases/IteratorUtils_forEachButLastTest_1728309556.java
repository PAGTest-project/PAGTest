
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

class IteratorUtils_forEachButLastTest {

    @Test
    void testForEachButLast_WithMultipleElements() {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        Closure<String> closure = element -> {
            // No-op closure
        };

        String result = IteratorUtils.forEachButLast(iterator, closure);
        assertEquals("c", result);
    }

    @Test
    void testForEachButLast_WithSingleElement() {
        Iterator<String> iterator = Arrays.asList("a").iterator();
        Closure<String> closure = element -> {
            // No-op closure
        };

        String result = IteratorUtils.forEachButLast(iterator, closure);
        assertEquals("a", result);
    }

    @Test
    void testForEachButLast_WithEmptyIterator() {
        Iterator<String> iterator = Arrays.asList().iterator();
        Closure<String> closure = element -> {
            // No-op closure
        };

        String result = IteratorUtils.forEachButLast(iterator, closure);
        assertNull(result);
    }

    @Test
    void testForEachButLast_WithNullIterator() {
        Closure<String> closure = element -> {
            // No-op closure
        };

        String result = IteratorUtils.forEachButLast(null, closure);
        assertNull(result);
    }

    @Test
    void testForEachButLast_WithNullClosure() {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.forEachButLast(iterator, null);
        });
    }
}
