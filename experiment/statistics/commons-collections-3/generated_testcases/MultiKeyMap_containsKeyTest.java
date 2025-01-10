
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_containsKeyTest {

    private MultiKeyMap<Integer, String> multiKeyMap;
    private static final Integer I1 = 1;
    private static final Integer I2 = 2;

    @BeforeEach
    public void setUp() {
        multiKeyMap = new MultiKeyMap<>();
    }

    @Test
    public void testContainsKey_KeyExists() {
        multiKeyMap.put(I1, I2, "1-2");
        assertTrue(multiKeyMap.containsKey(I1, I2));
    }

    @Test
    public void testContainsKey_KeyDoesNotExist() {
        multiKeyMap.put(I1, I2, "1-2");
        assertFalse(multiKeyMap.containsKey(I1, 3));
    }

    @Test
    public void testContainsKey_NullKey() {
        multiKeyMap.put(null, I2, "null-2");
        assertTrue(multiKeyMap.containsKey(null, I2));
    }

    @Test
    public void testContainsKey_NullKeyDoesNotExist() {
        multiKeyMap.put(I1, I2, "1-2");
        assertFalse(multiKeyMap.containsKey(null, I2));
    }
}
