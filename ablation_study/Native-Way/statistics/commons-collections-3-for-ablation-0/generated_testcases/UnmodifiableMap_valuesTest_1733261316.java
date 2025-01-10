
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableMap_valuesTest {

    @Test
    public void testValues() {
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");

        UnmodifiableMap<String, String> unmodifiableMap = UnmodifiableMap.unmodifiableMap(originalMap);
        Collection<String> values = unmodifiableMap.values();

        assertTrue(values instanceof UnmodifiableCollection);
    }
}
