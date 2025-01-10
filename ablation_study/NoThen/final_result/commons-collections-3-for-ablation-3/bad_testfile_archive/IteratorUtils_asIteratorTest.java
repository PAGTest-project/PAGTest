
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IteratorUtils_asIteratorTest {

    @Test
    public void testAsIterator() {
        // Given
        List<String> list = List.of("a", "b", "c");
        Enumeration<String> enumeration = Collections.enumeration(list);
        List<String> removeCollection = Collections.emptyList();

        // When
        Iterator<String> iterator = IteratorUtils.asIterator(enumeration, removeCollection);

        // Then
        assertNotNull(iterator);
        assertTrue(iterator.hasNext());
    }
}
