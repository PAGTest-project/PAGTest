
package org.apache.commons.collections4.keyvalue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiedMapEntry_hashCodeTest {

    private Map<String, String> map;
    private TiedMapEntry<String, String> entry;

    @BeforeEach
    public void setUp() {
        map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        entry = new TiedMapEntry<>(map, "key1");
    }

    @Test
    public void testHashCodeWithNonNullKeyAndValue() {
        assertEquals(("key1".hashCode() ^ "value1".hashCode()), entry.hashCode());
    }

    @Test
    public void testHashCodeWithNullKey() {
        entry = new TiedMapEntry<>(map, null);
        assertEquals(0, entry.hashCode());
    }

    @Test
    public void testHashCodeWithNullValue() {
        map.put("key1", null);
        assertEquals("key1".hashCode(), entry.hashCode());
    }

    @Test
    public void testHashCodeWithNullKeyAndValue() {
        entry = new TiedMapEntry<>(map, null);
        map.put(null, null);
        assertEquals(0, entry.hashCode());
    }
}
