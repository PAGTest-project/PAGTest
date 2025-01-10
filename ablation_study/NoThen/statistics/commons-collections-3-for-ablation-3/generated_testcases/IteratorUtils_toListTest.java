
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_toListTest {

    @Test
    public void testToList_SuccessfulConversion() {
        // Given
        List<String> inputList = new ArrayList<>();
        inputList.add("A");
        inputList.add("B");
        Iterator<String> iterator = inputList.iterator();

        // When
        List<String> result = IteratorUtils.toList(iterator, 2);

        // Then
        assertEquals(inputList, result);
    }

    @Test
    public void testToList_NullIterator() {
        // Given
        Iterator<String> iterator = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.toList(iterator, 2);
        });
    }

    @Test
    public void testToList_InvalidEstimatedSize() {
        // Given
        List<String> inputList = new ArrayList<>();
        inputList.add("A");
        Iterator<String> iterator = inputList.iterator();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            IteratorUtils.toList(iterator, 0);
        });
    }
}
