
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IteratorUtils_toListTest {

    @Test
    void testToList_SuccessfulConversion() {
        // Given
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        int estimatedSize = 3;

        // When
        List<String> result = IteratorUtils.toList(iterator, estimatedSize);

        // Then
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }

    @Test
    void testToList_NullIterator() {
        // Given
        Iterator<String> iterator = null;
        int estimatedSize = 3;

        // When & Then
        assertThrows(NullPointerException.class, () -> IteratorUtils.toList(iterator, estimatedSize));
    }

    @Test
    void testToList_InvalidEstimatedSize() {
        // Given
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        int estimatedSize = 0;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> IteratorUtils.toList(iterator, estimatedSize));
    }
}
