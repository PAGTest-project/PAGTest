
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Msg;
import org.apache.commons.validator.Var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidatorUtils_copyMapTest {

    private Map<String, Object> originalMap;

    @BeforeEach
    public void setUp() {
        originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", new Msg());
        originalMap.put("key3", new Arg());
        originalMap.put("key4", new Var());
    }

    @Test
    public void testCopyMap() {
        Map<String, Object> copiedMap = ValidatorUtils.copyMap(originalMap);

        assertEquals(originalMap.size(), copiedMap.size());
        for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
            String key = entry.getKey();
            Object originalValue = entry.getValue();
            Object copiedValue = copiedMap.get(key);

            assertEquals(originalValue, copiedValue);
            if (originalValue instanceof Cloneable) {
                assertTrue(originalValue != copiedValue); // Ensure deep copy
            }
        }
    }
}
