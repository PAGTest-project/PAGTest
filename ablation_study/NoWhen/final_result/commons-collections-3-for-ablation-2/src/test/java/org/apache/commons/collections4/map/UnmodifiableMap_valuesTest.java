
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableMap_valuesTest {

    @Test
    void testValues() {
        // Given
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");
        Map<String, String> unmodifiableMap = UnmodifiableMap.unmodifiableMap(originalMap);

        // When
        Collection<String> values = unmodifiableMap.values();

        // Then
        assertTrue(values instanceof UnmodifiableCollection);
        assertEquals(2, values.size());
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertThrows(UnsupportedOperationException.class, () -> values.add("value3"));
    }
}
