
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class SetUtils_unmodifiableSetTest {

    @Test
    void testUnmodifiableSetWithNull() {
        // Given
        String[] items = null;

        // When
        Set<String> result = SetUtils.unmodifiableSet(items);

        // Then
        assertNull(result);
    }

    @Test
    void testUnmodifiableSetWithItems() {
        // Given
        String[] items = {"item1", "item2"};

        // When
        Set<String> result = SetUtils.unmodifiableSet(items);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("item1"));
        assertTrue(result.contains("item2"));
        assertEquals(2, result.size());
    }
}
