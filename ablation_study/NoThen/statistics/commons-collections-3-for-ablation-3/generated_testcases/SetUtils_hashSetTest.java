
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class SetUtils_hashSetTest {

    @Test
    public void testHashSetWithItems() {
        // Given
        String[] items = {"item1", "item2"};

        // When
        HashSet<String> result = SetUtils.hashSet(items);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("item1"));
        assertTrue(result.contains("item2"));
    }

    @Test
    public void testHashSetWithNull() {
        // Given
        String[] items = null;

        // When
        HashSet<String> result = SetUtils.hashSet(items);

        // Then
        assertNull(result);
    }
}
