
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PassiveExpiringMap_getTest {

    private PassiveExpiringMap<String, String> map;
    private PassiveExpiringMap.ExpirationPolicy<String, String> mockPolicy;

    @BeforeEach
    public void setUp() {
        mockPolicy = mock(PassiveExpiringMap.ExpirationPolicy.class);
        map = new PassiveExpiringMap<>(mockPolicy, new HashMap<>());
    }

    @Test
    public void testGet_EntryNotExpired() {
        String key = "key";
        String value = "value";
        map.put(key, value);

        when(mockPolicy.expirationTime(key, value)).thenReturn(System.currentTimeMillis() + 1000L);

        assertEquals(value, map.get(key));
    }

    @Test
    public void testGet_EntryExpired() {
        String key = "key";
        String value = "value";
        map.put(key, value);

        when(mockPolicy.expirationTime(key, value)).thenReturn(System.currentTimeMillis() - 1000L);

        assertNull(map.get(key));
    }
}
