
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapUtils_toPropertiesTest {

    @Test
    public void testToProperties() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        // When
        Properties properties = MapUtils.toProperties(map);

        // Then
        assertEquals("value1", properties.getProperty("key1"));
        assertEquals("value2", properties.getProperty("key2"));
    }
}
