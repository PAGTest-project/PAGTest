
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Static_shorterThanTest {

    @Test
    public void testShorterThan_Collection() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        assertTrue(Static.shorterThan(collection, 4));
        assertFalse(Static.shorterThan(collection, 3));
    }

    @Test
    public void testShorterThan_Iterable() {
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3);
        assertTrue(Static.shorterThan(iterable, 4));
        assertFalse(Static.shorterThan(iterable, 3));
    }

    @Test
    public void testShorterThan_NonCollectionIterable() {
        Iterable<Integer> iterable = new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return Arrays.asList(1, 2, 3).iterator();
            }
        };
        assertTrue(Static.shorterThan(iterable, 4));
        assertFalse(Static.shorterThan(iterable, 3));
    }

    @Test
    public void testShorterThan_NonPositiveN() {
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3);
        assertFalse(Static.shorterThan(iterable, 0));
        assertFalse(Static.shorterThan(iterable, -1));
    }
}
