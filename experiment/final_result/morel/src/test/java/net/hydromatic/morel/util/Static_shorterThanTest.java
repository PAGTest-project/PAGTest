
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Static_shorterThanTest {

    @Test
    void testShorterThanWithCollection() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        assertTrue(Static.shorterThan(collection, 4));
        assertFalse(Static.shorterThan(collection, 3));
    }

    @Test
    void testShorterThanWithIterable() {
        Iterable<Integer> iterable = ImmutableList.of(1, 2, 3);
        assertTrue(Static.shorterThan(iterable, 4));
        assertFalse(Static.shorterThan(iterable, 3));
    }

    @Test
    void testShorterThanWithNonPositiveN() {
        Iterable<Integer> iterable = ImmutableList.of(1, 2, 3);
        assertFalse(Static.shorterThan(iterable, 0));
        assertFalse(Static.shorterThan(iterable, -1));
    }
}
