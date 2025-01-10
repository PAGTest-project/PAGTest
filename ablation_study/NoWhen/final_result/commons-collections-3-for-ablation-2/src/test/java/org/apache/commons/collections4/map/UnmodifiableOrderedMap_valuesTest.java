
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableOrderedMap_valuesTest {

    @Test
    public void testValuesReturnsUnmodifiableCollection() {
        // Given
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");
        OrderedMap<String, String> orderedMap = UnmodifiableOrderedMap.unmodifiableOrderedMap(new LinkedMap<>(originalMap));

        // When
        Collection<String> values = orderedMap.values();

        // Then
        assertTrue(values instanceof UnmodifiableCollection);
    }
}
