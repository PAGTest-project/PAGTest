
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class CopyOnWriteMap_removeTest {

    @Test
    public void testRemove() {
        // Given
        Supplier<Map<String, String>> mapSupplier = HashMap::new;
        CopyOnWriteMap<String, String> copyOnWriteMap = new CopyOnWriteMap<>(mapSupplier);
        copyOnWriteMap.put("key1", "value1");
        copyOnWriteMap.put("key2", "value2");

        // When
        String removedValue = copyOnWriteMap.remove("key1");

        // Then
        assertEquals("value1", removedValue);
        assertFalse(copyOnWriteMap.containsKey("key1"));
        assertEquals(1, copyOnWriteMap.size());
    }
}
