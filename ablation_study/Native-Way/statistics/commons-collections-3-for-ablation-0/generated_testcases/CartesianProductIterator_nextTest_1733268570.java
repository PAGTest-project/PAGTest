
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartesianProductIterator_nextTest {

    private List<Iterable<Character>> iterables;
    private CartesianProductIterator<Character> iterator;

    @BeforeEach
    public void setUp() {
        iterables = new ArrayList<>();
    }

    @Test
    public void testNextWithSingleIterable() {
        iterables.add(Arrays.asList('A', 'B', 'C'));
        iterator = new CartesianProductIterator<>(iterables.toArray(new Iterable[0]));

        assertTrue(iterator.hasNext());
        assertEquals(Arrays.asList('A'), iterator.next());
        assertEquals(Arrays.asList('B'), iterator.next());
        assertEquals(Arrays.asList('C'), iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testNextWithMultipleIterables() {
        iterables.add(Arrays.asList('A', 'B'));
        iterables.add(Arrays.asList('1', '2'));
        iterator = new CartesianProductIterator<>(iterables.toArray(new Iterable[0]));

        assertTrue(iterator.hasNext());
        assertEquals(Arrays.asList('A', '1'), iterator.next());
        assertEquals(Arrays.asList('A', '2'), iterator.next());
        assertEquals(Arrays.asList('B', '1'), iterator.next());
        assertEquals(Arrays.asList('B', '2'), iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testNextWithEmptyIterable() {
        iterables.add(Arrays.asList('A', 'B'));
        iterables.add(List.of());
        iterator = new CartesianProductIterator<>(iterables.toArray(new Iterable[0]));

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testNextWithAllEmptyLists() {
        iterables.add(List.of());
        iterables.add(List.of());
        iterables.add(List.of());
        iterator = new CartesianProductIterator<>(iterables.toArray(new Iterable[0]));

        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
