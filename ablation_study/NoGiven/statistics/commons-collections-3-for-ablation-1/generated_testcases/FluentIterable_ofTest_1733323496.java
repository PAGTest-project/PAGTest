
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluentIterable_ofTest {

    @Test
    void testOfWithNonFluentIterable() {
        List<String> list = Arrays.asList("a", "b", "c");
        FluentIterable<String> result = FluentIterable.of(list);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertTrue(result.contains("a"));
    }

    @Test
    void testOfWithFluentIterable() {
        FluentIterable<String> original = FluentIterable.of(Arrays.asList("a", "b", "c"));
        FluentIterable<String> result = FluentIterable.of(original);
        assertSame(original, result);
    }

    @Test
    void testOfWithNullIterable() {
        assertThrows(NullPointerException.class, () -> {
            FluentIterable.of(null);
        });
    }
}
