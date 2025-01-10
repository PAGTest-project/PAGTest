
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_putTest {

    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testPutNewKey() {
        String key = "key1";
        String value = "value1";
        assertNull(map.put(key, value));
        assertEquals(value, map.get(key));
    }

    @Test
    public void testPutExistingKey() {
        String key = "key1";
        String value1 = "value1";
        String value2 = "value2";
        assertNull(map.put(key, value1));
        assertEquals(value1, map.put(key, value2));
        assertEquals(value2, map.get(key));
    }

    @Test
    public void testPutNullKey() {
        String value = "value1";
        assertNull(map.put(null, value));
        assertEquals(value, map.get(null));
    }

    @Test
    public void testPutNullValue() {
        String key = "key1";
        assertNull(map.put(key, null));
        assertNull(map.get(key));
    }
}
