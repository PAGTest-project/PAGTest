
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapUtils_getByteTest {

    @Test
    public void testGetByte() {
        Map<String, Number> map = new HashMap<>();
        map.put("key1", (byte) 123);
        map.put("key2", 456);

        // Test case for when the value is a Byte
        Byte result1 = MapUtils.getByte(map, "key1");
        assertEquals((byte) 123, result1);

        // Test case for when the value is a Number but not a Byte
        Byte result2 = MapUtils.getByte(map, "key2");
        assertEquals((byte) 456, result2);

        // Test case for when the value is null
        Byte result3 = MapUtils.getByte(map, "key3");
        assertNull(result3);
    }
}
