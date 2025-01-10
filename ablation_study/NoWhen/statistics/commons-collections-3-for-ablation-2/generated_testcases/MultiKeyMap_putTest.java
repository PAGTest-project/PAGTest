
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MultiKeyMap_putTest {

    @Test
    public void testPutNewEntry() {
        MultiKeyMap<String, String> map = new MultiKeyMap<>();
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key3";
        String key4 = "key4";
        String key5 = "key5";
        String value = "value";

        // Given: A new entry to be added
        // When: put is called with new keys and value
        String result = map.put(key1, key2, key3, key4, key5, value);

        // Then: The result should be null (indicating a new entry)
        assertNull(result);
    }

    @Test
    public void testPutExistingEntry() {
        MultiKeyMap<String, String> map = new MultiKeyMap<>();
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key3";
        String key4 = "key4";
        String key5 = "key5";
        String value1 = "value1";
        String value2 = "value2";

        // Given: An existing entry in the map
        map.put(key1, key2, key3, key4, key5, value1);

        // When: put is called with the same keys but a different value
        String result = map.put(key1, key2, key3, key4, key5, value2);

        // Then: The result should be the old value
        assertEquals(value1, result);
    }
}
