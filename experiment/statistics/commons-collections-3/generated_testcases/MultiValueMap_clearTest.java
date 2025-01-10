
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiValueMap_clearTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>(new HashMap<>(), ArrayList.class);
    }

    @Test
    public void testClear() {
        // Given
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");

        // When
        multiValueMap.clear();

        // Then
        assertTrue(multiValueMap.isEmpty());
    }
}
