
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorUtils_filteredIteratorTest {

    @Test
    public void testFilteredIterator_Success() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        Predicate<Integer> predicate = TruePredicate.truePredicate();

        Iterator<Integer> filteredIterator = IteratorUtils.filteredIterator(iterator, predicate);

        assertNotNull(filteredIterator);
    }

    @Test
    public void testFilteredIterator_NullIterator() {
        Predicate<Integer> predicate = TruePredicate.truePredicate();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.filteredIterator(null, predicate);
        });
    }

    @Test
    public void testFilteredIterator_NullPredicate() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.filteredIterator(iterator, null);
        });
    }
}
