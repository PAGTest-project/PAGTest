
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
    public void testSizeAfterRemovingValues() {
        multiValueMap.put("key3", "value3");
        multiValueMap.put("key3", "value4");
        multiValueMap.removeMapping("key3", "value3");
        assertEquals(1, multiValueMap.size("key3"));
    }

    @Test
    public void testSizeAfterClearingCollection() {
        multiValueMap.put("key4", "value5");
        multiValueMap.put("key4", "value6");
        Collection<String> coll = multiValueMap.getCollection("key4");
        coll.clear();
        assertEquals(0, multiValueMap.size("key4"));
    }

    @Test
    public void testSizeWithEmptyCollection() {
        multiValueMap.put("key5", "value7");
        multiValueMap.remove("key5");
        assertEquals(0, multiValueMap.size("key5"));
    }
}
