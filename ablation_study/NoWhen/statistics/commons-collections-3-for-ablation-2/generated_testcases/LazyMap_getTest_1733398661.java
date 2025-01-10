
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LazyMap_getTest {

    private Map<String, String> map;
    private Factory<String> factory;
    private LazyMap<String, String> lazyMap;

    @BeforeEach
    public void setUp() {
        map = new HashMap<>();
        factory = mock(Factory.class);
        lazyMap = LazyMap.lazyMap(map, factory);
    }

    @Test
    public void testGet_KeyNotInMap() {
        String key = "newKey";
        String value = "newValue";

        when(factory.create()).thenReturn(value);

        String result = lazyMap.get(key);

        assertEquals(value, result);
        verify(factory).create();
        assertEquals(value, map.get(key));
    }

    @Test
    public void testGet_KeyInMap() {
        String key = "existingKey";
        String value = "existingValue";
        map.put(key, value);

        String result = lazyMap.get(key);

        assertEquals(value, result);
        verify(factory, never()).create();
    }
}
