
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CopyOnWriteMap_putTest {

    @Test
    public void testPut() {
        // Given
        Supplier<Map<String, String>> mapSupplier = () -> new HashMap<>();
        CopyOnWriteMap<String, String> copyOnWriteMap = new CopyOnWriteMap<>(mapSupplier);
        Map<String, String> initialMap = new HashMap<>();
        initialMap.put("key1", "value1");
        copyOnWriteMap.putAll(initialMap);

        // When
        String result = copyOnWriteMap.put("key2", "value2");

        // Then
        assertNull(result); // Since "key2" did not exist before, result should be null
        assertEquals("value2", copyOnWriteMap.get("key2")); // Verify the new key-value pair is added
        assertEquals(2, copyOnWriteMap.size()); // Verify the size of the map has increased by 1
    }
}
