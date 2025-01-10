
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultedMap_getTest {

    private DefaultedMap<String, String> defaultedMap;

    @BeforeEach
    public void setUp() {
        Map<String, String> baseMap = new HashMap<>();
        Transformer<String, String> transformer = ConstantTransformer.constantTransformer("Default");
        defaultedMap = new DefaultedMap<>(baseMap, transformer);
    }

    @Test
    public void testGetExistingKey() {
        defaultedMap.put("Key", "Value");
        assertEquals("Value", defaultedMap.get("Key"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertFalse(defaultedMap.containsKey("NotInMap"));
        assertEquals("Default", defaultedMap.get("NotInMap"));
    }

    @Test
    public void testGetNullKey() {
        assertFalse(defaultedMap.containsKey(null));
        assertEquals("Default", defaultedMap.get(null));
    }

    @Test
    public void testGetAfterAddingAndRemovingKey() {
        defaultedMap.put("Key", "Value");
        assertEquals("Value", defaultedMap.get("Key"));
        defaultedMap.remove("Key");
        assertFalse(defaultedMap.containsKey("Key"));
        assertEquals("Default", defaultedMap.get("Key"));
    }

    @Test
    public void testGetWithEmptyMap() {
        assertEquals(0, defaultedMap.size());
        assertFalse(defaultedMap.containsKey("NotInMap"));
        assertEquals("Default", defaultedMap.get("NotInMap"));
    }
}
