
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PassiveExpiringMap_removeTest {

    private PassiveExpiringMap<String, String> map;
    private ExpirationPolicy<String, String> mockPolicy;

    @BeforeEach
    public void setUp() {
        mockPolicy = mock(ExpirationPolicy.class);
        map = new PassiveExpiringMap<>(mockPolicy, new HashMap<>());
    }

    @Test
    public void testRemove_KeyExists() {
        // Given
        String key = "testKey";
        String value = "testValue";
        map.put(key, value);

        // When
        String result = map.remove(key);

        // Then
        assertEquals(value, result);
        assertNull(map.get(key));
    }

    @Test
    public void testRemove_KeyDoesNotExist() {
        // Given
        String key = "nonExistentKey";

        // When
        String result = map.remove(key);

        // Then
        assertNull(result);
    }

    @Test
    public void testRemove_ExpiredKey() {
        // Given
        String key = "expiredKey";
        String value = "expiredValue";
        map.put(key, value);
        when(mockPolicy.expirationTime(key, value)).thenReturn(System.currentTimeMillis() - 1000L);

        // When
        map.remove(key); // Directly remove the key
        String result = map.remove(key);

        // Then
        assertNull(result);
    }
}
