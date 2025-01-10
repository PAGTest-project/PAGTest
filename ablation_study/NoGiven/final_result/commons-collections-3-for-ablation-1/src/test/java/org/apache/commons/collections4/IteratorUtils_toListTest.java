
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_toListTest {

    @Test
    public void testToList_SuccessfulConversion() {
        // Given
        List<String> inputList = Arrays.asList("a", "b", "c");
        Iterator<String> iterator = inputList.iterator();
        int estimatedSize = 3;

        // When
        List<String> result = IteratorUtils.toList(iterator, estimatedSize);

        // Then
        assertEquals(inputList, result);
    }

    @Test
    public void testToList_NullIterator() {
        // Given
        Iterator<String> iterator = null;
        int estimatedSize = 3;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.toList(iterator, estimatedSize);
        });
    }

    @Test
    public void testToList_InvalidEstimatedSize() {
        // Given
        List<String> inputList = Arrays.asList("a", "b", "c");
        Iterator<String> iterator = inputList.iterator();
        int estimatedSize = 0;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            IteratorUtils.toList(iterator, estimatedSize);
        });
    }
}
