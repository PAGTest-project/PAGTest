
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterIterator_setIteratorTest {

    private FilterIterator<String> filterIterator;
    private Iterator<String> baseIterator;
    private Predicate<String> predicate;

    @BeforeEach
    public void setUp() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        baseIterator = list.iterator();
        predicate = TruePredicate.truePredicate();
        filterIterator = new FilterIterator<>(baseIterator, predicate);
    }

    @Test
    public void testSetIterator() {
        List<String> newList = new ArrayList<>(Arrays.asList("d", "e", "f"));
        Iterator<String> newIterator = newList.iterator();
        filterIterator.setIterator(newIterator);

        assertEquals(newIterator, filterIterator.getIterator());
        assertFalse(filterIterator.hasNext()); // Ensure the iterator is reset
    }
}
