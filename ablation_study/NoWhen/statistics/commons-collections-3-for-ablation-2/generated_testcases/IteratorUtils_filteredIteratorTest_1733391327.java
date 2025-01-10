
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_filteredIteratorTest {

    @Test
    public void testFilteredIterator() {
        // Given
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4, 5).iterator();
        Predicate<Integer> predicate = n -> n % 2 == 0;

        // When
        Iterator<Integer> filteredIterator = IteratorUtils.filteredIterator(iterator, new Predicate<Integer>() {
            @Override
            public boolean test(Integer n) {
                return predicate.test(n);
            }
        });

        // Then
        assertTrue(filteredIterator.hasNext());
        assertEquals(2, filteredIterator.next());
        assertTrue(filteredIterator.hasNext());
        assertEquals(4, filteredIterator.next());
        assertFalse(filteredIterator.hasNext());
    }

    @Test
    public void testFilteredIteratorWithNullIterator() {
        // Given
        Iterator<Integer> iterator = null;
        Predicate<Integer> predicate = n -> n % 2 == 0;

        // When & Then
        assertThrows(NullPointerException.class, () -> IteratorUtils.filteredIterator(iterator, new Predicate<Integer>() {
            @Override
            public boolean test(Integer n) {
                return predicate.test(n);
            }
        }));
    }

    @Test
    public void testFilteredIteratorWithNullPredicate() {
        // Given
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4, 5).iterator();
        Predicate<Integer> predicate = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> IteratorUtils.filteredIterator(iterator, new Predicate<Integer>() {
            @Override
            public boolean test(Integer n) {
                return predicate.test(n);
            }
        }));
    }
}
