
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorUtils_filteredListIteratorTest {

    @Test
    public void testFilteredListIterator_Success() {
        // Given
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        ListIterator<Integer> listIterator = list.listIterator();
        Predicate<Integer> predicate = n -> n % 2 == 0;

        // When
        ListIterator<Integer> filteredIterator = IteratorUtils.filteredListIterator(listIterator, predicate);

        // Then
        assertNotNull(filteredIterator);
    }

    @Test
    public void testFilteredListIterator_NullListIterator() {
        // Given
        ListIterator<Integer> listIterator = null;
        Predicate<Integer> predicate = n -> n % 2 == 0;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.filteredListIterator(listIterator, predicate);
        });
    }

    @Test
    public void testFilteredListIterator_NullPredicate() {
        // Given
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        ListIterator<Integer> listIterator = list.listIterator();
        Predicate<Integer> predicate = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.filteredListIterator(listIterator, predicate);
        });
    }
}
