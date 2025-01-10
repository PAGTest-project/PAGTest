
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IteratorUtils_asIteratorTest {

    @Test
    void testAsIterator() {
        // Given
        List<String> list = List.of("a", "b", "c");
        Enumeration<String> enumeration = Collections.enumeration(list);
        Collection<String> removeCollection = new ArrayList<>();

        // When
        Iterator<String> iterator = IteratorUtils.asIterator(enumeration, removeCollection);

        // Then
        assertFalse(IteratorUtils.isEmpty(iterator));
        assertEquals(3, IteratorUtils.size(iterator));
    }

    @Test
    void testAsIteratorWithNullEnumeration() {
        // Given
        Enumeration<String> enumeration = null;
        Collection<String> removeCollection = new ArrayList<>();

        // When & Then
        assertThrows(NullPointerException.class, () -> IteratorUtils.asIterator(enumeration, removeCollection));
    }

    @Test
    void testAsIteratorWithNullRemoveCollection() {
        // Given
        List<String> list = List.of("a", "b", "c");
        Enumeration<String> enumeration = Collections.enumeration(list);
        Collection<String> removeCollection = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> IteratorUtils.asIterator(enumeration, removeCollection));
    }
}
