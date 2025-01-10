
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiValueMap_valuesTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>(new HashMap<>(), ArrayList.class);
    }

    @Test
    public void testValues() {
        // Given
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");

        // When
        Collection<String> values = multiValueMap.values();

        // Then
        assertNotNull(values);
        assertEquals(3, values.size());
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertTrue(values.contains("value3"));
    }
}
