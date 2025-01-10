
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollatingIterator_hasNextTest {

    private CollatingIterator<Integer> collatingIterator;
    private List<Iterator<Integer>> iterators;

    @BeforeEach
    public void setUp() {
        collatingIterator = new CollatingIterator<>(Comparator.naturalOrder());
        iterators = new ArrayList<>();
    }

    @Test
    public void testHasNext_WithNoIterators() {
        assertFalse(collatingIterator.hasNext());
    }

    @Test
    public void testHasNext_WithEmptyIterators() {
        iterators.add(Arrays.asList().iterator());
        iterators.add(Arrays.asList().iterator());
        iterators.forEach(collatingIterator::addIterator);
        assertFalse(collatingIterator.hasNext());
    }

    @Test
    public void testHasNext_WithNonEmptyIterators() {
        iterators.add(Arrays.asList(1, 2).iterator());
        iterators.add(Arrays.asList(3, 4).iterator());
        iterators.forEach(collatingIterator::addIterator);
        assertTrue(collatingIterator.hasNext());
    }
}
