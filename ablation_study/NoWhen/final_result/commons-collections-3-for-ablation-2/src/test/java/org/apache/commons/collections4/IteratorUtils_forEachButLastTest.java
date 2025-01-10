
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

class IteratorUtils_forEachButLastTest {

    @Test
    void testForEachButLast_SingleElement() {
        Iterator<String> iterator = Arrays.asList("A").iterator();
        Closure<String> closure = element -> {};

        String result = IteratorUtils.forEachButLast(iterator, closure);

        assertEquals("A", result);
    }

    @Test
    void testForEachButLast_MultipleElements() {
        Iterator<String> iterator = Arrays.asList("A", "B", "C").iterator();
        Closure<String> closure = element -> {};

        String result = IteratorUtils.forEachButLast(iterator, closure);

        assertEquals("C", result);
    }

    @Test
    void testForEachButLast_NullIterator() {
        Closure<String> closure = element -> {};

        String result = IteratorUtils.forEachButLast(null, closure);

        assertNull(result);
    }

    @Test
    void testForEachButLast_NullClosure() {
        Iterator<String> iterator = Arrays.asList("A").iterator();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.forEachButLast(iterator, null);
        });
    }
}
