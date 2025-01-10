
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

public class IterableUtils_boundedIterableTest {

    @Test
    public void testBoundedIterable_NormalCase() {
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3, 4, 5);
        Iterable<Integer> boundedIterable = IterableUtils.boundedIterable(iterable, 3);
        assertEquals(3, IterableUtils.size(boundedIterable));
    }

    @Test
    public void testBoundedIterable_NegativeMaxSize() {
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3, 4, 5);
        assertThrows(IllegalArgumentException.class, () -> {
            IterableUtils.boundedIterable(iterable, -1);
        });
    }

    @Test
    public void testBoundedIterable_NullIterable() {
        assertThrows(NullPointerException.class, () -> {
            IterableUtils.boundedIterable(null, 3);
        });
    }
}
