
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IteratorUtils_filteredIteratorTest {

    @Test
    public void testFilteredIterator() {
        // Given
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4, 5).iterator();
        Predicate<Integer> predicate = i -> i % 2 == 0;

        // When
        Iterator<Integer> filteredIterator = IteratorUtils.filteredIterator(iterator, predicate);

        // Then
        assertNotNull(filteredIterator);
        assertTrue(filteredIterator.hasNext());
    }
}
