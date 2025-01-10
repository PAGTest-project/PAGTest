
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        Transformer<String, String> transformer = ConstantTransformer.constantTransformer("Default");
        defaultedMap = new DefaultedMap<>(new HashMap<>(), transformer);
    }

    @Test
    public void testGetExistingKey() {
        defaultedMap.put("Key1", "Value1");
        assertEquals("Value1", defaultedMap.get("Key1"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertNull(defaultedMap.get("NonExistingKey"));
    }

    @Test
    public void testGetNonExistingKeyWithDefault() {
        assertEquals("Default", defaultedMap.get("NonExistingKey"));
    }

    @Test
    public void testGetWithNullKey() {
        assertNull(defaultedMap.get(null));
    }

    @Test
    public void testGetWithNullKeyAndDefault() {
        defaultedMap.put(null, "NullValue");
        assertEquals("NullValue", defaultedMap.get(null));
    }

    @Test
    public void testGetWithDefaultTransformer() {
        Transformer<String, String> transformer = ConstantTransformer.constantTransformer("NewDefault");
        DefaultedMap<String, String> newDefaultedMap = new DefaultedMap<>(new HashMap<>(), transformer);
        assertEquals("NewDefault", newDefaultedMap.get("NonExistingKey"));
    }

    @Test
    public void testGetWithExistingKeyAfterDefaultTransformerChange() {
        defaultedMap.put("Key2", "Value2");
        Transformer<String, String> transformer = ConstantTransformer.constantTransformer("NewDefault");
        DefaultedMap<String, String> newDefaultedMap = new DefaultedMap<>(new HashMap<>(), transformer);
        newDefaultedMap.putAll(defaultedMap);
        assertEquals("Value2", newDefaultedMap.get("Key2"));
    }

    @Test
    public void testGetWithNonExistingKeyAfterDefaultTransformerChange() {
        Transformer<String, String> transformer = ConstantTransformer.constantTransformer("NewDefault");
        DefaultedMap<String, String> newDefaultedMap = new DefaultedMap<>(new HashMap<>(), transformer);
        assertEquals("NewDefault", newDefaultedMap.get("NonExistingKey"));
    }

    @Test
    public void testGetWithDefaultTransformerAndNullKey() {
        Transformer<String, String> transformer = ConstantTransformer.constantTransformer("NewDefault");
        DefaultedMap<String, String> newDefaultedMap = new DefaultedMap<>(new HashMap<>(), transformer);
        assertNull(newDefaultedMap.get(null));
    }

    @Test
    public void testGetWithDefaultTransformerAndNullKeyAfterPut() {
        Transformer<String, String> transformer = ConstantTransformer.constantTransformer("NewDefault");
        DefaultedMap<String, String> newDefaultedMap = new DefaultedMap<>(new HashMap<>(), transformer);
        newDefaultedMap.put(null, "NullValue");
        assertEquals("NullValue", newDefaultedMap.get(null));
    }
}
