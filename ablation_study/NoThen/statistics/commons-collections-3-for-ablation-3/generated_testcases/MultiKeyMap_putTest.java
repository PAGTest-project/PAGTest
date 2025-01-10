
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

        String result = map.put(key1, key2, key3, key4, key5, value);

        assertNull(result);
        assertEquals(value, map.get(key1, key2, key3, key4, key5));
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

        map.put(key1, key2, key3, key4, key5, value1);
        String result = map.put(key1, key2, key3, key4, key5, value2);

        assertEquals(value1, result);
        assertEquals(value2, map.get(key1, key2, key3, key4, key5));
    }
}
