
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CollectionUtils_sizeTest {

    @Test
    public void testSizeWithNull() {
        assertEquals(0, CollectionUtils.size(null));
    }

    @Test
    public void testSizeWithMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals(2, CollectionUtils.size(map));
    }

    @Test
    public void testSizeWithUnsupportedObject() {
        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.size("UnsupportedType"));
    }
}
