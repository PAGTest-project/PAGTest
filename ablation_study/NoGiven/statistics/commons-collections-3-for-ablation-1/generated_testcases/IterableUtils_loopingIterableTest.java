
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

public class IterableUtils_loopingIterableTest {

    @Test
    public void testLoopingIterableWithNonEmptyIterable() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        Iterable<String> result = IterableUtils.loopingIterable(iterable);
        assertNotNull(result);
        assertEquals(3, IterableUtils.size(result));
    }

    @Test
    public void testLoopingIterableWithEmptyIterable() {
        Iterable<String> iterable = Collections.emptyList();
        Iterable<String> result = IterableUtils.loopingIterable(iterable);
        assertNotNull(result);
        assertTrue(IterableUtils.isEmpty(result));
    }

    @Test
    public void testLoopingIterableWithNullIterable() {
        assertThrows(NullPointerException.class, () -> {
            IterableUtils.loopingIterable(null);
        });
    }
}
