
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;

public class IteratorUtils_sizeTest {

    @Test
    public void testSizeWithNonNullIterator() {
        // Given
        Iterator<Integer> iterator = IteratorUtils.arrayIterator(new Integer[]{1, 2, 3});

        // When
        int size = IteratorUtils.size(iterator);

        // Then
        assertEquals(3, size);
    }

    @Test
    public void testSizeWithNullIterator() {
        // Given
        Iterator<?> iterator = null;

        // When
        int size = IteratorUtils.size(iterator);

        // Then
        assertEquals(0, size);
    }
}
