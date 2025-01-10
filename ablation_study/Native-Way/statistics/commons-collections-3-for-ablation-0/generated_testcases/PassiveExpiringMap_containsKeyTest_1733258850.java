
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PassiveExpiringMap_containsKeyTest {

    private PassiveExpiringMap<String, String> map;

    @Mock
    private ExpirationPolicy<String, String> expiringPolicy;

    @BeforeEach
    public void setUp() {
        map = new PassiveExpiringMap<>(expiringPolicy, new HashMap<>());
    }

    @Test
    public void testContainsKey_KeyExistsAndNotExpired() {
        // Given
        String key = "key";
        String value = "value";
        map.put(key, value);
        when(expiringPolicy.expirationTime(key, value)).thenReturn(System.currentTimeMillis() + 1000L);

        // When
        boolean result = map.containsKey(key);

        // Then
        assertTrue(result);
    }

    @Test
    public void testContainsKey_KeyExistsButExpired() {
        // Given
        String key = "key";
        String value = "value";
        map.put(key, value);
        when(expiringPolicy.expirationTime(key, value)).thenReturn(System.currentTimeMillis() - 1000L);

        // When
        boolean result = map.containsKey(key);

        // Then
        assertFalse(result);
    }

    @Test
    public void testContainsKey_KeyDoesNotExist() {
        // Given
        String key = "nonexistentKey";

        // When
        boolean result = map.containsKey(key);

        // Then
        assertFalse(result);
    }
}
