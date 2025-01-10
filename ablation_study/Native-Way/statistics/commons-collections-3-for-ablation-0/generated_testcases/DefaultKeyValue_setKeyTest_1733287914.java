
package org.apache.commons.collections4.keyvalue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultKeyValue_setKeyTest {

    private DefaultKeyValue<String, String> defaultKeyValue;

    @BeforeEach
    public void setUp() {
        defaultKeyValue = new DefaultKeyValue<>();
    }

    @Test
    public void testSetKeyValid() {
        String key = "newKey";
        String oldKey = defaultKeyValue.setKey(key);
        assertNull(oldKey);
        assertEquals(key, defaultKeyValue.getKey());
    }

    @Test
    public void testSetKeySelfReference() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            defaultKeyValue.setKey((String) defaultKeyValue);
        });
        assertEquals("DefaultKeyValue may not contain itself as a key.", exception.getMessage());
    }
}
