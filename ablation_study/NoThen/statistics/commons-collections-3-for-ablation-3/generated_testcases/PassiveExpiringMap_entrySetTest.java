
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.map.PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassiveExpiringMap_entrySetTest {

    @Test
    public void testEntrySet() {
        // Given
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(new ConstantTimeToLiveExpirationPolicy<>(1000L), new HashMap<>());
        map.put("key1", "value1");
        map.put("key2", "value2");

        // When
        Set<Map.Entry<String, String>> entrySet = map.entrySet();

        // Then
        assertEquals(2, entrySet.size());
    }
}
