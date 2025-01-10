
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class CopyOnWriteMap_putAllTest {

    @Test
    public void testPutAll() {
        // Given
        Supplier<Map<String, Integer>> mapSupplier = () -> new HashMap<>();
        CopyOnWriteMap<String, Integer> copyOnWriteMap = new CopyOnWriteMap<>(mapSupplier);
        Map<String, Integer> initialMap = new HashMap<>();
        initialMap.put("key1", 1);
        initialMap.put("key2", 2);
        copyOnWriteMap.putAll(initialMap);

        Map<String, Integer> newMap = new HashMap<>();
        newMap.put("key3", 3);
        newMap.put("key4", 4);

        // When
        copyOnWriteMap.putAll(newMap);

        // Then
        assertEquals(4, copyOnWriteMap.size());
        assertTrue(copyOnWriteMap.containsKey("key1"));
        assertTrue(copyOnWriteMap.containsKey("key2"));
        assertTrue(copyOnWriteMap.containsKey("key3"));
        assertTrue(copyOnWriteMap.containsKey("key4"));
    }
}
