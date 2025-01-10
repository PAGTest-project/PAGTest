
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_sizeTest {

    @Test
    public void testSizeWithNullIterator() {
        // Given
        Iterator<?> iterator = null;

        // When
        int size = IteratorUtils.size(iterator);

        // Then
        assertEquals(0, size);
    }

    @Test
    public void testSizeWithEmptyIterator() {
        // Given
        Iterator<?> iterator = IteratorUtils.emptyIterator();

        // When
        int size = IteratorUtils.size(iterator);

        // Then
        assertEquals(0, size);
    }

    @Test
    public void testSizeWithSingletonIterator() {
        // Given
        Iterator<?> iterator = IteratorUtils.singletonIterator("element");

        // When
        int size = IteratorUtils.size(iterator);

        // Then
        assertEquals(1, size);
    }

    @Test
    public void testSizeWithArrayIterator() {
        // Given
        Iterator<?> iterator = IteratorUtils.arrayIterator(new String[]{"a", "b", "c"});

        // When
        int size = IteratorUtils.size(iterator);

        // Then
        assertEquals(3, size);
    }
}
