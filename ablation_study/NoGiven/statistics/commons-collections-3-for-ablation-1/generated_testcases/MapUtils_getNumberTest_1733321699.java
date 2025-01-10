
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapUtils_getNumberTest {

    @Test
    public void testGetNumber_NumberValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", 123);
        Number result = MapUtils.getNumber(map, "key1");
        assertEquals(123, result);
    }

    @Test
    public void testGetNumber_StringValue() throws ParseException {
        Map<String, Object> map = new HashMap<>();
        map.put("key2", "456");
        Number result = MapUtils.getNumber(map, "key2");
        assertEquals(456, result);
    }

    @Test
    public void testGetNumber_NullValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("key3", null);
        Number result = MapUtils.getNumber(map, "key3");
        assertNull(result);
    }

    @Test
    public void testGetNumber_NonNumberStringValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("key4", "notANumber");
        Number result = MapUtils.getNumber(map, "key4");
        assertNull(result);
    }

    @Test
    public void testGetNumber_NullMap() {
        Number result = MapUtils.getNumber(null, "key5");
        assertNull(result);
    }
}
