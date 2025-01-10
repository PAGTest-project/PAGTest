
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Static_shorterThanTest {

    @Test
    void testShorterThanWithCollection() {
        List<Integer> list = ImmutableList.of(1, 2, 3);
        assertTrue(Static.shorterThan(list, 4));
        assertFalse(Static.shorterThan(list, 3));
    }

    @Test
    void testShorterThanWithIterable() {
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3);
        assertTrue(Static.shorterThan(iterable, 4));
        assertFalse(Static.shorterThan(iterable, 3));
    }

    @Test
    void testShorterThanWithNonPositiveN() {
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3);
        assertFalse(Static.shorterThan(iterable, 0));
        assertFalse(Static.shorterThan(iterable, -1));
    }

    @Test
    void testShorterThanWithEmptyIterable() {
        Iterable<Integer> emptyIterable = Collections.emptyList();
        assertTrue(Static.shorterThan(emptyIterable, 1));
        assertFalse(Static.shorterThan(emptyIterable, 0));
    }
}
