
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_sizeTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>(new HashMap<>(), new ReflectionFactory<>(ArrayList.class));
    }

    @Test
    public void testSizeWithExistingKey() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        assertEquals(2, multiValueMap.size("key1"));
    }

    @Test
    public void testSizeWithNonExistingKey() {
        assertEquals(0, multiValueMap.size("key2"));
    }

    @Test
    public void testSizeAfterRemovingAllValues() {
        multiValueMap.put("key3", "value3");
        multiValueMap.put("key3", "value4");
        multiValueMap.removeMapping("key3", "value3");
        multiValueMap.removeMapping("key3", "value4");
        assertEquals(0, multiValueMap.size("key3"));
    }

    @Test
    public void testSizeAfterAddingCollection() {
        Collection<String> values = new ArrayList<>();
        values.add("value5");
        values.add("value6");
        multiValueMap.putAll("key4", values);
        assertEquals(2, multiValueMap.size("key4"));
    }
}
