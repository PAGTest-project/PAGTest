
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.map.AbstractHashedMap.HashEntry;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MultiKeyMap_containsKeyTest {

    @Test
    void testContainsKey_KeyExists() {
        // Given
        MultiKeyMap<Object, Object> map = new MultiKeyMap<>();
        Object key1 = new Object();
        Object key2 = new Object();
        int hashCode = map.hash(key1, key2);
        HashEntry<MultiKey<? extends Object>, Object> entry = mock(HashEntry.class);
        when(entry.hashCode).thenReturn(hashCode);
        when(entry.next).thenReturn(null);
        when(map.decorated().data).thenReturn(new HashEntry[]{entry});
        when(map.decorated().hashIndex(hashCode, 1)).thenReturn(0);
        when(map.isEqualKey(entry, key1, key2)).thenReturn(true);

        // When
        boolean result = map.containsKey(key1, key2);

        // Then
        assertTrue(result);
    }

    @Test
    void testContainsKey_KeyDoesNotExist() {
        // Given
        MultiKeyMap<Object, Object> map = new MultiKeyMap<>();
        Object key1 = new Object();
        Object key2 = new Object();
        int hashCode = map.hash(key1, key2);
        HashEntry<MultiKey<? extends Object>, Object> entry = mock(HashEntry.class);
        when(entry.hashCode).thenReturn(hashCode + 1); // Different hash code
        when(entry.next).thenReturn(null);
        when(map.decorated().data).thenReturn(new HashEntry[]{entry});
        when(map.decorated().hashIndex(hashCode, 1)).thenReturn(0);
        when(map.isEqualKey(entry, key1, key2)).thenReturn(false);

        // When
        boolean result = map.containsKey(key1, key2);

        // Then
        assertFalse(result);
    }
}
